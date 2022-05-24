package be.kuleuven.gymbuddy.data.local.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

/**
 * A simple saved routine, it just holds a list of string arrays, we need both the exercise name
 * and its identifier when we are storing exercises. One to display to the user and the other one
 * to locate the exercise.
 */
@Entity(tableName = "saved_routine")
public class SavedRoutine {
    @PrimaryKey(autoGenerate = true)
    public int savedRoutineID;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "saved_exercises")
    public ArrayList<String[]> savedExercises;
}
