package be.kuleuven.gymbuddy.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;


@Entity
public class Health {
    @PrimaryKey
    public Date date;

    @ColumnInfo(name = "weight")
    public float weight;

    @ColumnInfo(name = "body_fat")
    public float bodyFat;

    @ColumnInfo(name = "BMI")
    public float BMI;

    @ColumnInfo(name = "waist")
    public float waist;

    @ColumnInfo(name = "chest")
    public float chest;

    @ColumnInfo(name = "arm")
    public float arm;
}
