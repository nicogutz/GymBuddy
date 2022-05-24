package be.kuleuven.gymbuddy.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import be.kuleuven.gymbuddy.R;
import be.kuleuven.gymbuddy.data.local.entities.SavedRoutine;

public class RoutinesFragment extends Fragment {

    ExpandableListView expandableListViewRoutines;
    ExercisesFragmentAdapter adapterRoutines;
    List<SavedRoutine> routines;

    public RoutinesFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_routine, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        expandableListViewRoutines = view.findViewById(R.id.expandable_listview_routines);
        routines = new ArrayList<>();
        ArrayList<String> exercises = new ArrayList<>();
        exercises.add("Exercise TEST 1");
        exercises.add("EXERCISE TEST 2");

        routines.add(new SavedRoutine("Routine Test", exercises));
        RoutinesFragmentAdapter adapterRoutines = new RoutinesFragmentAdapter(getContext(),
                (ArrayList<SavedRoutine>) routines);
        expandableListViewRoutines.setAdapter(adapterRoutines);

    }

    /**
     public void initData(){
     ArrayList<String> array1 = new ArrayList<>();
     array1.add("squats");
     array1.add("bench press");

     ArrayList<String> array2 = new ArrayList<>();
     array2.add("chest press");
     array2.add("deadlift");

     routinesMap.put("Monday Routine", array1);
     routinesMap.put("Wednesday Routine", array2);
     }
     **/

}