package be.kuleuven.gymbuddy.data.local.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = SavedRoutine.class,
        parentColumns = "id",
        childColumns = "saved_routine_id",
        onDelete = ForeignKey.CASCADE)})
public class RecordedRoutine {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "saved_routine_id")
    public int savedRoutineID;

}
