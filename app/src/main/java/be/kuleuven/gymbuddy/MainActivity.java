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

import be.kuleuven.gymbuddy.databinding.ActivityMainPageBinding;
import be.kuleuven.gymbuddy.ui.login.LoginFragment;


public class MainActivity extends AppCompatActivity {

    private @NonNull ActivityMainPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView bottomNavigationView = binding.navView;
        bottomNavigationView.setVisibility(View.INVISIBLE);

        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment_activity_main, new LoginFragment());
        fragmentTransaction.commit();

    }

    public void changeView(View v){
        BottomNavigationView bottomNavigationView = binding.navView;
        bottomNavigationView.setVisibility(View.VISIBLE);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_activity_main);
        NavController navController = navHostFragment.getNavController();

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_routines, R.id.navigation_exercises, R.id.navigation_calendar)
                .build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

}