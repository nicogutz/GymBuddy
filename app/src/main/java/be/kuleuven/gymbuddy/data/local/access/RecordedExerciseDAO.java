package be.kuleuven.gymbuddy.data.local.access;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import be.kuleuven.gymbuddy.data.local.entities.RecordedExercise;
import be.kuleuven.gymbuddy.data.local.entities.RecordedRoutine;


@Dao
public interface RecordedExerciseDAO {
    @Insert
    void insert(RecordedExercise recordedExercise);

    @Insert
    void insertAll(RecordedExercise... recordedExercises);

    @Delete
    void delete(RecordedExercise recordedExercise);

    @Query("SELECT * FROM recorded_exercise")
    List<RecordedExercise> getAll();
}
