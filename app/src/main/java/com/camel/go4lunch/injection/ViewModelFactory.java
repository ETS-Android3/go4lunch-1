package com.camel.go4lunch.injection;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.camel.go4lunch.repositories.GooglePlaceRepository;
import com.camel.go4lunch.repositories.WorkmatesDataRepository;
import com.camel.go4lunch.ui.MainViewModel;
import com.camel.go4lunch.ui.SharedViewModel;
import com.camel.go4lunch.ui.fragment.LoginViewModel;
import com.camel.go4lunch.ui.fragment.MapViewFragmentViewModel;

import java.util.concurrent.Executor;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final WorkmatesDataRepository mWorkmatesDataRepository;
    private final GooglePlaceRepository mGooglePlaceRepository;
    private final Executor mExecutor;

    public ViewModelFactory(WorkmatesDataRepository workmatesDataRepository,
                            GooglePlaceRepository googlePlaceRepository,
                            Executor executor) {
        mWorkmatesDataRepository = workmatesDataRepository;
        mGooglePlaceRepository = googlePlaceRepository;
        mExecutor = executor;

    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(SharedViewModel.class)){
            return (T) new SharedViewModel();
        }
        else if(modelClass.isAssignableFrom(LoginViewModel.class)){
            return (T) new LoginViewModel(mWorkmatesDataRepository, mExecutor);
        }
        else if(modelClass.isAssignableFrom(MainViewModel.class)){
            return (T) new MainViewModel(mWorkmatesDataRepository);
        }
        else if(modelClass.isAssignableFrom(MapViewFragmentViewModel.class)){
            return (T) new MapViewFragmentViewModel(mGooglePlaceRepository, mExecutor);
        }
        throw new IllegalArgumentException("Unknown ViewModel Class");
    }
}
