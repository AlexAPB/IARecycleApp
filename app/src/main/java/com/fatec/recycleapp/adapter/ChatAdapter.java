package com.fatec.recycleapp.adapter;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fatec.recycleapp.R;
import com.fatec.recycleapp.model.MaterialCategory;
import com.fatec.recycleapp.model.Message;
import com.fatec.recycleapp.model.MessageConnection;
import com.fatec.recycleapp.model.MessageType;
import com.fatec.recycleapp.util.ButtonUtil;
import com.fatec.recycleapp.util.DetectedObjects;
import com.fatec.recycleapp.util.ImageUtil;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.shape.CornerFamily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private final Context context;
    private Function<Void, Void> onAdd;
    private Function<String, Void> onClick;
    private final List<Message> messages;
    private boolean canSend = true;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final FrameLayout chatLayout;
        private final CardView cardView;

        public ViewHolder(View view) {
            super(view);
            chatLayout = view.findViewById(R.id.chatLayout);
            cardView = view.findViewById(R.id.chatCard);
        }

        public CardView getCardView() {
            return cardView;
        }

        public void setConnection(MessageConnection connection) {
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) cardView.getLayoutParams();

            int pad = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    40,
                    chatLayout.getContext().getResources().getDisplayMetrics()
            );

            if(connection == MessageConnection.SENDER) {
                params.gravity = Gravity.LEFT | Gravity.START;
                chatLayout.setPadding(0, 0, pad, 0);
            } else {
                params.gravity = Gravity.RIGHT | Gravity.END;
                chatLayout.setPadding(pad, 0, 0, 0);
            }
        }
    }

    public ChatAdapter(Context context) {
        this.context = context;
        messages = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chat_view, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Message message = messages.get(position);

        switch (message.getType()) {
            case TEXT:
                TextView textView = new TextView(context);
                textView.setText((String) message.getMsg());
                textView.setTextSize(16);
                textView.setTextColor(Color.BLACK);
                holder.getCardView().addView(textView);
                holder.getCardView().setVisibility(View.VISIBLE);
                break;
            case IMAGE:
                ShapeableImageView imageView = new ShapeableImageView(context);
                imageView.setImageBitmap((Bitmap) message.getMsg());
                imageView.setAdjustViewBounds(true);
                imageView.setShapeAppearanceModel(imageView.getShapeAppearanceModel()
                        .toBuilder()
                        .setAllCorners(CornerFamily.ROUNDED,20f)
                        .build());

                ImageUtil.paddingRightToLeft(holder.getCardView());

                holder.getCardView().addView(imageView);
                break;
            case DETECTION:
                DetectedObjects detectedObjects = (DetectedObjects) message.getMsg();

                LinearLayout linearLayout = new LinearLayout(context);
                linearLayout.setOrientation(LinearLayout.VERTICAL);

                TextView resultText = new TextView(context);

                if(!detectedObjects.getCategories().isEmpty()) {
                    if(detectedObjects.getResult() != null) {
                        ShapeableImageView resultView = new ShapeableImageView(context);
                        resultView.setImageBitmap(detectedObjects.getResult());
                        resultView.setAdjustViewBounds(true);
                        resultView.setShapeAppearanceModel(resultView.getShapeAppearanceModel()
                                .toBuilder()
                                .setAllCorners(CornerFamily.ROUNDED, 20f)
                                .build());

                        ImageUtil.paddingRightToLeft(holder.getCardView());

                        linearLayout.addView(resultView);
                    }

                    StringBuilder builder = new StringBuilder();
                    builder.append("Materiais detectados:").append("\n");

                    for(int i = 0; i < detectedObjects.getCategories().size(); i++) {
                        String category = detectedObjects.getCategories().get(i).getName();
                        String score = String.valueOf(detectedObjects.getScores().get(i));

                        builder.append(" - ").append(category).append(" (").append(String.format("%.2f", Float.parseFloat(score) * 100f)).append("%)").append("\n");
                    }

                    resultText.setText(builder.toString());
                    resultText.setTextSize(16);
                    resultText.setTextColor(Color.BLACK);
                } else {
                    resultText.setText("Nenhum material confiável detectado!");
                    resultText.setTextSize(16);
                    resultText.setTextColor(Color.BLACK);
                }

                linearLayout.addView(resultText);

                holder.getCardView().addView(linearLayout);
                break;
            case CHOOSE_MATERIAL:
                LinearLayout chooseLayout = new LinearLayout(context);
                chooseLayout.setOrientation(LinearLayout.VERTICAL);

                TextView chooseText = new TextView(context);
                chooseText.setText("Sobre qual material deseja falar?");
                chooseText.setTextSize(16);
                chooseText.setTextColor(Color.BLACK);
                chooseText.setTypeface(null, Typeface.BOLD);

                LinearLayout.LayoutParams spinnerParams = new LinearLayout.LayoutParams(
                        MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                spinnerParams.setMargins(0, 25, 0, 25);

                Spinner spinner = new Spinner(context);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                        context,
                        android.R.layout.simple_spinner_item,
                        Arrays.stream(MaterialCategory.values()).map(MaterialCategory::getName).collect(Collectors.toList())
                );
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                spinner.setLayoutParams(spinnerParams);

                Button chooseButton = new Button(context);
                chooseButton.setText("Escolher");
                chooseButton.setTextColor(Color.WHITE);
                ButtonUtil.wrapToMatch(chooseButton, true, false);
                ButtonUtil.corner(chooseButton, 12, 0xFF007E2F);
                chooseButton.setPadding(0, 20, 0, 20);
                chooseButton.setMinHeight(0);
                chooseButton.setMinimumHeight(0);
                chooseButton.setOnClickListener(v -> {
                    onClick.apply((String) spinner.getSelectedItem());
                    chooseButton.setEnabled(false);
                });

                chooseLayout.addView(chooseText);
                chooseLayout.addView(spinner);
                chooseLayout.addView(chooseButton);

                holder.getCardView().getLayoutParams().width = MATCH_PARENT;
                holder.getCardView().addView(chooseLayout);
                break;
        }

        holder.setConnection(message.getConnection());
    }

    public boolean addItem(String message, MessageConnection connection) {
        if(!canSend && connection == MessageConnection.RECEIVER) {
            Toast.makeText(context, "Aguarde para enviar outra mensagem!", Toast.LENGTH_SHORT).show();
            return false;
        }

        Message msg = new Message(message, MessageType.TEXT, connection);
        messages.add(msg);

        notifyItemInserted(messages.size() - 1);
        onAdd.apply(null);
        return true;
    }

    public boolean addItem(Bitmap message, MessageConnection connection) {
        if(!canSend && connection == MessageConnection.RECEIVER) {
            Toast.makeText(context, "Aguarde para enviar outra mensagem!", Toast.LENGTH_SHORT).show();
            return false;
        }

        Message msg = new Message(message, MessageType.IMAGE, connection);
        messages.add(msg);

        notifyItemInserted(messages.size() - 1);
        onAdd.apply(null);
        return true;
    }

    public boolean addItem(DetectedObjects message) {
        Message msg = new Message(message, MessageType.DETECTION, MessageConnection.SENDER);
        messages.add(msg);

        notifyItemInserted(messages.size() - 1);
        onAdd.apply(null);
        return true;
    }

    public boolean chooseMaterial() {
        Message msg = new Message(null, MessageType.CHOOSE_MATERIAL, MessageConnection.SENDER);
        messages.add(msg);

        notifyItemInserted(messages.size() - 1);
        onAdd.apply(null);
        return true;
    }

    public void canSend() {
        canSend = true;
    }

    public void cantSend() {
        canSend = false;
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void setOnClick(Function<String, Void> onClick) {
        this.onClick = onClick;
    }

    public void setOnAdd(Function<Void, Void> onAdd) {
        this.onAdd = onAdd;
    }

    public List<Message> getMessages() {
        return messages;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
