package com.fatec.recycleapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fatec.recycleapp.R;
import com.fatec.recycleapp.model.bot.BotSuggestion;

import java.util.function.Function;

public class SuggestionAdapter extends RecyclerView.Adapter<SuggestionAdapter.ViewHolder>{
    private ChatAdapter chatAdapter;
    private Function<String, Void> onClick;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CardView card;
        private final TextView text;

        public ViewHolder(View view) {
            super(view);
            card = view.findViewById(R.id.cardSuggestion);
            text = view.findViewById(R.id.textSuggestion);
        }

        public CardView getCard() {
            return card;
        }

        public TextView getText() {
            return text;
        }
    }

    public SuggestionAdapter(ChatAdapter chatAdapter) {
        this.chatAdapter = chatAdapter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.text_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getText().setText(BotSuggestion.values()[position].getAbbreviated());
        holder.getCard().setOnClickListener(v -> {
            onClick.apply(BotSuggestion.values()[position].getPhrase());
        });
    }

    public void setOnClick(Function<String, Void> onClick) {
        this.onClick = onClick;
    }

    @Override
    public int getItemCount() {
        return BotSuggestion.values().length;
    }
}
