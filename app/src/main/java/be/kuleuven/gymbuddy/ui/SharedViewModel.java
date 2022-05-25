package be.kuleuven.gymbuddy.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.Date;
import java.util.List;
import java.util.Map;

import be.kuleuven.gymbuddy.data.PublicExerciseRepository;
import be.kuleuven.gymbuddy.data.RecordedExerciseRepository;
import be.kuleuven.gymbuddy.data.SavedRoutineRepository;
import be.kuleuven.gymbuddy.data.local.entities.PublicExercise;
import be.kuleuven.gymbuddy.data.local.entities.RecordedExercise;
import be.kuleuven.gymbuddy.data.local.entities.SavedRoutine;
import be.kuleuven.gymbuddy.data.model.ExerciseValue;
import be.kuleuven.gymbuddy.data.model.RecordedExerciseValue;

public class SharedViewModel extends AndroidViewModel {
    private final RecordedExerciseRepository recordedExerciseRepository;
    private final SavedRoutineRepository savedRoutineRepository;
    private final PublicExerciseRepository publicExerciseRepository;

    public SharedViewModel(@NonNull Application application) {
        super(application);

        publicExerciseRepository = new PublicExerciseRepository(application);
        recordedExerciseRepository = new RecordedExerciseRepository(application);
        savedRoutineRepository = new SavedRoutineRepository(application);

    }

    public LiveData<Map<String, List<ExerciseValue>>> getExercisesGroupedByMuscles() {
        return publicExerciseRepository.getExercisesGroupedByMuscles();
    }

    public LiveData<Map<String, List<RecordedExercise>>> getRecordedExerciseValues(){
        return recordedExerciseRepository.getAllRecordedExerciseValues();
    }

    public LiveData<PublicExercise> getPublicExerciseByID(int id){
        return publicExerciseRepository.getPublicExerciseByID(id);
    }

    public LiveData<List<SavedRoutine>> getAllSavedRoutines(){
        return savedRoutineRepository.getAllSavedRoutines();
    }


    public static void addSavedRoutine(SavedRoutine savedRoutine, Application app){
        SavedRoutineRepository.insertSavedRoutine(savedRoutine, app);
    }

    public static void addAllRecordedExercises(List<RecordedExercise> recordedExercises, Application app){
        RecordedExerciseRepository.insertAllRecordedExercise(recordedExercises, app);
    }

    public LiveData<Map<Date, List<RecordedExercise>>> getRecordedExercisesByDate() {
        return recordedExerciseRepository.getRecordedExercisesByDate();
    }

    public LiveData<List<String>> getSavedRoutineByID(Integer savedRoutineID) {
        return savedRoutineRepository.getSavedRoutineByID(savedRoutineID);
    }
}
