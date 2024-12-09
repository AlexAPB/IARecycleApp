package com.fatec.recycleapp.services;

import com.fatec.recycleapp.model.address.Cep;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CEPService {
    @GET("{cep}/json")
    Call<Cep> getData(@Path("cep") String cep);

}
