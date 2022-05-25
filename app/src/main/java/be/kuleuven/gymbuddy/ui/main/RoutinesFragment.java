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
    ExercisesFragmentAdapter adapter;

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
        ArrayList<SavedRoutine> routines = new ArrayList<>();
        ArrayList<String> exercises = new ArrayList<>();
        exercises.add("Exercise TEST 1");
        exercises.add("EXERCISE TEST 2");

        routines.add(new SavedRoutine("Routine Test", exercises));

        RoutinesFragmentAdapter adapterRoutines = new RoutinesFragmentAdapter(getContext(), routines);
        expandableListViewRoutines.setAdapter(adapterRoutines);

    }

}