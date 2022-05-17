package be.kuleuven.gymbuddy.data.local.access;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import be.kuleuven.gymbuddy.data.local.entities.PublicExercise;
import be.kuleuven.gymbuddy.data.local.entities.RecordedRoutine;


@Dao
public interface PublicExerciseDAO {
    @Insert
    void insert(PublicExercise publicExercise);

    @Insert
    void insertAll(PublicExercise... publicExercises);

    @Delete
    void delete(PublicExercise publicExercise);

    @Query("SELECT * FROM public_exercise")
    List<PublicExercise> getAll();
}
