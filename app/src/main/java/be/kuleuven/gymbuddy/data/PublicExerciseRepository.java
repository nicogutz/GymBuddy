package be.kuleuven.gymbuddy.data;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

import be.kuleuven.gymbuddy.data.local.AppDatabase;
import be.kuleuven.gymbuddy.data.local.access.PublicExerciseDAO;
import be.kuleuven.gymbuddy.data.local.entities.PublicExercise;
import be.kuleuven.gymbuddy.data.remote.PublicExerciseAPI;
import be.kuleuven.gymbuddy.data.remote.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Response;

public class PublicExerciseRepository {
    private final PublicExerciseDAO publicExerciseDAO;
    private final LiveData<List<PublicExercise>> allPublicExercises;

    public PublicExerciseRepository(Application application) {
        PublicExerciseAPI api = RetrofitInstance.getInstance().create(PublicExerciseAPI.class);

        publicExerciseDAO = AppDatabase.getInstance(application).publicExerciseDAO();

        allPublicExercises = publicExerciseDAO.getAllExercises();

        getPublicExerciseFromAPI(api);
    }

    private void getPublicExerciseFromAPI(PublicExerciseAPI api) {
        Call<List<PublicExercise>> call = api.getPublicExercises();
        call.enqueue(new retrofit2.Callback<List<PublicExercise>>() {
            @Override
            public void onResponse(Call<List<PublicExercise>> call,
                                   Response<List<PublicExercise>> response) {
                if (response.isSuccessful()) {
                    insert(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<PublicExercise>> call, Throwable t) {
                Log.d("main", "onFailure: " + t.getMessage());
            }
        });
    }

    public LiveData<List<PublicExercise>> getAllPublicExercises() {
        return allPublicExercises;
    }

    public void deleteAll() {
        AppDatabase.databaseWriteExecutor.execute(publicExerciseDAO::deleteAll);
    }

    void insert(List<PublicExercise> publicExercises) {
        AppDatabase.databaseWriteExecutor.execute(() -> publicExerciseDAO.insertAll(publicExercises));
    }
}
