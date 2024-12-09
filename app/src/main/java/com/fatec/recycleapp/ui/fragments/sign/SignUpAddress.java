package com.fatec.recycleapp.ui.fragments.sign;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.fatec.recycleapp.R;
import com.fatec.recycleapp.model.address.Cep;
import com.fatec.recycleapp.model.address.City;
import com.fatec.recycleapp.model.address.State;
import com.fatec.recycleapp.services.CEPService;
import com.fatec.recycleapp.services.IBGEService;
import com.vicmikhailau.maskededittext.MaskedEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpAddress extends Fragment {
    private IBGEService ibgeService;
    private CEPService cepService;
    private AutoCompleteTextView state;
    private AutoCompleteTextView city;
    private List<State> states;
    private List<City> cities;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up_address, container, false);
        states = new ArrayList<>();
        cities = new ArrayList<>();

        ibgeService = new Retrofit.Builder()
                .baseUrl("https://servicodados.ibge.gov.br/api/v1/localidades/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(IBGEService.class);

        cepService = new Retrofit.Builder()
                .baseUrl("https://viacep.com.br/ws/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CEPService.class);

        EditText street = view.findViewById(R.id.fsuaStreet);
        EditText neighborhood = view.findViewById(R.id.fsuaNeighborhood);
        EditText number = view.findViewById(R.id.fsuaNumber);
        EditText complement = view.findViewById(R.id.fsuaComplement);
        EditText reference = view.findViewById(R.id.fsuaReference);
        MaskedEditText cep = view.findViewById(R.id.fsuaCep);
        state = view.findViewById(R.id.fsuaState);
        city = view.findViewById(R.id.fsuaCity);

        state.setKeyListener(null);
        city.setKeyListener(null);

        state.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                for(State st : states) {
                    System.out.println("Comparando " + state.getText().toString() + " com " + st.getName());
                    if(st.getName().equalsIgnoreCase(s.toString())) {
                        loadCities(st.getId());
                        break;
                    }
                }
            }
        });

        cep.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = cep.getText().toString();

                if(text.replace("-", "").length() == 8){
                    System.out.println("CEP: " + text);
                    cepService.getData(text.replace("-", "")).enqueue(new Callback<Cep>() {
                        @Override
                        public void onResponse(Call<Cep> call, Response<Cep> response) {
                            System.out.println(response.isSuccessful() ? "CEP Response successful" + response.code() : "CEP Response failed");
                            System.out.println("CEP Body is null: " + response.body() == null);

                            if(response.body().getCep() == null)
                                return;

                            Cep c = response.body();

                            // Programação porca, arruma dps
                            cities.clear();
                            cities.add(new City(c.getCity()));

                            street.setText(c.getStreet());
                            neighborhood.setText(c.getNeighborhood());
                            state.setText(c.getState());
                        }

                        @Override
                        public void onFailure(Call<Cep> call, Throwable t) {
                            t.printStackTrace();
                            Toast.makeText(getContext(), "Erro ao buscar CEP", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        Button back = view.findViewById(R.id.fsuaBack);
        Button next = view.findViewById(R.id.fsuaNext);

        next.setOnClickListener(v -> {
            Bundle bundle = getArguments();

            if(street.getText().toString().isEmpty() || neighborhood.getText().toString().isEmpty() || cep.getText().toString().isEmpty()) {
                Toast.makeText(getContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            bundle.putString("street", street.getText().toString());
            bundle.putString("neighborhood", neighborhood.getText().toString());
            bundle.putString("number", number.getText().toString());
            bundle.putString("cep", cep.getText().toString());
            bundle.putString("complement", complement.getText().toString());
            bundle.putString("reference", reference.getText().toString());
            bundle.putString("state", state.getText().toString());
            bundle.putString("city", city.getText().toString());

            SignUpEmail signUpEmail = new SignUpEmail();
            signUpEmail.setArguments(bundle);

            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, signUpEmail)
                    .commit();
        });

        back.setOnClickListener(v -> {
            assert getActivity() != null;
            getActivity().onBackPressed();
        });

        loadStates();

        return view;
    }

    private void loadStates() {
        ibgeService.getStates().enqueue(new Callback<List<State>>() {
            @Override
            public void onResponse(Call<List<State>> call, Response<List<State>> response) {
                System.out.println(response.isSuccessful() ? "State Response successful" + response.code() : "State Response failed");

                assert response.body() != null;
                assert getActivity() != null;

                if(states.isEmpty()) {
                    states.addAll(response.body());
                }  else {
                    states.clear();
                    states.addAll(response.body());
                }

                String[] states = new String[response.body().size()];

                for(int i = 0; i < states.length; i++) {
                    states[i] = response.body().get(i).getName();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, states);
                state.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<State>> call, Throwable t) {
                t.printStackTrace();
                System.out.println("State erro");
                Toast.makeText(getContext(), "Erro ao buscar estados", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadCities(int stateId) {
        if(cities.size() == 1) {
            city.setText(cities.get(0).getName());
        } else {
            city.setText("");
        }

        ibgeService.getCities(stateId).enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                assert response.body() != null;
                assert getActivity() != null;

                String[] citiesArray = new String[response.body().size()];

                for(int i = 0; i < citiesArray.length; i++) {
                    citiesArray[i] = response.body().get(i).getName();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, citiesArray);
                city.setAdapter(adapter);

                if(cities.isEmpty()) {
                    city.setText("");
                }
            }

            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {
                Toast.makeText(getContext(), "Erro ao buscar cidades", Toast.LENGTH_SHORT).show();
            }
        });
    }
}