package com.fatec.recycleapp.ui.fragments.sign;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.fatec.recycleapp.R;
import com.fatec.recycleapp.model.user.Enterprise;
import com.fatec.recycleapp.model.user.TrashHandler;
import com.fatec.recycleapp.model.user.TrashProducer;
import com.fatec.recycleapp.model.user.User;
import com.fatec.recycleapp.model.user.attributes.Address;
import com.fatec.recycleapp.model.user.attributes.UserGender;
import com.fatec.recycleapp.model.user.attributes.UserType;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vicmikhailau.maskededittext.MaskedEditText;

public class SignUpEmail extends Fragment {
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private FirebaseAuth auth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        assert getArguments() != null;

        View view = inflater.inflate(R.layout.fragment_sign_up_email, container, false);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("users");
        auth = FirebaseAuth.getInstance();

        EditText emailText = view.findViewById(R.id.fsueEmail);
        EditText passwordText = view.findViewById(R.id.fsuePassword);
        Button back = view.findViewById(R.id.fsuaBack);
        Button next = view.findViewById(R.id.fsuaNext);
        TextView error = view.findViewById(R.id.fsuaErrorText);

        next.setOnClickListener(v -> {
            if(emailText.getText().toString().isEmpty() || passwordText.getText().toString().isEmpty()) {
                Toast.makeText(getContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            String email = emailText.getText().toString();
            String password = passwordText.getText().toString();

            register(email, password);
        });

        back.setOnClickListener(v -> {
            assert getActivity() != null;
            getActivity().onBackPressed();
        });

        return view;
    }

    private void register(String email, String password) {
        Bundle bundle = getArguments();
        UserType type = UserType.fromId(getArguments().getInt("type"));
        User user = null;

        switch (type) {
            case TRASH_PRODUCER:
                user = new TrashProducer();
                user.setName(bundle.getString("name"));
                user.setLastName(bundle.getString("lastName"));
                user.setPhone(bundle.getString("phone"));
                ((TrashProducer) user).setCpf(bundle.getString("cpf"));
                ((TrashProducer) user).setBirth(bundle.getString("birth"));
                ((TrashProducer) user).setGender(UserGender.fromId(bundle.getInt("gender")));
                break;
            case TRASH_HANDLER:
                user = new TrashHandler();
                user.setName(bundle.getString("name"));
                user.setLastName(bundle.getString("lastName"));
                user.setPhone(bundle.getString("phone"));
                ((TrashHandler) user).setCpf(bundle.getString("cpf"));
                ((TrashHandler) user).setBirth(bundle.getString("birth"));
                ((TrashHandler) user).setGender(UserGender.fromId(bundle.getInt("gender")));
                break;
            case ENTERPRISE:
                user = new Enterprise();
                ((Enterprise) user).setLegalName(bundle.getString("legalName"));
                ((Enterprise) user).setBrandName(bundle.getString("brandName"));
                ((Enterprise) user).setCnpj(bundle.getString("cnpj"));
                ((Enterprise) user).setDescription(bundle.getString("description"));
                ((Enterprise) user).setEmail(bundle.getString("email"));
                ((Enterprise) user).setPhone(bundle.getString("phone"));
                break;
        }

        user.setEmail(email);
        user.setPassword(password);
        user.setUserType(type);

        Address address = new Address();
        address.setStreet(bundle.getString("street"));
        address.setNumber(Integer.parseInt(bundle.getString("number")));
        address.setComplement(bundle.getString("complement"));
        address.setNeighborhood(bundle.getString("neighborhood"));
        address.setCity(bundle.getString("city"));
        address.setState(bundle.getString("state"));
        address.setZipCode(bundle.getString("cep"));
        address.setReference(bundle.getString("reference"));

        user.add(address);

        User finalUser = user;
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), task -> {
                    if(task.isSuccessful()) {
                        Toast.makeText(getContext(), "Usuário criado com sucesso!", Toast.LENGTH_SHORT).show();

                        switch (type) {
                            case TRASH_PRODUCER:
                                reference.push().setValue((TrashProducer) finalUser);
                                break;
                            case TRASH_HANDLER:
                                reference.push().setValue((TrashHandler) finalUser);
                                break;
                            case ENTERPRISE:
                                reference.push().setValue((Enterprise) finalUser);
                                break;
                        }

                        getActivity().finish();
                    } else {
                        Toast.makeText(getContext(), "Erro ao criar usuário!", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}