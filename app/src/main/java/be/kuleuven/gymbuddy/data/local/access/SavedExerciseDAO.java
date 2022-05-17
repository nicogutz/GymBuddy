package be.kuleuven.gymbuddy.data.local.access;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import be.kuleuven.gymbuddy.data.local.entities.SavedExercise;


@Dao
public interface SavedExerciseDAO {
    @Insert
    void insert(SavedExercise savedExercise);

    @Insert
    void insertAll(SavedExercise... savedExercises);

    @Delete
    void delete(SavedExercise savedExercise);

    @Query("SELECT * FROM saved_exercise")
    List<SavedExercise> getAll();

}
