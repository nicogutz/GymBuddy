package be.kuleuven.gymbuddy.data.local.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = SavedExercise.class,
        parentColumns = "id",
        childColumns = "saved_routine_id",
        onDelete = ForeignKey.CASCADE), @ForeignKey(entity = RecordedRoutine.class,
        parentColumns = "id",
        childColumns = "public_exercise_id",
        onDelete = ForeignKey.CASCADE)})
public class RecordedExercise {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "recorded_routine_id")
    public int recordedRoutineID;

    @ColumnInfo(name = "saved_exercise_id")
    public int savedExerciseID;

    @ColumnInfo(name = "sets")
    public int sets;

    @ColumnInfo(name = "weight")
    public int weight;

    @ColumnInfo(name = "reps")
    public int reps;

}
