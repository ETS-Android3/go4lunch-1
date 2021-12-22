package com.camel.go4lunch.injection;

import com.camel.go4lunch.repositories.PlacesDataRepository;
import com.camel.go4lunch.repositories.WorkmatesDataRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Injection {

    private static Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    private static WorkmatesDataRepository provideWorkmateDataRepository() {
        return new WorkmatesDataRepository();
    }

    private static PlacesDataRepository provideGooglePlaceRepository() {
        return new PlacesDataRepository();
    }

    public static ViewModelFactory provideViewModelFactory(){
        WorkmatesDataRepository workmatesDataRepository = provideWorkmateDataRepository();
        PlacesDataRepository placesDataRepository = provideGooglePlaceRepository();
        Executor executor = provideExecutor();
        return new ViewModelFactory(workmatesDataRepository, placesDataRepository, executor);
    }

}
