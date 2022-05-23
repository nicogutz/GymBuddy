package be.kuleuven.gymbuddy.data.local.access;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.MapInfo;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
import java.util.Map;

import be.kuleuven.gymbuddy.data.local.entities.PublicExercise;
import be.kuleuven.gymbuddy.data.model.ExerciseValue;

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

    @Query("SELECT DISTINCT muscle_group, publicExerciseID, internal_name, name FROM " +
            "public_exercise ORDER BY muscle_group")
    @MapInfo(keyColumn = "muscle_group")
    public LiveData<Map<String, List<ExerciseValue>>> getExerciseCategories();

    @Query("SELECT * FROM public_exercise pe WHERE pe.publicExerciseID = :id")
    public LiveData<PublicExercise> getExerciseByIdentifier(int id);

    @Query("DELETE FROM public_exercise")
    void deleteAll();
}
