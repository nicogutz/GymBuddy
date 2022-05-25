package be.kuleuven.gymbuddy.data.local.entities;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Calendar;
import java.util.Date;

/**
 * This table represents the exercises that a user records, together with their load. It will
 * be very useful to get the volume for the graphs.
 */
@Entity(tableName = "recorded_exercise", indices = {@Index(value = {"date", "name"}, unique = true)})
public class RecordedExercise {
    @PrimaryKey(autoGenerate = true)
    public int recordedExerciseID;

    @ColumnInfo(name = "name")
    public String name;

    /**
     * Weirdly enough dates are not accepted as types for a Room column, this means we need to set
     * up a converter and store the date as a long integer representing the date's UNIX Time
     * (milliseconds after Unix Time Zero).
     */
    @ColumnInfo(name = "date")
    public Date date;

    @ColumnInfo(name = "sets")
    public int sets;

    @ColumnInfo(name = "weight")
    public float weight;

    @ColumnInfo(name = "reps")
    public int reps;

    public int getRecordedExerciseID() {
        return recordedExerciseID;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public int getSets() {
        return sets;
    }

    public float getWeight() {
        return weight;
    }

    public int getReps() {
        return reps;
    }

    public RecordedExercise(String name,
                            Date date,
                            int sets,
                            float weight, int reps) {
        this.name = name;
        this.date = date;
        this.sets = sets;
        this.weight = weight;
        this.reps = reps;
    }
    public void setDate(int year, int month, int day){
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day, 0, 0, 0);
        date = cal.getTime();
    }

    @Ignore
    public RecordedExercise(String name,
                            int sets,
                            float weight, int reps) {
        this.name = name;
        this.sets = sets;
        this.weight = weight;
        this.reps = reps;
    }

    public String getNameFull() {
        return name + " Sets: " + sets + " Reps: " + reps + " Weight " + weight;
    }
}
