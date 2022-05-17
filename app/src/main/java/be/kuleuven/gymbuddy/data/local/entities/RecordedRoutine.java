package be.kuleuven.gymbuddy.data.local.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = SavedRoutine.class,
                parentColumns = "id",
                childColumns = "saved_routine_id",
                onDelete = ForeignKey.CASCADE),
        @ForeignKey(
                entity = RecordedExercise.class,
                parentColumns = "id",
                childColumns = "recorded_exercise_id",
                onDelete = ForeignKey.CASCADE)
},
        tableName = "recorded_routine"
)
public class RecordedRoutine {
    @PrimaryKey(autoGenerate = true)
    public int recordedRoutineID;

    @ColumnInfo(name = "saved_routine_id")
    public int savedRoutineID;

    @ColumnInfo(name = "recorded_exercise_id")
    public int recordedExerciseID;

}
