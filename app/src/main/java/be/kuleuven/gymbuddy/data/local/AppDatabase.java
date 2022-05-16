package be.kuleuven.gymbuddy.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import be.kuleuven.gymbuddy.data.local.access.DoneExerciseDAO;
import be.kuleuven.gymbuddy.data.local.access.HealthDAO;
import be.kuleuven.gymbuddy.data.local.access.PublicExercisesDAO;
import be.kuleuven.gymbuddy.data.local.access.SavedRoutinesDAO;
import be.kuleuven.gymbuddy.data.model.DoneExercise;
import be.kuleuven.gymbuddy.data.model.Health;
import be.kuleuven.gymbuddy.data.model.PublicExercises;
import be.kuleuven.gymbuddy.data.model.SavedRoutines;

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


