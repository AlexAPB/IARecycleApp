package com.fatec.recycleapp.services;

import com.fatec.recycleapp.model.address.City;
import com.fatec.recycleapp.model.address.State;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IBGEService {
    @GET("estados")
    Call<List<State>> getStates();

    @GET("estados/{id}/municipios")
    Call<List<City>> getCities(@Path("id") int stateId);
}
