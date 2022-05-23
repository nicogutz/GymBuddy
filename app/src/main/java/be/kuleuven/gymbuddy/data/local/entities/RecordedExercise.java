package be.kuleuven.gymbuddy.data.local.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "recorded_exercise")
public class RecordedExercise {
    @PrimaryKey(autoGenerate = true)
    public int recordedExerciseID;

    @ColumnInfo(name = "public_exercise_identifier")
    public int publicExerciseIdentifier;

    @ColumnInfo(name = "public_exercise_name")
    public int publicExerciseName;

    @ColumnInfo(name = "date")
    public Date date;

    @ColumnInfo(name = "sets")
    public int sets;

    @ColumnInfo(name = "weight")
    public float weight;

    @ColumnInfo(name = "reps")
    public int reps;
}
