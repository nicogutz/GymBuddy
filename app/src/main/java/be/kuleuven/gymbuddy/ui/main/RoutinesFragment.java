package be.kuleuven.gymbuddy.ui.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import java.util.ArrayList;
import java.util.List;

import be.kuleuven.gymbuddy.R;
import be.kuleuven.gymbuddy.data.local.entities.SavedRoutine;
import be.kuleuven.gymbuddy.ui.SharedViewModel;

public class RoutinesFragment extends Fragment {

    ExpandableListView expandableListViewRoutines;
    RoutinesFragmentAdapter adapter;
    SharedViewModel viewModel;
    Activity activity;
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
        viewModel = new ViewModelProvider(getActivity()).get(SharedViewModel.class);
        activity = getActivity();

        Button newRoutineButton = view.findViewById(R.id.newRoutineButton);

        newRoutineButton.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("New Routine");
            // Set up the input
            final EditText input = new EditText(getContext());
            // Specify the type of input expected; this, for example, sets the input as a
            // password, and will mask the text
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);

            // Set up the buttons
            builder.setPositiveButton("OK", (dialog, which) -> {
                if (input.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Name cannot be empty", Toast.LENGTH_SHORT)
                         .show();
                    return;
                }
                ArrayList<String> initialList = new ArrayList<>();
                initialList.add("Lever Neck Extension");
                initialList.add("Lever Seated Row");
                SharedViewModel.addSavedRoutine(
                        new SavedRoutine(input.getText().toString(), initialList),
                        activity.getApplication());
            });
            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
            builder.show();
        });
        viewModel.getAllSavedRoutines().observe(getActivity(), getRoutineObserver());
    }

    @NonNull
    private Observer<List<SavedRoutine>> getRoutineObserver() {
        return routineList -> {
            adapter = new RoutinesFragmentAdapter(getContext(),
                    routineList, activity.getApplication(), getView());
            expandableListViewRoutines.setAdapter(adapter);
        };
    }


}