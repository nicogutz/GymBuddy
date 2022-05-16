package be.kuleuven.gymbuddy.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import be.kuleuven.gymbuddy.data.database.access.*;
import be.kuleuven.gymbuddy.data.database.entities.*;

@Database(entities = {
        Health.class,
        PublicExercises.class,
        SavedRoutines.class,
        DoneExercise.class }, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract HealthDAO healthDAO();

    public abstract SavedRoutinesDAO savedRoutinesDAO();

    public abstract DoneExerciseDAO doneExerciseDAO();

    public abstract PublicExercisesDAO publicExercisesDAO();
}


