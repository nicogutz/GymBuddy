package be.kuleuven.gymbuddy.data.local.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.net.URL;
import java.util.ArrayList;

@Entity
public class PublicExercise {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "identifier")
    public String identifier;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "utility")
    public ArrayList<String> utility;

    @ColumnInfo(name = "mechanics")
    public ArrayList<String> mechanics;

    @ColumnInfo(name = "force")
    public ArrayList<String> force;

    @ColumnInfo(name = "target_muscles")
    public ArrayList<String> targetMuscles;

    @ColumnInfo(name = "synergist_muscles")
    public ArrayList<String> synergistMuscles;

    @ColumnInfo(name = "video_uri")
    public URL videoURI;

    @ColumnInfo(name = "preparation")
    public String preparation;

    @ColumnInfo(name = "execution")
    public String execution;

    @ColumnInfo(name = "comments")
    public String comments;
}
