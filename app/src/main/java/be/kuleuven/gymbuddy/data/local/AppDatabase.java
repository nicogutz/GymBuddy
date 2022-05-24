package be.kuleuven.gymbuddy.data.local;

import static be.kuleuven.gymbuddy.common.Constants.NUMBER_OF_THREADS;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import be.kuleuven.gymbuddy.data.local.access.Converters;
import be.kuleuven.gymbuddy.data.local.access.PublicExerciseDAO;
import be.kuleuven.gymbuddy.data.local.access.RecordedExerciseDAO;
import be.kuleuven.gymbuddy.data.local.access.SavedRoutinesDAO;
import be.kuleuven.gymbuddy.data.local.entities.PublicExercise;
import be.kuleuven.gymbuddy.data.local.entities.RecordedExercise;
import be.kuleuven.gymbuddy.data.local.entities.SavedRoutine;


/**
 * This class defines both the Database and the instance of the Room database.
 * The decorator sets the entities that the database will hold, it also holds the DAOs for
 * convenience.
 * TODO: Remove export schema for production release.
 */
@Database(entities = {
        PublicExercise.class,
        SavedRoutine.class,
        RecordedExercise.class}, version = 2, exportSchema = true)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    /**
     * This way is much better than making AsyncCalls, it makes the database instance handle
     * the request queue and we simply need to run something like:
     * databaseWriteExecutor.execute(() -> publicExerciseDAO.insertAll(publicExercises)
     * to make sure it makes the requests outside the main thread. The constant can be changed to
     * handle more simultaneous requests
     */
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static AppDatabase instance;

    /**
     * The singleton pattern helps us make sure that there is only one instance of the database
     * running at all times. This makes it super hard to test though so we'd need to make Android
     * tests and run them in the Emulator #NoThanks. The upside is that the default emulator in
     * Android Studio has a database inspector, so we can see the database state at runtime
     * without too much hassle. Good thing I could never install Genymotion.
     *
     * @param context the application it is running.
     * @return an instance of AppDatabase.
     */
    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "main-db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract PublicExerciseDAO publicExerciseDAO();

    public abstract RecordedExerciseDAO recordedExerciseDAO();

    public abstract SavedRoutinesDAO savedRoutinesDAO();

}


