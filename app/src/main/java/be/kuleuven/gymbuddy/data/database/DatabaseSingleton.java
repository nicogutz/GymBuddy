package be.kuleuven.gymbuddy.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import be.kuleuven.gymbuddy.data.database.entities.*;

@Database(entities = {User.class, }, version = 1)
public abstract class DatabaseSingleton extends RoomDatabase {
}
