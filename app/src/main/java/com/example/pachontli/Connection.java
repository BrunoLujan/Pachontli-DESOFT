package com.example.pachontli;

import android.content.Context;
import android.widget.Toast;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.pachontli.interfaces.ApiService;
import com.example.pachontli.models.User;

public class Connection {
    public static final String URL = "http://192.168.0.12/";
    public static String HOME = URL + "api/";
    private static Retrofit retrofit;
    public static ApiService CONNECTION;
    public static String AUTHTOKEN;
    public static User LOGGEDUSER = new User();

    public static Retrofit getRetrofit() {
        if(retrofit==null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(HOME)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static void getUserService() {
        ApiService getUserService = getRetrofit().create(ApiService.class);
        CONNECTION = getUserService;
    }

    public static void Message(Context context, String message) {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }
}
