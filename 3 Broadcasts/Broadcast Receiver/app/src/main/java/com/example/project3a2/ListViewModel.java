package com.example.project3a2;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ListViewModel extends ViewModel {

    private final MutableLiveData<Integer> aSelectedItem = new MutableLiveData<>();

    public void selectedItem(Integer item) { aSelectedItem.setValue(item);}
    public LiveData<Integer> getSelectedItem() { return aSelectedItem;}
}
