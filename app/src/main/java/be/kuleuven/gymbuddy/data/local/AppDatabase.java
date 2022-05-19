package be.kuleuven.gymbuddy.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import be.kuleuven.gymbuddy.data.local.access.Converters;
import be.kuleuven.gymbuddy.data.local.access.PublicExerciseDAO;
import be.kuleuven.gymbuddy.data.local.access.RecordedExerciseDAO;
import be.kuleuven.gymbuddy.data.local.access.RecordedRoutineDAO;
import be.kuleuven.gymbuddy.data.local.access.SavedExerciseDAO;
import be.kuleuven.gymbuddy.data.local.access.SavedRoutinesDAO;
import be.kuleuven.gymbuddy.data.local.entities.PublicExercise;
import be.kuleuven.gymbuddy.data.local.entities.RecordedExercise;
import be.kuleuven.gymbuddy.data.local.entities.RecordedRoutine;
import be.kuleuven.gymbuddy.data.local.entities.SavedExercise;
import be.kuleuven.gymbuddy.data.local.entities.SavedRoutine;

@Database(entities = {
        PublicExercise.class,
        SavedRoutine.class,
        SavedExercise.class,
        RecordedRoutine.class,
        RecordedExercise.class
}, version = 1, exportSchema = true)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract PublicExerciseDAO getPublicExerciseDAO();
    public abstract RecordedExerciseDAO getRecordedExerciseDAO();
    public abstract RecordedRoutineDAO getRecordedRoutineDAO();
    public abstract SavedExerciseDAO getSavedExerciseDAO();
    public abstract SavedRoutinesDAO getSavedRoutinesDAO();
}


