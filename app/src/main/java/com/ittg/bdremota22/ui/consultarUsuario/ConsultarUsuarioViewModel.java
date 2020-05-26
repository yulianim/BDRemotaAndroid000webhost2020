package com.ittg.bdremota22.ui.consultarUsuario;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ConsultarUsuarioViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ConsultarUsuarioViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tools fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}