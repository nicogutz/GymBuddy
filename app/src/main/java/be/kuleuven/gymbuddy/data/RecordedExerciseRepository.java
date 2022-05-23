package be.kuleuven.gymbuddy.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import be.kuleuven.gymbuddy.data.local.AppDatabase;
import be.kuleuven.gymbuddy.data.local.access.RecordedExerciseDAO;
import be.kuleuven.gymbuddy.data.local.entities.PublicExercise;
import be.kuleuven.gymbuddy.data.local.entities.RecordedExercise;
import be.kuleuven.gymbuddy.data.remote.PublicExerciseAPI;
import be.kuleuven.gymbuddy.data.remote.RetrofitInstance;

public class RecordedExerciseRepository {
    private RecordedExerciseDAO recordedExerciseDAO;
    private LiveData<List<RecordedExercise>> allRecordedExercises;

    public RecordedExerciseRepository(Application application) {
        recordedExerciseDAO = AppDatabase.getInstance(application).recordedExerciseDAO();
        allRecordedExercises = recordedExerciseDAO.getAll();
    }

    public LiveData<List<RecordedExercise>> getAllRecordedExercises() {
        return allRecordedExercises;
    }

    public void insertAllRecordedExercise(RecordedExercise recordedExercise){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            recordedExerciseDAO.insert(recordedExercise);
        });
    }

    public void removeRecordedExercise(RecordedExercise recordedExercise){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            recordedExerciseDAO.delete(recordedExercise);
        });
    }
}
