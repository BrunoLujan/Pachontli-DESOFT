package com.example.pachontli.ui.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.pachontli.PetRegisterActivity;
import com.example.pachontli.R;
import com.example.pachontli.RegisterDateActivity;

public class CalendarFragment extends Fragment {

    View viewFragment;
    Button btnRegister;

    private CalendarViewModel calendarViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewFragment = inflater.inflate(R.layout.fragment_calendar, container, false);
        btnRegister = viewFragment.findViewById(R.id.btnRegisterCalendarFragment);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RegisterDateActivity.class);
                startActivity(intent);
            }
        });

        return viewFragment;
    }
}