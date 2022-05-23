package be.kuleuven.gymbuddy.data.local.access;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
import java.util.Map;

import be.kuleuven.gymbuddy.data.local.entities.SavedRoutine;


@Dao
public interface SavedRoutinesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SavedRoutine savedRoutine);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<SavedRoutine> savedRoutines);

    @Delete
    void delete(SavedRoutine savedRoutine);

    @Query("SELECT * FROM saved_routine")
    public LiveData<List<SavedRoutine>> getAll();

    @Query("SELECT * FROM saved_routine WHERE name = :name")
    public LiveData<SavedRoutine> getByName(String name);

}
