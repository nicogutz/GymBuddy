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
import be.kuleuven.gymbuddy.data.model.ExerciseValue;

public class SharedViewModel extends AndroidViewModel {
    private LiveData<Map<String, List<ExerciseValue>>> exercisesGroupedByMuscles;

    public SharedViewModel(@NonNull Application application) {
        super(application);

        PublicExerciseRepository publicExerciseRepository =
                new PublicExerciseRepository(application);

        RecordedExerciseRepository recordedExerciseRepository =
                new RecordedExerciseRepository(application);

        SavedRoutineRepository savedRoutineRepository =
                new SavedRoutineRepository(application);

        exercisesGroupedByMuscles = publicExerciseRepository.getExercisesGroupedByMuscles();
    }

    public LiveData<Map<String, List<ExerciseValue>>> getExercisesGroupedByMuscles() {
        return exercisesGroupedByMuscles;
    }
}
