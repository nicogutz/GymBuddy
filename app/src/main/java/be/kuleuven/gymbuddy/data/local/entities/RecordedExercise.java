package be.kuleuven.gymbuddy.data.local.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = SavedExercise.class,
        parentColumns = "savedExerciseID",
        childColumns = "saved_exercise_id",
        onDelete = ForeignKey.CASCADE)
},
        tableName = "recorded_exercise")
public class RecordedExercise {
    @PrimaryKey(autoGenerate = true)
    public int recordedExerciseID;

    @ColumnInfo(name = "saved_exercise_id", index = true)
    public int savedExerciseID;

    @ColumnInfo(name = "sets")
    public int sets;

    @ColumnInfo(name = "weight")
    public float weight;

    @ColumnInfo(name = "reps")
    public int reps;

}
