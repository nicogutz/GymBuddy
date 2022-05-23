package be.kuleuven.gymbuddy.data.local.access;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import be.kuleuven.gymbuddy.data.local.entities.RecordedExercise;


@Dao
public interface RecordedExerciseDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(RecordedExercise recordedExercise);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<RecordedExercise> recordedExercises);

    @Delete
    void delete(RecordedExercise recordedExercise);

    @Query("SELECT * FROM recorded_exercise")
    LiveData<List<RecordedExercise>> getAll();
}
