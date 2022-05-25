package be.kuleuven.gymbuddy.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import be.kuleuven.gymbuddy.data.local.AppDatabase;
import be.kuleuven.gymbuddy.data.local.access.SavedRoutinesDAO;
import be.kuleuven.gymbuddy.data.local.entities.SavedRoutine;

public class SavedRoutineRepository {
    private final SavedRoutinesDAO savedRoutinesDAO;

    public SavedRoutineRepository(Application application) {
        savedRoutinesDAO = AppDatabase.getInstance(application).savedRoutinesDAO();
    }

    public static void insertSavedRoutine(SavedRoutine savedRoutine, Application app) {
        AppDatabase.databaseWriteExecutor.execute(
                () -> AppDatabase.getInstance(app).savedRoutinesDAO().insert(savedRoutine));
    }

    public void setExerciseList(List<String> list, Integer id) {
        AppDatabase.databaseWriteExecutor.execute(
                () -> {
                    SavedRoutine savedRoutine = savedRoutinesDAO.getRoutineById(id);
                    savedRoutine.savedExercises.clear();
                    savedRoutine.savedExercises.addAll(list);
                    savedRoutinesDAO.insert(savedRoutine);}
        );
    }

    public LiveData<List<SavedRoutine>> getAllSavedRoutines() {
        return savedRoutinesDAO.getAll();
    }

    public void removeRecordedExercise(SavedRoutine savedRoutine) {
        AppDatabase.databaseWriteExecutor.execute(() -> savedRoutinesDAO.delete(savedRoutine));
    }

    public LiveData<List<String>> getSavedRoutineByID(Integer savedRoutineID) {
    return savedRoutinesDAO.getByID(savedRoutineID);
    }
}
