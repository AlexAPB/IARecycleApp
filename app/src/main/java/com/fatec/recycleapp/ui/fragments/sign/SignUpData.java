package com.fatec.recycleapp.ui.fragments.sign;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.fatec.recycleapp.R;
import com.fatec.recycleapp.model.user.attributes.UserGender;
import com.fatec.recycleapp.model.user.attributes.UserType;
import com.google.android.material.textfield.TextInputEditText;
import com.vicmikhailau.maskededittext.MaskedEditText;

public class SignUpData extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        assert getArguments() != null;
        UserType user = UserType.fromId(getArguments().getInt("type"));

        View view = null;
        switch (user) {
            case TRASH_PRODUCER:
            case TRASH_HANDLER:
                view = onCreatePerson(inflater, container, getArguments());
                break;
            case ENTERPRISE:
                view = onCreateEnterprise(inflater, container, getArguments());
                break;
        }

        return view;
    }

    private View onCreatePerson(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = inflater.inflate(R.layout.fragment_sign_up_information_person, container, false);

        TextInputEditText nameText = view.findViewById(R.id.fsuiPersonName);
        TextInputEditText lastNameText = view.findViewById(R.id.fsuiPersonLastName);
        MaskedEditText cpfText = view.findViewById(R.id.fsuiPersonCPF);
        MaskedEditText birthText = view.findViewById(R.id.fsuiPersonBirth);
        MaskedEditText phoneText = view.findViewById(R.id.fsuiPersonPhone);
        Spinner genderSpinner = view.findViewById(R.id.fsuiPersonGender);

        TextView errorText = view.findViewById(R.id.fsuiPersonError);
        Button backButton = view.findViewById(R.id.fsuiPersonBack);
        Button nextButton = view.findViewById(R.id.fsuiPersonNext);

        nextButton.setOnClickListener(v -> {
            String name = nameText.getText().toString();
            String lastName = lastNameText.getText().toString();
            String cpf = cpfText.getText().toString();
            String birth = birthText.getText().toString();
            String phone = phoneText.getText().toString();
            String genderText = genderSpinner.getSelectedItem().toString();

            if (name.isEmpty() || lastName.isEmpty() || cpf.isEmpty() || birth.isEmpty() || genderText.isEmpty()) {
                return;
            }

            if(!genderText.equals("Masculino") && !genderText.equals("Feminino")){
                return;
            }

            UserGender gender = UserGender.fromString(genderText);

            bundle.putString("name", name);
            bundle.putString("lastName", lastName);
            bundle.putString("cpf", cpf);
            bundle.putString("birth", birth);
            bundle.putString("phone", phone);
            bundle.putInt("gender", gender.getId());

            SignUpAddress addressFragment = new SignUpAddress();
            addressFragment.setArguments(bundle);

            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, addressFragment)
                    .addToBackStack(null)
                    .commit();
        });

        backButton.setOnClickListener(v -> {
            assert getActivity() != null;
            getActivity().onBackPressed();
        });

        return view;
    }

    private View onCreateEnterprise(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = inflater.inflate(R.layout.fragment_sign_up_information_enterprise, container, false);

        TextInputEditText legalName = view.findViewById(R.id.fsuiEnterpriseLegalName);
        TextInputEditText brandName = view.findViewById(R.id.fsuiEnterpriseBrandName);
        TextInputEditText cnpj = view.findViewById(R.id.fsuiEnterpriseCNPJ);
        TextInputEditText description = view.findViewById(R.id.fsuiEnterpriseDescription);
        TextInputEditText email = view.findViewById(R.id.fsuiEnterpriseEmail);
        TextInputEditText phone = view.findViewById(R.id.fsuiEnterprisePhone);

        TextView errorText = view.findViewById(R.id.fsuiPersonError);
        Button backButton = view.findViewById(R.id.fsuiEnterpriseBack);
        Button nextButton = view.findViewById(R.id.fsuiEnterpriseNext);

        nextButton.setOnClickListener(v -> {
            String legalNameText = legalName.getText().toString();
            String brandNameText = brandName.getText().toString();
            String cnpjText = cnpj.getText().toString();
            String descriptionText = description.getText().toString();
            String emailText = email.getText().toString();
            String phoneText = phone.getText().toString();

            if (legalNameText.isEmpty() || brandNameText.isEmpty() || cnpjText.isEmpty() || descriptionText.isEmpty() || emailText.isBlank() || phoneText.isBlank()) {
                return;
            }

            bundle.putString("legalName", legalNameText);
            bundle.putString("brandName", brandNameText);
            bundle.putString("cnpj", cnpjText);
            bundle.putString("description", descriptionText);
            bundle.putString("email", emailText);
            bundle.putString("phone", phoneText);

            SignUpAddress addressFragment = new SignUpAddress();
            addressFragment.setArguments(bundle);

            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, addressFragment)
                    .addToBackStack(null)
                    .commit();
        });

        backButton.setOnClickListener(v -> {
            assert getActivity() != null;
            getActivity().onBackPressed();
        });

        return view;
    }
}