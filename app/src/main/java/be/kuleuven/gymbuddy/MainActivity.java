package be.kuleuven.gymbuddy;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import be.kuleuven.gymbuddy.data.PublicExerciseRepository;
import be.kuleuven.gymbuddy.data.remote.PublicExerciseAPI;
import be.kuleuven.gymbuddy.databinding.ActivityMainPageBinding;


public class MainActivity extends AppCompatActivity {

    private @NonNull ActivityMainPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PublicExerciseRepository publicExerciseRepository = new PublicExerciseRepository(getApplication());

    }

    public void changeView(View v){
    }

}