package com.example.pachontli;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.example.pachontli.adapters.PetDateRvAdapter;
import com.example.pachontli.adapters.PetRvAdapter;
import com.example.pachontli.adapters.VeterinarianDateRvAdapter;
import com.example.pachontli.models.Pet;
import com.example.pachontli.models.Veterinarian;
import com.example.pachontli.requests.RegisterDateRequest;
import com.example.pachontli.requests.RegisterPetRequest;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterDateActivity extends AppCompatActivity {

    RecyclerView rvPets, rvVeterinarians;
    PetDateRvAdapter petAdapter;
    VeterinarianDateRvAdapter veterinarianAdapter;

    List<Pet> petList;
    List<Veterinarian> veterinarianList;

    Button btnSelectDay, btnSelectHour, btnRegisterDate;

    int idPetSelected = -1;
    int idVeterinarianSelected = -1;
    java.sql.Date dateSelected = new Date(-1,-1,-1);
    Time timeSelected = new Time(-1,-1,-1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_date);

        rvPets = (RecyclerView) findViewById(R.id.rvPetsRegisterDateActivity);
        rvVeterinarians = (RecyclerView) findViewById(R.id.rvVeterinariansRegisterDateActivity);

        btnSelectDay = (Button) findViewById(R.id.btnSelectDayRegisterDateActivity);
        btnSelectHour = (Button) findViewById(R.id.btnSelectHourRegisterDateActivity);
        btnRegisterDate = (Button) findViewById(R.id.btnRegisterDateRegisterDateActivity);

        btnSelectDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalendar(v);
            }
        });

        btnSelectHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalendarHour(v);
            }
        });

        btnRegisterDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateEntry();
            }
        });

        getPets();
        getVeterinarians();

    }

    public void getPets() {
        Call<List<Pet>> call = Connection.CONNECTION.getPets(Connection.AUTHTOKEN);
        call.enqueue(new Callback<List<Pet>>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(Call<List<Pet>> call, Response<List<Pet>> response) {
                if (response.isSuccessful()) {
                    petList = response.body();
                    buildPetRV();
                } else {
                    Connection.Message(getApplicationContext(), "Error, try again: " + response.message());
                    Log.d("ERROR-RegisterDateActivity-getPets-onResponse", response.message());
                }
            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(Call<List<Pet>> call, Throwable t) {
                Connection.Message(getApplicationContext(), t.getMessage());
                Log.d("ERROR-RegisterDateActivity-getPets-onFailure", t.getMessage());
            }
        });
    }

    public void getVeterinarians() {
        Call<List<Veterinarian>> call = Connection.CONNECTION.getVeterinarians(Connection.AUTHTOKEN);
        call.enqueue(new Callback<List<Veterinarian>>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(Call<List<Veterinarian>> call, Response<List<Veterinarian>> response) {
                if (response.isSuccessful()) {
                    veterinarianList = response.body();
                    buildVeterinarianRV();
                } else {
                    Connection.Message(getApplicationContext(), "Error, try again: " + response.message());
                    Log.d("ERROR-RegisterDateActivity-getVeterinarians-onResponse", response.message());
                }
            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(Call<List<Veterinarian>> call, Throwable t) {
                Connection.Message(getApplicationContext(), t.getMessage());
                Log.d("ERROR-RegisterDateActivity-getVeterinarians-onFailure", t.getMessage());
            }
        });
    }

    public void buildPetRV() {
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rvPets.setLayoutManager(llm);

        petAdapter = new PetDateRvAdapter(petList);

        petAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idPetSelected = petList.get(rvPets.getChildAdapterPosition(v)).getId();
                Connection.Message(getApplicationContext(),
                        petList.get(rvPets.getChildAdapterPosition(v)).getNombre() + " has been selected");
            }
        });

        rvPets.setAdapter(petAdapter);
    }

    public void buildVeterinarianRV() {
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rvVeterinarians.setLayoutManager(llm);

        veterinarianAdapter = new VeterinarianDateRvAdapter(veterinarianList);

        veterinarianAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idVeterinarianSelected = veterinarianList.get(rvVeterinarians.getChildAdapterPosition(v)).getId();
                Connection.Message(getApplicationContext(),
                        veterinarianList.get(rvVeterinarians.getChildAdapterPosition(v)).getNombre() + " has been selected");
            }
        });

        rvVeterinarians.setAdapter(veterinarianAdapter);
    }

    public void openCalendar(View view) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(RegisterDateActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dateSelected.setYear(year);
                dateSelected.setMonth((month+1));
                dateSelected.setDate(dayOfMonth);
                Connection.Message(getApplicationContext(), (dateSelected.getDay()) + "/" +
                        dateSelected.getMonth() + "/" + dateSelected.getYear());
            }
        }, year, month, day);
        dpd.show();
    }

    public void openCalendarHour(View view) {
        Calendar calendarHour = Calendar.getInstance();
        int hour = calendarHour.get(Calendar.HOUR_OF_DAY);
        int minutes = calendarHour.get(Calendar.MINUTE);

        TimePickerDialog tpd = new TimePickerDialog(RegisterDateActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                timeSelected.setHours(hourOfDay);
                timeSelected.setMinutes(minute);
                Connection.Message(getApplicationContext(), timeSelected.getHours() + ":" +
                        timeSelected.getMinutes() + ": 00");
            }
        }, hour, minutes,true);
        tpd.show();
    }

    public void validateEntry() {
        if (dateSelected.getDate() != -1) {
            if (timeSelected.getHours() != -1) {
                if(idPetSelected != -1) {
                    if(idVeterinarianSelected != -1) {
                        registerDate();
                    } else {
                        Connection.Message(getApplicationContext(), "Select a veterinarian");
                    }
                } else {
                    Connection.Message(getApplicationContext(), "Select a pet");
                }
            } else {
                Connection.Message(getApplicationContext(), "Select hour");
            }
        } else {
           Connection.Message(getApplicationContext(), "Select a day");
        }
    }

    public void registerDate() {
        RegisterDateRequest date = new RegisterDateRequest();
        date.setFecha(dateSelected);
        date.setHora(timeSelected);

        Call<ResponseBody> call = Connection.CONNECTION.registerDate(Connection.AUTHTOKEN, date,
                idVeterinarianSelected, idPetSelected);
        call.enqueue(new Callback<ResponseBody>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    Connection.Message(getApplicationContext(), "Date has been registered");
                    Intent intent = new Intent(RegisterDateActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Connection.Message(getApplicationContext(), "Error, try again: " + response.message());
                    Log.d("ERROR-RegisterDateActivity-registerDate-onResponse", response.message());
                }
            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Connection.Message(getApplicationContext(), t.getMessage());
                Log.d("ERROR-RegisterDateActivity-registerDate-onFailure", t.getMessage());
            }
        });
    }
}