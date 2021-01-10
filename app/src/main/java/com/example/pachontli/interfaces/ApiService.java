package com.example.pachontli.interfaces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @POST("registrarCliente")
    @FormUrlEncoded
    Call<ResponseBody> signUpUser(
            @Field("nombre") String nombre,
            @Field("apellidos") String apellidos,
            @Field("email") String email,
            @Field("telefono") String telefono,
            @Field("password") String password
    );
}