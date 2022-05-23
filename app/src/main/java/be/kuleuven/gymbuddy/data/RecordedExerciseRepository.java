package be.kuleuven.gymbuddy.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.Map;

import be.kuleuven.gymbuddy.data.local.AppDatabase;
import be.kuleuven.gymbuddy.data.local.access.RecordedExerciseDAO;
import be.kuleuven.gymbuddy.data.local.entities.RecordedExercise;
import be.kuleuven.gymbuddy.data.model.RecordedExerciseValue;

/**
 * Same thing as with the Public Exercises Repository but a bit simpler,
 * the list of recorded exercises eventually has to be casted into MutableLiveData
 * since we need to delete and add new recorded exercises.
 */
public class RecordedExerciseRepository {
    private final RecordedExerciseDAO recordedExerciseDAO;

    public RecordedExerciseRepository(Application application) {
        recordedExerciseDAO = AppDatabase.getInstance(application).recordedExerciseDAO();
    }

    public LiveData<List<RecordedExercise>> getAllRecordedExercises() {
        return recordedExerciseDAO.getAll();
    }

    public LiveData<Map<String, List<RecordedExerciseValue>>> getAllRecordedExerciseValues() {
        return recordedExerciseDAO.getMapWithDates();
    }


    public void insertAllRecordedExercise(List<RecordedExercise> recordedExercise) {
        AppDatabase.databaseWriteExecutor.execute(
                () -> recordedExerciseDAO.insertAll(recordedExercise));
    }

    public void removeRecordedExercise(RecordedExercise recordedExercise) {
        AppDatabase.databaseWriteExecutor.execute(
                () -> recordedExerciseDAO.delete(recordedExercise));
    }
}
