package com.camel.go4lunch.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.camel.go4lunch.models.Workmate;
import com.camel.go4lunch.repositories.WorkmatesDataRepository;


public class MainViewModel extends ViewModel {

    private final WorkmatesDataRepository mWorkmatesDataRepository;

    public MainViewModel(WorkmatesDataRepository workmatesDataRepository) {
        this.mWorkmatesDataRepository = workmatesDataRepository;
    }

    public LiveData<Workmate> getWorkmateWithId(String uid){
        return mWorkmatesDataRepository.getWorkmateWithId(uid);
    }

}
