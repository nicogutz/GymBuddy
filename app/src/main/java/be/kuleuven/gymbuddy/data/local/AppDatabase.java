package be.kuleuven.gymbuddy.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

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
}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SavedRoutinesDAO savedRoutinesDAO();
}


