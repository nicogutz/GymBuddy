package be.kuleuven.gymbuddy.ui.routine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import be.kuleuven.gymbuddy.R;

public class SelectedRoutine extends AppCompatActivity {

    //this class should display the routine selected in the routines fragment
    //if user clicks on item (exercise) -> ex page of exercise opens(?)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_routine);
    }
}