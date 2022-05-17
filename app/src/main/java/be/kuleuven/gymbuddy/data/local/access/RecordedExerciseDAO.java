package be.kuleuven.gymbuddy.data.local.access;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import be.kuleuven.gymbuddy.data.local.entities.RecordedRoutine;


@Dao
public interface RecordedExerciseDAO {
    @Insert
    void insert(RecordedRoutine recordedRoutine);

    @Insert
    void insertAll(RecordedRoutine... recordedRoutines);

    @Delete
    void delete(RecordedRoutine recordedRoutine);

    @Query("SELECT * FROM recorded_routine")
    List<RecordedRoutine> getAll();
}
