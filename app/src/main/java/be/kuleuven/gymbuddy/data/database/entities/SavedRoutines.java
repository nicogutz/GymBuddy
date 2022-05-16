package be.kuleuven.gymbuddy.data.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class SavedRoutines {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "date")
    public Date date;

    @ColumnInfo(name = "favorite")
    public boolean favorite;

    @ColumnInfo(name = "repeats_days")
    public int repeatsDays;

}
