package be.kuleuven.gymbuddy.data.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * A class to map the query results of exercises to a map of lists. This class maps the equivalent
 * columns in the PublicExercise table.
 */
public class ExerciseValue {
    @SerializedName("publicExerciseID")
    public int publicExerciseID;

    @SerializedName("internal_name")
    public String internal_name;

    @SerializedName("name")
    public String name;

    public int getId() {
        return publicExerciseID;
    }

    public String getInternal_name() {
        return internal_name;
    }

    public String getName() {
        return name;
    }
    public String getNameLower(){return name.toLowerCase();}

    @NonNull
    @Override
    public String toString() {
        return name;
    }

    public ExerciseValue(ExerciseValue exerciseValue){
        this.publicExerciseID = exerciseValue.publicExerciseID;
        this.internal_name = exerciseValue.internal_name;
        this.name = exerciseValue.name;
    }
    /**
     * Just for testing, Exercises are final.
     */
    public ExerciseValue(int publicExerciseID, String internal_name, String name) {
        this.publicExerciseID = publicExerciseID;
        this.internal_name = internal_name;
        this.name = name;
    }
}
