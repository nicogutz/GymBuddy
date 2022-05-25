package be.kuleuven.gymbuddy.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import be.kuleuven.gymbuddy.data.local.AppDatabase;
import be.kuleuven.gymbuddy.data.local.access.SavedRoutinesDAO;
import be.kuleuven.gymbuddy.data.local.entities.SavedRoutine;

public class SavedRoutineRepository {
    private final SavedRoutinesDAO savedRoutinesDAO;
    private final LiveData<List<SavedRoutine>> allSavedRoutines;

    public SavedRoutineRepository(Application application) {
        savedRoutinesDAO = AppDatabase.getInstance(application).savedRoutinesDAO();
        allSavedRoutines = savedRoutinesDAO.getAll();
    }

    public static void insertSavedRoutine(SavedRoutine savedRoutine, Application app) {
        AppDatabase.databaseWriteExecutor.execute(
                () -> AppDatabase.getInstance(app).savedRoutinesDAO().insert(savedRoutine));
    }

    public LiveData<List<SavedRoutine>> getAllSavedRoutines() {
        return allSavedRoutines;
    }

    public void removeRecordedExercise(SavedRoutine savedRoutine) {
        AppDatabase.databaseWriteExecutor.execute(() -> savedRoutinesDAO.delete(savedRoutine));
    }
}
