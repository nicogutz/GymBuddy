package be.kuleuven.gymbuddy.data.local.entities;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
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

    @ColumnInfo(name = "public_exercise_identifier")
    public int publicExerciseIdentifier;

    @ColumnInfo(name = "public_exercise_name")
    public int publicExerciseName;

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

    public int getPublicExerciseIdentifier() {
        return publicExerciseIdentifier;
    }

    public int getPublicExerciseName() {
        return publicExerciseName;
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
}
