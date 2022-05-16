package be.kuleuven.gymbuddy.data.local;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.room.Room;

public class AppDatabaseSingleton {
    @SuppressLint("StaticFieldLeak")
    private static AppDatabaseSingleton instance;
    @SuppressLint("StaticFieldLeak")
    private static Context ctx;
    private AppDatabase appDatabase;

    private AppDatabaseSingleton(Context context) {
        ctx = context;
        appDatabase = getAppDatabase();
    }

    public static synchronized AppDatabaseSingleton getInstance(Context context) {
        if (instance == null) {
            instance = new AppDatabaseSingleton(context);
        }
        return instance;
    }

    public AppDatabase getAppDatabase() {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(ctx.getApplicationContext(), AppDatabase.class,
                    "main-db").build();
        }
        return appDatabase;
    }
}