package com.fatec.recycleapp.ui.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Base64;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fatec.recycleapp.R;
import com.fatec.recycleapp.adapter.ChatAdapter;
import com.fatec.recycleapp.adapter.SuggestionAdapter;
import com.fatec.recycleapp.model.MaterialCategory;
import com.fatec.recycleapp.model.MessageConnection;
import com.fatec.recycleapp.model.MessageType;
import com.fatec.recycleapp.model.Suggestion;
import com.fatec.recycleapp.util.DetectedObjects;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ChatActivity extends AppCompatActivity {
    private ImageButton backButton;
    private RecyclerView chatView;
    private EditText textInput;
    private ImageButton sendButton;
    private RecyclerView suggestionView;
    private List<MaterialCategory> subjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        subjects = new ArrayList<>();

        backButton = findViewById(R.id.backButton);
        chatView = findViewById(R.id.chatView);
        textInput = findViewById(R.id.textInput);
        sendButton = findViewById(R.id.sendButton);
        suggestionView = findViewById(R.id.suggestionView);

        setupChat();
        setupSuggestion();

        backButton.setOnClickListener(v -> {
            finish();
        });

        sendButton.setOnClickListener(v -> {
            if(textInput.getText().toString().isBlank())
                return;

            if(((ChatAdapter) chatView.getAdapter()).addItem(textInput.getText().toString(), MessageConnection.RECEIVER)) {
                sendAskToServer(textInput.getText().toString());
                ((ChatAdapter) chatView.getAdapter()).cantSend();
                textInput.setText("");
            } else {
                Toast.makeText(this, "Aguarde para enviar outra mensagem!", Toast.LENGTH_SHORT).show();
            }
        });

        Intent intent = getIntent();
        DetectedObjects detectedObjects = intent.getParcelableExtra("detectedObjects");

        if(detectedObjects != null) {
            ((ChatAdapter) chatView.getAdapter()).addItem(detectedObjects.getSource(), MessageConnection.RECEIVER);
            ((ChatAdapter) chatView.getAdapter()).cantSend();
            sendImageToServer(detectedObjects);
        } else {
            ((ChatAdapter) chatView.getAdapter()).addItem("Olá! Eu sou o assistente do aplicativo RecycleApp! No que posso ajudar?", MessageConnection.SENDER);
            ((ChatAdapter) chatView.getAdapter()).canSend();
        }
    }

    private void setupChat() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        chatView.setLayoutManager(layoutManager);

        ChatAdapter adapter = new ChatAdapter(this);
        chatView.setAdapter(adapter);
        adapter.setOnClick(new Function<String, Void>() {
            @Override
            public Void apply(String s) {
                if(((ChatAdapter) chatView.getAdapter()).addItem(s, MessageConnection.RECEIVER)) {
                    sendAskToServer(s);
                    ((ChatAdapter) chatView.getAdapter()).cantSend();
                }
                return null;
            }
        });

        adapter.setOnAdd(new Function<Void, Void>() {
            @Override
            public Void apply(Void unused) {
                int position = chatView.getAdapter().getItemCount() - 1;
                chatView.scrollToPosition(position);
                return null;
            }
        });
    }

    private void setupSuggestion() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        suggestionView.setLayoutManager(layoutManager);

        SuggestionAdapter adapter = new SuggestionAdapter((ChatAdapter) chatView.getAdapter());
        adapter.setOnClick(new Function<String, Void>() {
            @Override
            public Void apply(String s) {
                if(((ChatAdapter) chatView.getAdapter()).addItem(s, MessageConnection.RECEIVER)) {
                    sendAskToServer(s);
                    ((ChatAdapter) chatView.getAdapter()).cantSend();
                }
                return null;
            }
        });

        suggestionView.setAdapter(adapter);
    }

    private void sendAskToServer(String input) {
        OkHttpClient client = new OkHttpClient();

        JSONObject jsonBody = new JSONObject();
        try {
            if(!subjects.isEmpty()) {
                StringBuilder builder = new StringBuilder();
                for (MaterialCategory category : subjects) {
                    builder.append(category.getName()).append(", ");
                }
                builder.delete(builder.length() - 2, builder.length());

                jsonBody.put("materials",  builder.toString());
                subjects.clear();
            } else {
                jsonBody.put("materials", "");
            }

            jsonBody.put("question", input);
            jsonBody.put("identifier", Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"),
                jsonBody.toString()
        );

        Request request = new Request.Builder()
                .url("http://192.168.0.11:5000/ask")
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(() -> {
                    ((ChatAdapter) chatView.getAdapter()).addItem("Erro ao enviar mensagem para o servidor.", MessageConnection.SENDER);
                    ((ChatAdapter) chatView.getAdapter()).canSend();
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String jsonResponse = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(jsonResponse);
                        String answer = jsonObject.optString("answer");

                        runOnUiThread(() -> {
                            if (!answer.isEmpty()) {
                                processAnswer(answer.trim());
                            } else {
                                ((ChatAdapter) chatView.getAdapter()).addItem("Nenhuma resposta do servidor.", MessageConnection.SENDER);
                                ((ChatAdapter) chatView.getAdapter()).canSend();
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                        runOnUiThread(() -> {
                            ((ChatAdapter) chatView.getAdapter()).addItem("Erro ao processar resposta do servidor!", MessageConnection.SENDER);
                            ((ChatAdapter) chatView.getAdapter()).canSend();
                        });
                    }
                } else {
                    runOnUiThread(() -> {
                        ((ChatAdapter) chatView.getAdapter()).addItem("Falha na requisição!", MessageConnection.SENDER);
                        ((ChatAdapter) chatView.getAdapter()).canSend();
                    });
                }
            }
        });
    }

    private void sendImageToServer(DetectedObjects detectedObjects) {
        new Thread(() -> {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                detectedObjects.getSource().compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();

                OkHttpClient client = new OkHttpClient();
                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("image", "image.jpg",
                                RequestBody.create(MediaType.parse("image/jpeg"), byteArray))
                        .build();

                Request request = new Request.Builder()
                        .url("http://192.168.0.11:5000/detect")
                        .post(requestBody)
                        .build();

                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    String jsonResponse = response.body().string();

                    JSONObject jsonObject = new JSONObject(jsonResponse);
                    String imageBase64 = jsonObject.optString("image");
                    JSONArray detectionArray = jsonObject.optJSONArray("detection");

                    if (!imageBase64.isEmpty()) {
                        byte[] decodedString = Base64.decode(imageBase64, Base64.DEFAULT);
                        Bitmap resultBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                        new Handler(Looper.getMainLooper()).post(() -> {
                            if (resultBitmap != null) {
                                detectedObjects.setResult(resultBitmap);
                            } else {
                                ((ChatAdapter) chatView.getAdapter()).addItem("Falha ao processar a imagem!", MessageConnection.SENDER);
                                ((ChatAdapter) chatView.getAdapter()).canSend();
                            }
                        });
                    }

                    if (detectionArray != null) {
                        new Handler(Looper.getMainLooper()).post(() -> {
                            for (int i = 0; i < detectionArray.length(); i++) {
                                JSONObject detection = detectionArray.optJSONObject(i);
                                detectedObjects.addDetection(detection.optInt("category"), detection.optDouble("score"));
                                subjects.add(MaterialCategory.values()[detection.optInt("category")]);
                            }
                        });
                    }

                    new Handler(Looper.getMainLooper()).post(() -> {
                        ((ChatAdapter) chatView.getAdapter()).addItem(detectedObjects);
                        ((ChatAdapter) chatView.getAdapter()).canSend();
                    });
                } else {
                    new Handler(Looper.getMainLooper()).post(() -> {
                        ((ChatAdapter) chatView.getAdapter()).addItem("Erro no servidor!", MessageConnection.SENDER);
                        ((ChatAdapter) chatView.getAdapter()).canSend();
                    });
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                new Handler(Looper.getMainLooper()).post(() -> {
                    ((ChatAdapter) chatView.getAdapter()).addItem("Erro ao enviar imagem!", MessageConnection.SENDER);
                    ((ChatAdapter) chatView.getAdapter()).canSend();
                });
            }
        }).start();
    }

    private void processAnswer(String answer) {
        ((ChatAdapter) chatView.getAdapter()).canSend();

        if(answer.contains("<ask_material>")) {
            ((ChatAdapter) chatView.getAdapter()).chooseMaterial();
            return;
        }

        if(answer.contains("<>")) {
            return;
        }

        ((ChatAdapter) chatView.getAdapter()).addItem(answer, MessageConnection.SENDER);
    }
}
