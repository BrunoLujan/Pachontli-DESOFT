package com.example.pachontli.ui.qualify;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class QualifyViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public QualifyViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}