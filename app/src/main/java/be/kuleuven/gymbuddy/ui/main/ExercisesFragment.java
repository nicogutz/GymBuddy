package be.kuleuven.gymbuddy.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;
import java.util.Map;

import be.kuleuven.gymbuddy.R;
import be.kuleuven.gymbuddy.data.model.ExerciseValue;
import be.kuleuven.gymbuddy.ui.SharedViewModel;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
@SuppressWarnings("ConstantConditions")
public class ExercisesFragment extends Fragment {

    ExpandableListView expandableListView;
    MainAdapter adapter;
    private LiveData<Map<String, List<ExerciseValue>>> exercisesGroupedByMuscles;


    public ExercisesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        expandableListView = view.findViewById(R.id.expandable_listview);
        // Create the observer which updates the UI.

        SharedViewModel viewModel = new ViewModelProvider(getActivity()).get(SharedViewModel.class);

        final Observer<Map<String, List<ExerciseValue>>> nameObserver =
                (Observer<Map<String, List<ExerciseValue>>>) stringListMap -> {
            // Update the UI, in this case, a TextView.
            adapter = new MainAdapter(getContext(), stringListMap);
            expandableListView.setAdapter(adapter);
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        viewModel.getExercisesGroupedByMuscles().observe(getActivity(), nameObserver);

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.top_app_bar, menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exercises, container, false);

    }

}