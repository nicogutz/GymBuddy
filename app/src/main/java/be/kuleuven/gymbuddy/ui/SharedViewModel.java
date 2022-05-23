package be.kuleuven.gymbuddy.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.Map;

import be.kuleuven.gymbuddy.data.PublicExerciseRepository;
import be.kuleuven.gymbuddy.data.RecordedExerciseRepository;
import be.kuleuven.gymbuddy.data.SavedRoutineRepository;
import be.kuleuven.gymbuddy.data.local.entities.RecordedExercise;
import be.kuleuven.gymbuddy.data.model.ExerciseValue;

public class SharedViewModel extends AndroidViewModel {
    private final RecordedExerciseRepository recordedExerciseRepository;
    private final SavedRoutineRepository savedRoutineRepository;
    private PublicExerciseRepository publicExerciseRepository;

    public SharedViewModel(@NonNull Application application) {
        super(application);

        publicExerciseRepository = new PublicExerciseRepository(application);
        recordedExerciseRepository = new RecordedExerciseRepository(application);
        savedRoutineRepository = new SavedRoutineRepository(application);
    }

    public LiveData<Map<String, List<ExerciseValue>>> getExercisesGroupedByMuscles() {
        return publicExerciseRepository.getExercisesGroupedByMuscles();
    }

    public LiveData<List<RecordedExercise>> getAllRecordedExercises(){
        return recordedExerciseRepository.getAllRecordedExercises();
    }
}
