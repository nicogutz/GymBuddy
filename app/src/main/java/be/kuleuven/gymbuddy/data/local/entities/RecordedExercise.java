package be.kuleuven.gymbuddy.data.local.entities;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

/**
 * This table represents the exercises that a user records, together with their load. It will
 * be very useful to get the volume for the graphs.
 */
@Entity(tableName = "recorded_exercise")
public class RecordedExercise {
    @PrimaryKey(autoGenerate = true)
    public int recordedExerciseID;

    @ColumnInfo(name = "muscle_group")
    public String muscleGroup;

    @ColumnInfo(name = "internal_name")
    public String internalName;

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

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public String getInternalName() {
        return internalName;
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

    public RecordedExercise(String muscleGroup,
                            String internalName,
                            String name,
                            Date date,
                            int sets,
                            float weight, int reps) {
        this.muscleGroup = muscleGroup;
        this.internalName = internalName;
        this.name = name;
        this.date = date;
        this.sets = sets;
        this.weight = weight;
        this.reps = reps;
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
        return name + ""
    }
}
