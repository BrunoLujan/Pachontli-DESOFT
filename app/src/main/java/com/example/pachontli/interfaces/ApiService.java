package com.example.pachontli.interfaces;

import com.example.pachontli.models.Pet;
import com.example.pachontli.models.User;
import com.example.pachontli.requests.LoginRequest;
import com.example.pachontli.requests.RegisterPetRequest;
import com.example.pachontli.responses.LoginResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {

    @POST("registrarCliente")
    @FormUrlEncoded
    Call<ResponseBody> signUpClient(
            @Field("nombre") String nombre,
            @Field("apellidos") String apellidos,
            @Field("email") String email,
            @Field("telefono") String telefono,
            @Field("password") String password
    );

    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest user);

    @POST("logout")
    Call<ResponseBody> logout();

    @GET("getCliente")
    Call<User> getLoggedClient(@Header("Authorization") String authToken);

    @POST("registrarMascota")
    Call<ResponseBody> registerPet(@Header("Authorization") String authToken, @Body RegisterPetRequest pet);

    @GET("getMascotas")
    Call<List<Pet>> getPets(@Header("Authorization") String authToken);
}