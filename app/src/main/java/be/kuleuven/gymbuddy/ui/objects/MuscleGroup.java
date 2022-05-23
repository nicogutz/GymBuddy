package be.kuleuven.gymbuddy.ui.objects;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class MuscleGroup {
    public String name;
    ArrayList<String> publicExercises;

    @NonNull
    @Override
    public String toString() {
        return name;
    }

    public MuscleGroup(ArrayList<String> stringsExercises, String name) {
        this.publicExercises = stringsExercises;
        this.name = name;
    }

    public ArrayList<String> getPublicExercises() {
        return publicExercises;
    }

    public int size() {
        return publicExercises.size();
    }

    public String get(int childPosition) {
        return publicExercises.get(childPosition);
    }
    public void add(String name, ArrayList<String> strings){
        this.name = name;
        this.publicExercises = strings;
    }
    public String getName() {
        return name;
    }
}
