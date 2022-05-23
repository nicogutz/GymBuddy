package be.kuleuven.gymbuddy.data.local.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "public_exercise")
public class PublicExercise {

    @SerializedName("id")
    @PrimaryKey
    public int publicExerciseID;

    @SerializedName("identifier")
    @ColumnInfo(name = "identifier")
    public String identifier;

    @SerializedName("name")
    @ColumnInfo(name = "name")
    public String name;

    @SerializedName("utility")
    @ColumnInfo(name = "utility")
    public String utility;

    @SerializedName("mechanics")
    @ColumnInfo(name = "mechanics")
    public String mechanics;

    @SerializedName("force")
    @ColumnInfo(name = "force")
    public String force;

    @SerializedName("target_muscles")
    @ColumnInfo(name = "target_muscles")
    public String targetMuscles;

    @SerializedName("synergist_muscles")
    @ColumnInfo(name = "synergist_muscles")
    public String synergistMuscles;

    @SerializedName("preparation")
    @ColumnInfo(name = "preparation")
    public String preparation;

    @SerializedName("execution")
    @ColumnInfo(name = "execution")
    public String execution;

    @SerializedName("comments")
    @ColumnInfo(name = "comments")
    public String comments;

    public int getPublicExerciseID() {
        return publicExerciseID;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public String getUtility() {
        return utility;
    }

    public String getMechanics() {
        return mechanics;
    }

    public String getForce() {
        return force;
    }

    public String getTargetMuscles() {
        return targetMuscles;
    }

    public String getSynergistMuscles() {
        return synergistMuscles;
    }

    public String getPreparation() {
        return preparation;
    }

    public String getExecution() {
        return execution;
    }

    public String getComments() {
        return comments;
    }
}
