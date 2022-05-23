package be.kuleuven.gymbuddy.data;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.Map;

import be.kuleuven.gymbuddy.data.local.AppDatabase;
import be.kuleuven.gymbuddy.data.local.access.PublicExerciseDAO;
import be.kuleuven.gymbuddy.data.local.entities.PublicExercise;
import be.kuleuven.gymbuddy.data.model.ExerciseValue;
import be.kuleuven.gymbuddy.data.remote.PublicExerciseAPI;
import be.kuleuven.gymbuddy.data.remote.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Repositories are not mandatory but according to Google's MVVM architecture guide, highly
 * recommended. This is the interaction layer between the view model and the data sources.
 * Here, we make sure both the API calls and database queries are run on a separate thread.
 * I am using a basic call.enqueue to get the response from studev. Thanks to the database
 * singleton we don't need to instantiate the database and also we don't have to worry about
 * making AsyncCalls every time we make a Query.
 * <p>
 * This repository is specific for the Public Exercises, we don't need to change anything on the
 * database except delete it all when it changed on the source.
 * <p>
 */

public class PublicExerciseRepository {
    private final PublicExerciseDAO publicExerciseDAO;

    /**
     * This gets the Dao and the LiveData from the database. Notice how we don't need to
     * insert data to the database before accessing the LiveData object. This is because we can
     * get the data once it is "ready".
     *
     * @param application the context it is run in, will be provided in the view model
     */
    public PublicExerciseRepository(Application application) {
        PublicExerciseAPI api = RetrofitInstance.getInstance().create(PublicExerciseAPI.class);
        publicExerciseDAO = AppDatabase.getInstance(application).publicExerciseDAO();

        getPublicExerciseFromAPI(api);
    }

    /**
     * Simple API call, gets all the exercises from the remote database. Using enqueue to
     * run it on a separate thread.
     *
     * @param api the api that is to be used.
     */
    private void getPublicExerciseFromAPI(PublicExerciseAPI api) {
        Call<List<PublicExercise>> call = api.getPublicExercises();
        call.enqueue(new retrofit2.Callback<List<PublicExercise>>() {
            @Override
            public void onResponse(@NonNull Call<List<PublicExercise>> call,
                                   @NonNull Response<List<PublicExercise>> response) {
                if (response.isSuccessful()) {
                    insert(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<PublicExercise>> call, @NonNull Throwable t) {
                Log.d("main", "onFailure: " + t.getMessage());
            }
        });
    }

    public LiveData<PublicExercise> getPublicExerciseByID(int id) {
        return publicExerciseDAO.getExerciseByID(id);
    }

    public LiveData<Map<String, List<ExerciseValue>>> getExercisesGroupedByMuscles() {
        return publicExerciseDAO.getExercisesGroupedByMuscles();
    }

    public void deleteAll() {
        AppDatabase.databaseWriteExecutor.execute(publicExerciseDAO::deleteAll);
    }

    /**
     * Since the list we get from the response.body() is already de-serialized, we can insert it
     * as is to the local database. We just need to run it in the WriteExecutor so that it runs
     * outside the main thread.
     *
     * @param publicExercises a list of PublicExercises to be inserted to the database.
     */
    void insert(List<PublicExercise> publicExercises) {
        AppDatabase.databaseWriteExecutor.execute(
                () -> publicExerciseDAO.insertAll(publicExercises));
    }

}


