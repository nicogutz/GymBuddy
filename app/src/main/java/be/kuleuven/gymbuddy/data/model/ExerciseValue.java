package be.kuleuven.gymbuddy.data.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

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

    @NonNull
    @Override
    public String toString() {
        return name;
    }

    public ExerciseValue(int publicExerciseID, String internal_name, String name) {
        this.publicExerciseID = publicExerciseID;
        this.internal_name = internal_name;
        this.name = name;
    }
}
