package be.kuleuven.gymbuddy.data.local.access;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.MapInfo;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
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

    /**
     * This is probably the coolest thing about Room, you can map queries to objects very easily.
     * The only requirement is that the targets have the same names and types as the query results.
     * @return LiveData map with the muscle group as key, and all exercises in that group as a list.
     */
    @Query("SELECT DISTINCT muscle_group, publicExerciseID, internal_name, name FROM " +
            "public_exercise ORDER BY muscle_group")
    @MapInfo(keyColumn = "muscle_group")
    public LiveData<Map<String, List<ExerciseValue>>> getExercisesGroupedByMuscles();

    @Query("SELECT * FROM public_exercise pe WHERE pe.publicExerciseID = :id")
    public LiveData<PublicExercise> getExerciseByID(int id);

    @Query("DELETE FROM public_exercise")
    void deleteAll();
}
