package com.example.pachontli;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.example.pachontli.models.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_pets, R.id.navigation_calendar, R.id.navigation_qualify, R.id.navigation_settings)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        if(Connection.LOGGEDUSER == null) {
            getDates();
        }
    }

    @Override
    public void onBackPressed() { }

    private void getDates(){
        Call<User> userResponseCall = Connection.CONNECTION.getLoggedClient(Connection.AUTHTOKEN);
        if (Connection.AUTHTOKEN != null){
            userResponseCall.enqueue(new Callback<User>() {
                @SuppressLint("LongLogTag")
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()){
                        Connection.LOGGEDUSER = response.body();
                    } else {
                        Connection.Message(getApplicationContext(), "Error, try again: " + response.message());
                        Log.d("ERROR-MainActivity-getDates-onResponse", response.message());
                    }
                }

                @SuppressLint("LongLogTag")
                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Connection.Message(getApplicationContext(), t.getMessage());
                    Log.d("ERROR-MainActivity-getDates-onFailure", t.getMessage());
                }
            });
        }
    }
}