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
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import java.util.List;
import java.util.Map;

import be.kuleuven.gymbuddy.R;
import be.kuleuven.gymbuddy.data.model.ExerciseValue;
import be.kuleuven.gymbuddy.ui.SharedViewModel;

@SuppressWarnings("ConstantConditions")
public class ExercisesFragment extends Fragment {

    ExpandableListView expandableListView;
    MainAdapter adapter;

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
        SharedViewModel viewModel = new ViewModelProvider(getActivity()).get(SharedViewModel.class);

        expandableListView = view.findViewById(R.id.expandable_listview);
        expandableListView.setOnChildClickListener(
                (parent, v, groupPosition, childPosition, id) -> {

                    MainAdapter mainAdapter = (MainAdapter) parent.getExpandableListAdapter();
                    ExerciseValue exerciseValue = (ExerciseValue) mainAdapter.getChild(
                            groupPosition, childPosition);
                    Bundle args = new Bundle();
                    args.putInt("exerciseID", exerciseValue.publicExerciseID);
                    getActivity().findViewById(R.id.nav_view).setVisibility(View.INVISIBLE);

                    Navigation.findNavController(view)
                              .navigate(R.id.action_exercises_to_exercise_page, args);
                    return false;
                });

        // Observe the LiveData, passing in the main activity as
        // the LifecycleOwner and the observer.
        viewModel.getExercisesGroupedByMuscles().observe(getActivity(), getMapObserver());

    }

    @NonNull
    private Observer<Map<String, List<ExerciseValue>>> getMapObserver() {
        return stringListMap -> {
            adapter = new MainAdapter(getContext(), stringListMap);
            expandableListView.setAdapter(adapter);
        };
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