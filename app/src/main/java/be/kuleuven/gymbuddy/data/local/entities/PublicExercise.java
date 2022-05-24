package be.kuleuven.gymbuddy.data.local.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Entities represent a table in SQL, they have keys and columns. This is the only entity that has
 * something extra. The SerializedName decorator tells the GSON converter in the Retrofit instance
 * where to put the values from the JSON response. It can convert basic things like strings to
 * integers.
 * This table represents an exercise, it has the same structure as its remote counterpart to make
 * the de-serialization easier.
 */
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

    @SerializedName("muscle_group")
    @ColumnInfo(name = "muscle_group")
    public String muscle_group;

    @SerializedName("internal_name")
    @ColumnInfo(name = "internal_name")
    public String internal_name;

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
