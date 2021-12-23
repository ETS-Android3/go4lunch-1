package com.camel.go4lunch.ui.fragment.workmates;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.camel.go4lunch.R;
import com.camel.go4lunch.mappers.WorkmateToWorkmateListViewMapper;
import com.camel.go4lunch.models.Workmate;
import com.camel.go4lunch.repositories.WorkmatesRepository;
import com.camel.go4lunch.utils.SingleLiveEvent;
import com.camel.go4lunch.utils.liveEvent.LiveEvent;
import com.camel.go4lunch.utils.liveEvent.ShowSnackbarLiveEvent;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@HiltViewModel
public class WorkmatesViewModel extends ViewModel {
    private static final String TAG = "WorkmatesViewModel";

    private final WorkmatesRepository mWorkmatesRepository;

    private final CompositeDisposable mDisposable = new CompositeDisposable();
    private final MutableLiveData<List<Workmate>> mWorkmateListLiveData = new MutableLiveData<>();
    private final SingleLiveEvent<LiveEvent> mSingleLiveEvent = new SingleLiveEvent<>();

    @Inject
    public WorkmatesViewModel(WorkmatesRepository workmatesRepository){
        mWorkmatesRepository = workmatesRepository;
    }

    public void startObservers(){
        mDisposable.add(mWorkmatesRepository.observeWorkmates()
                .subscribeOn(Schedulers.computation())
                .map(new WorkmateToWorkmateListViewMapper())
                .subscribe(mWorkmateListLiveData::postValue,
                        throwable -> {
                            Log.e(TAG, "mWorkmatesRepository.observeWorkmates: ", throwable);
                            mSingleLiveEvent.postValue(new ShowSnackbarLiveEvent(R.string.error));
                        }));
    }

    public LiveData<List<Workmate>> observeWorkmateList(){
        return mWorkmateListLiveData;
    }

    public LiveData<LiveEvent> observeEvents(){
        return mSingleLiveEvent;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        clearDisposables();
    }

    public void clearDisposables(){
        mDisposable.clear();
    }
}
