package be.kuleuven.gymbuddy.data.local.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "saved_routine")
public class SavedRoutine {
    @PrimaryKey(autoGenerate = true)
    public int savedRoutineID;

    @ColumnInfo(name = "name")
    public String name;

}
