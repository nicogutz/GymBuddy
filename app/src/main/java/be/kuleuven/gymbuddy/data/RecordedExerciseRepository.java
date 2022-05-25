package be.kuleuven.gymbuddy.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

    public LiveData<Map<String, List<RecordedExercise>>> getAllRecordedExerciseValues() {
        return recordedExerciseDAO.getMapWithDates();
    }


    public static void insertAllRecordedExercise(List<RecordedExercise> recordedExercise, Application app) {
        AppDatabase.databaseWriteExecutor.execute(
                () -> AppDatabase.getInstance(app).recordedExerciseDAO().insertAll(recordedExercise));
    }

    public void removeRecordedExercise(RecordedExercise recordedExercise) {
        AppDatabase.databaseWriteExecutor.execute(
                () -> recordedExerciseDAO.delete(recordedExercise));
    }

    public LiveData<Map<Date, List<RecordedExercise>>> getRecordedExercisesByDate() {
        return recordedExerciseDAO.getAllByDates();
    }
}
