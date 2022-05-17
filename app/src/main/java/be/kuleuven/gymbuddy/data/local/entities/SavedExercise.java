package be.kuleuven.gymbuddy.data.local.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(
        entity = SavedRoutine.class,
        parentColumns = "savedRoutineID",
        childColumns = "saved_routine_id",
        onDelete = ForeignKey.CASCADE),

        @ForeignKey(
        entity = PublicExercise.class,
        parentColumns = "publicExerciseID",
        childColumns = "public_exercise_id",
        onDelete = ForeignKey.CASCADE)},

        tableName = "saved_exercise")
public class SavedExercise {
    @PrimaryKey(autoGenerate = true)
    public int savedExerciseID;

    @ColumnInfo(name = "saved_routine_id", index = true)
    public int savedRoutineID;

    @ColumnInfo(name = "public_exercise_id", index = true)
    public int publicExerciseID;
}
