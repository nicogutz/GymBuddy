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

@Database(entities = {
        PublicExercise.class,
        SavedRoutine.class,
        RecordedExercise.class}, version = 2, exportSchema = true)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract PublicExerciseDAO publicExerciseDAO();
    public abstract RecordedExerciseDAO recordedExerciseDAO();
    public abstract SavedRoutinesDAO savedRoutinesDAO();

    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class ,
                            "main-db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}


