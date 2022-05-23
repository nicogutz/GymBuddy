package be.kuleuven.gymbuddy.data.remote;

import java.util.List;

import be.kuleuven.gymbuddy.data.local.entities.PublicExercise;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PublicExerciseAPI {
    @GET("get_all_exercises")
    Call<List<PublicExercise>> getPublicExercises();
}
