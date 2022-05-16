package be.kuleuven.gymbuddy.ui.exercise_page;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import be.kuleuven.gymbuddy.R;

import androidx.appcompat.app.AppCompatActivity;

public class ExercisePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_page);
    }
}