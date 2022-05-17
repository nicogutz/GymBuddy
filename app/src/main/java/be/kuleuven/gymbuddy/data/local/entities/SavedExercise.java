package be.kuleuven.gymbuddy.data.local.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = SavedRoutine.class,
        parentColumns = "id",
        childColumns = "saved_routine_id",
        onDelete = ForeignKey.CASCADE), @ForeignKey(entity = PublicExercise.class,
        parentColumns = "id",
        childColumns = "public_exercise_id",
        onDelete = ForeignKey.CASCADE)})
public class SavedExercise {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "saved_routine_id")
    public int savedRoutineID;

    @ColumnInfo(name = "public_exercise_id")
    public int publicExerciseID;
}
