package be.kuleuven.gymbuddy.data.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class DoneExercise {
    @PrimaryKey
    public int id;

    @ForeignKey(parentColumns = "saved_routinesid")
    public String name;
    @ColumnInfo(name = 'name')
    public String name;
    @ColumnInfo(name = 'name')
    public String name;
    @ColumnInfo(name = 'name')
    public String name;
    @ColumnInfo(name = 'name')
    public String name;
    @ColumnInfo(name = 'name')
    public String name;

}
