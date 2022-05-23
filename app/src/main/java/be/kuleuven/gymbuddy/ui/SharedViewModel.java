package be.kuleuven.gymbuddy.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import be.kuleuven.gymbuddy.data.PublicExerciseRepository;
import be.kuleuven.gymbuddy.data.RecordedExerciseRepository;
import be.kuleuven.gymbuddy.data.SavedRoutineRepository;
import be.kuleuven.gymbuddy.data.local.entities.PublicExercise;

public class SharedViewModel extends AndroidViewModel {
    private PublicExerciseRepository publicExerciseRepository;
    private RecordedExerciseRepository recordedExerciseRepository;
    private SavedRoutineRepository savedRoutineRepository;

    public SharedViewModel(@NonNull Application application) {
        super(application);

        publicExerciseRepository = new PublicExerciseRepository(application);
        recordedExerciseRepository = new RecordedExerciseRepository(application);
        savedRoutineRepository = new SavedRoutineRepository(application);

    }
}
