package be.kuleuven.gymbuddy.ui.main;

import android.os.Bundle;
import android.service.voice.VoiceInteractionService;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import be.kuleuven.gymbuddy.MainActivity;
import be.kuleuven.gymbuddy.R;
import be.kuleuven.gymbuddy.data.model.ExerciseValue;
import be.kuleuven.gymbuddy.ui.SharedViewModel;

@SuppressWarnings("ConstantConditions")
public class ExercisesFragment extends Fragment {

    private Integer savedRoutineID;
    private ExpandableListView expandableListView;
    private ExercisesFragmentAdapter adapter;
    private MainActivity mainActivity;
    SharedViewModel viewModel;

    public ExercisesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) getActivity();
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(getActivity()).get(SharedViewModel.class);

        try {
            savedRoutineID = Integer.valueOf(getArguments().get("savedRoutineID").toString());
        } catch (NullPointerException ignore) {
            savedRoutineID = null;
        }

        super.onViewCreated(view, savedInstanceState);

        expandableListView = view.findViewById(R.id.expandable_listview);
        if (savedRoutineID == null) {
            expandableListView.setOnChildClickListener(
                    (parent, v, groupPosition, childPosition, id) -> {

                        ExercisesFragmentAdapter exercisesFragmentAdapter =
                                (ExercisesFragmentAdapter)
                                        parent.getExpandableListAdapter();
                        ExerciseValue exerciseValue =
                                (ExerciseValue) exercisesFragmentAdapter.getChild(
                                        groupPosition, childPosition);
                        Bundle args = new Bundle();
                        args.putInt("exerciseID", exerciseValue.publicExerciseID);
                        Navigation.findNavController(view)
                                  .navigate(R.id.action_exercises_to_exercise_page, args);
                        return false;
                    });
        }

        // Observe the LiveData, passing in the main activity as
        // the LifecycleOwner and the observer.
        viewModel.getExercisesGroupedByMuscles().observe(getActivity(), getMapObserver());

    }

    @NonNull
    private Observer<Map<String, List<ExerciseValue>>> getMapObserver() {
        return stringListMap -> {
            if (savedRoutineID == null) {
                adapter = new ExercisesFragmentAdapter(getContext(), stringListMap);
                expandableListView.setAdapter(adapter);
            } else {
                expandableListView.setAdapter(
                        new ExercisesFragmentAdapterChecked(getContext(), stringListMap));
                for (int i = 0; i < expandableListView.getExpandableListAdapter()
                                                      .getGroupCount(); i++) {
                    expandableListView.expandGroup(i);
                }
            }

        };
    }

    public void expandAll() {
        for (int i = 0; i < adapter.getGroupCount(); i++) {
            expandableListView.expandGroup(i);
        }
    }

    public void collapseAll() {
        for (int i = 0; i < adapter.getGroupCount(); i++) {
            expandableListView.collapseGroup(i);
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        menu.clear();
        inflater.inflate(R.menu.top_app_bar, menu);

        if (savedRoutineID != null) {
            getActivity().findViewById(
                    R.id.nav_view).setVisibility(View.INVISIBLE);
            MenuItem item = menu.findItem(R.id.submitButton);
            item.setVisible(true);
            item.setOnMenuItemClickListener(item1 -> {
                ArrayList<String> list = ((ExercisesFragmentAdapterChecked) expandableListView.getExpandableListAdapter()).selectedExercises;
                viewModel.setExerciseList(list,savedRoutineID);
                getActivity().findViewById(
                        R.id.nav_view).setVisibility(View.VISIBLE);
                Navigation.findNavController(getView())
                          .navigate(R.id.action_exercises_to_routines);

                return false;
            });
            return;
        }
        MenuItem item = menu.findItem(R.id.app_bar_search);
        item.setVisible(true);
        SearchView searchView = new SearchView(
                mainActivity.getSupportActionBar().getThemedContext());
        item.expandActionView();
        item.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                adapter.filterData("");
                collapseAll();
                return true;
            }
        });

        item.setShowAsAction(
                MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItem.SHOW_AS_ACTION_IF_ROOM);
        item.setActionView(searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.filterData(query);
                expandAll();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filterData(newText);
                expandAll();
                return false;
            }
        });
        searchView.setOnCloseListener(() -> {
            adapter.filterData("");
            collapseAll();
            return false;
        });
        searchView.setOnClickListener(v -> {

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getActivity().findViewById(
                R.id.nav_host_fragment_activity_main).setVisibility(View.VISIBLE);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exercises, container, false);

    }
}