package be.kuleuven.gymbuddy.data.local.access;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.MapInfo;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.Date;
import java.util.List;
import java.util.Map;

import be.kuleuven.gymbuddy.data.local.entities.RecordedExercise;
import be.kuleuven.gymbuddy.data.model.RecordedExerciseValue;


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

    @Query("SELECT * FROM recorded_exercise ORDER BY name, date")
    @MapInfo(keyColumn = "name")
    LiveData<Map<String, List<RecordedExercise>>> getMapWithDates();

    @Query("SELECT * FROM recorded_exercise ORDER BY date, name")
    @MapInfo(keyColumn = "date")
    LiveData<Map<Date, List<RecordedExercise>>> getAllByDates();
}
