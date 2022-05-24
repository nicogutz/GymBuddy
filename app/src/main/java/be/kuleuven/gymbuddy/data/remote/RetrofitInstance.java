package be.kuleuven.gymbuddy.data.remote;

import static be.kuleuven.gymbuddy.common.Constants.BASE_URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Same idea as with the Room singleton, unnecessary given that we are only calling the
 * REST API once, nevertheless if we choose to add functionality this will make things easier.
 */
public class RetrofitInstance {

    private static Retrofit instance;

    /**
     * A function that returns the retrofit instance, what is really important is the addition
     * of the converter factory. Without which, Retrofit wouldn't know how to fill the objects.
     *
     * TODO: Check how to convert other types, for example splitting strings into ArrayLists.
     * * @return Retrofit instance
     */
    public static synchronized Retrofit getInstance() {
        if (instance == null) {
            instance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instance;
    }

}
