package com.fatec.recycleapp.ui.fragments.sign;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.fatec.recycleapp.R;
import com.fatec.recycleapp.model.user.attributes.UserType;

public class SignUpType extends Fragment {
    private CardView producerCard;
    private CardView handlerCard;
    private CardView enterpriseCard;
    private UserType userType;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up_type, container, false);

        producerCard = view.findViewById(R.id.fsuTypeCardProducer);
        handlerCard = view.findViewById(R.id.fsuTypeCardHandler);
        enterpriseCard = view.findViewById(R.id.fsuTypeCardEnterprise);

        producerCard.setOnClickListener(v -> {
            changeType(UserType.TRASH_PRODUCER);
        });

        handlerCard.setOnClickListener(v -> {
            changeType(UserType.TRASH_HANDLER);
        });

        enterpriseCard.setOnClickListener(v -> {
            changeType(UserType.ENTERPRISE);
        });

        Button nextButton = view.findViewById(R.id.fsuiPersonNext);
        nextButton.setOnClickListener(v -> {
            if(userType == null) {
                Toast.makeText(getContext(), "Selecione um tipo de usuário!", Toast.LENGTH_SHORT).show();
                return;
            }

            Bundle bundle = new Bundle();
            bundle.putInt("type", userType.getId());

            SignUpData dataFragment = new SignUpData();
            dataFragment.setArguments(bundle);

            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, dataFragment)
                    .addToBackStack(null)
                    .commit();
        });

        return view;
    }

    private void changeType(UserType type) {
        if(type == userType)
            return;

        if(userType != null) {
            switch (userType) {
                case TRASH_PRODUCER:
                    producerCard.setCardBackgroundColor(getResources().getColor(R.color.white));
                    break;
                case TRASH_HANDLER:
                    handlerCard.setCardBackgroundColor(getResources().getColor(R.color.white));
                    break;
                case ENTERPRISE:
                    enterpriseCard.setCardBackgroundColor(getResources().getColor(R.color.white));
                    break;
            }
        }

        this.userType = type;

        switch (userType) {
            case TRASH_PRODUCER:
                producerCard.setCardBackgroundColor(getResources().getColor(R.color.card_selected));
                break;
            case TRASH_HANDLER:
                handlerCard.setCardBackgroundColor(getResources().getColor(R.color.card_selected));
                break;
            case ENTERPRISE:
                enterpriseCard.setCardBackgroundColor(getResources().getColor(R.color.card_selected));
                break;
        }
    }
}