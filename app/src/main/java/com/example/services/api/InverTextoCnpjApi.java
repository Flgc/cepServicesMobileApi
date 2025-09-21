package com.example.services.api;

import com.example.services.model.Address;
import com.example.services.model.Cnpj;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/* Retrofit turns your HTTP API into a Java (or Kotlin) interface. */
public interface InverTextoCnpjApi {
    @GET("/v1/cnpj/{cnpjCodNumber}")
    Call<Cnpj> getCnpj(
            @Path("cnpjCodNumber") String cnpjCodNumber,
            @Query("token") String token
    );
}
