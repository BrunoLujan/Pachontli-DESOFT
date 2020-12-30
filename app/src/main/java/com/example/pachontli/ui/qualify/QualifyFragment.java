package com.example.pachontli.ui.qualify;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.pachontli.R;

public class QualifyFragment extends Fragment {

    private QualifyViewModel qualifyViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        qualifyViewModel =
                new ViewModelProvider(this).get(QualifyViewModel.class);
        View root = inflater.inflate(R.layout.fragment_qualify, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        qualifyViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}