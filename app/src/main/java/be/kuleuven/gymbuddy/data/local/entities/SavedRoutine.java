package be.kuleuven.gymbuddy.data.local.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.Map;

@Entity(tableName = "saved_routine")
public class SavedRoutine {
    @PrimaryKey(autoGenerate = true)
    public int savedRoutineID;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "saved_exercises")
    public ArrayList<String[]> savedExercises;
}
