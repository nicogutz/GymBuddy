package be.kuleuven.gymbuddy.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import be.kuleuven.gymbuddy.data.local.AppDatabase;
import be.kuleuven.gymbuddy.data.local.access.SavedRoutinesDAO;
import be.kuleuven.gymbuddy.data.local.entities.SavedRoutine;

public class SavedRoutineRepository {
    private SavedRoutinesDAO savedRoutinesDAO;
    private LiveData<List<SavedRoutine>> allSavedRoutines;

    public SavedRoutineRepository(Application application) {
        savedRoutinesDAO = AppDatabase.getInstance(application).savedRoutinesDAO();
        allSavedRoutines = savedRoutinesDAO.getAll();
    }

    public LiveData<List<SavedRoutine>> getAllRecordedExercises() {
        return allSavedRoutines;
    }

    public void insertAllRecordedExercise(SavedRoutine savedRoutine) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            savedRoutinesDAO.insert(savedRoutine);
        });
    }

    public void removeRecordedExercise(SavedRoutine savedRoutine) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            savedRoutinesDAO.delete(savedRoutine);
        });
    }
}
