package be.kuleuven.gymbuddy.data;

import static be.kuleuven.gymbuddy.common.Constants.BASE_URL;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

import be.kuleuven.gymbuddy.data.local.AppDatabase;
import be.kuleuven.gymbuddy.data.local.access.PublicExerciseDAO;
import be.kuleuven.gymbuddy.data.local.entities.PublicExercise;
import be.kuleuven.gymbuddy.data.remote.PublicExerciseAPI;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PublicExerciseRepository {
    private PublicExerciseDAO publicExerciseDAO;
    private LiveData<List<PublicExercise>> allPublicExercises;
    private Retrofit retrofit;

    public PublicExerciseRepository(Application application) {
         retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                 .addConverterFactory(GsonConverterFactory.create())
                .build();
        PublicExerciseAPI api = retrofit.create(PublicExerciseAPI.class);

        Call<List<PublicExercise>> call=api.getPublicExercises();
        getFromAPI(call);

        AppDatabase appDatabase = AppDatabase.getInstance(application);
        publicExerciseDAO = appDatabase.publicExerciseDAO();
        allPublicExercises = publicExerciseDAO.getAllExercises();
    }

    private void getFromAPI(Call<List<PublicExercise>> call) {
        call.enqueue(new retrofit2.Callback<List<PublicExercise>>() {
            @Override
            public void onResponse(Call<List<PublicExercise>> call, Response<List<PublicExercise>> response) {
                if(response.isSuccessful()) {
                    insert(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<PublicExercise>> call, Throwable t) {
                Log.d("main", "onFailure: "+t.getMessage());
            }
        });
    }

    public LiveData<List<PublicExercise>> getAllPublicExercises() {
        return allPublicExercises;
    }

    void insert(List<PublicExercise> publicExercises) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            publicExerciseDAO.insertAll(publicExercises);
        });

    }


}
