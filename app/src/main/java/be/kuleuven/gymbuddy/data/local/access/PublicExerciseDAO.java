package be.kuleuven.gymbuddy.data.local.access;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import be.kuleuven.gymbuddy.data.local.entities.PublicExercise;


@Dao
public interface PublicExerciseDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PublicExercise publicExercise);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<PublicExercise> publicExercises);

    @Delete
    void delete(PublicExercise publicExercise);

    @Query("SELECT * FROM public_exercise pe ORDER BY identifier")
    public LiveData<List<PublicExercise>> getAllExercises();

    @Query("SELECT pe.identifier FROM public_exercise pe ORDER BY pe.identifier")
    public LiveData<List<String>> getExerciseCategories();

    @Query("SELECT * FROM public_exercise pe WHERE identifier = :identifier")
    public LiveData<PublicExercise> getExerciseByIdentifier(String identifier);

    @Query("DELETE FROM public_exercise")
    void deleteAll();
}
