package com.camel.go4lunch.injection;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.camel.go4lunch.repositories.WorkmatesDataRepository;
import com.camel.go4lunch.ui.fragment.LoginFragmentViewModel;

import java.util.concurrent.Executor;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final WorkmatesDataRepository mWorkmatesDataRepository;
    private final Executor executor;

    public ViewModelFactory(WorkmatesDataRepository workmatesDataRepository,
                            Executor executor) {
        this.mWorkmatesDataRepository = workmatesDataRepository;
        this.executor = executor;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(LoginFragmentViewModel.class)){
            return (T) new LoginFragmentViewModel(mWorkmatesDataRepository, executor);
        } throw new IllegalArgumentException("Unknown ViewModel Class");
    }
}
