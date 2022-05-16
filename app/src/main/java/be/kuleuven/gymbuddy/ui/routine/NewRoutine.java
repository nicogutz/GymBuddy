package be.kuleuven.gymbuddy.ui.routine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import be.kuleuven.gymbuddy.R;

public class NewRoutine extends AppCompatActivity {

    //user can add a new routine
    //displays exercises list with a checker item and a 'finish' button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_routine);
    }
}