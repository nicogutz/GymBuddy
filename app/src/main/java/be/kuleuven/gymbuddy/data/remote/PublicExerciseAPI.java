package be.kuleuven.gymbuddy.data.remote;

import java.util.List;

import be.kuleuven.gymbuddy.data.local.entities.PublicExercise;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PublicExerciseAPI {

    /**
     * This is as pretty as it gets, probably the cleanest way to make queries, Volley is simply
     * not equipped for MVVM architectures. The return object is already the same one we are
     * defining the Room database with so insertion is a breeze.
     *
     * @return Retrofit.Call object with a list of PublicExercise objects inside.
     */
    @GET("get_all_exercises")
    Call<List<PublicExercise>> getPublicExercises();
}
