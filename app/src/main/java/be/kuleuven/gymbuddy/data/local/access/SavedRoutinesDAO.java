package be.kuleuven.gymbuddy.data.local.access;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
import java.util.Map;

import be.kuleuven.gymbuddy.data.local.entities.SavedExercise;
import be.kuleuven.gymbuddy.data.local.entities.SavedRoutine;


@Dao
public interface SavedRoutinesDAO {
    @Insert
    void insert(SavedRoutine SavedRoutine);

    @Insert
    void insertAll(SavedRoutine... SavedRoutines);

    @Delete
    void delete(SavedRoutine SavedRoutine);

    @Query("SELECT * FROM saved_routine")
    List<SavedRoutine> getAll();

    @Query("SELECT * FROM saved_routine sr " +
            "JOIN saved_exercise se " +
            "ON sr.savedRoutineID = se.saved_routine_id")
    Map<SavedRoutine, List<SavedExercise>> getAllSavedRoutinesAndExercises();

}
