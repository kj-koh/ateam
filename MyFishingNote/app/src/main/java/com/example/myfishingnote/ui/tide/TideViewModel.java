package com.example.myfishingnote.ui.tide;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TideViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TideViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tide fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}