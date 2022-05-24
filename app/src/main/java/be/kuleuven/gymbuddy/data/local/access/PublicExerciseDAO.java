package be.kuleuven.gymbuddy.data.local.access;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import be.kuleuven.gymbuddy.data.local.entities.PublicExercise;

/**
 * A DAO is a Data Access Object, pretty much an interface between SQLite and Java, it works in
 * conjunction with the type converters to output the right data. LiveData is necessary because we
 * need to know when the query has changed EG. a new row has been added. And update the fragments
 * accordingly.
 */
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

    //TODO: Change when new column is available.
    @Query("SELECT pe.identifier FROM public_exercise pe ORDER BY pe.identifier")
    public LiveData<List<String>> getExerciseCategories();

    @Query("SELECT * FROM public_exercise pe WHERE identifier = :identifier")
    public LiveData<PublicExercise> getExerciseByIdentifier(String identifier);

    @Query("DELETE FROM public_exercise")
    void deleteAll();
}
