package be.kuleuven.gymbuddy.data.local.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SavedRoutine {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "name")
    public String name;
}
