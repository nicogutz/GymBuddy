package be.kuleuven.gymbuddy.ui.exercise_page;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import be.kuleuven.gymbuddy.R;
import be.kuleuven.gymbuddy.data.local.entities.PublicExercise;
import be.kuleuven.gymbuddy.ui.SharedViewModel;

public class ExercisePageFragment extends Fragment {

    public ExercisePageFragment() {
    }

    private static void setNavigation(View viewFragment) {
        viewFragment.setFocusableInTouchMode(true);
        viewFragment.requestFocus();
        viewFragment.setOnKeyListener((v, keyCode, event) -> {
            Navigation.findNavController(v)
                      .navigate(R.id.action_exercise_page_to_exercises);
            return keyCode == KeyEvent.KEYCODE_BACK;
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Navigation.findNavController(getView())
                  .navigate(R.id.action_exercise_page_to_exercises);
        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewFragment = inflater.inflate(R.layout.fragment_exercise_page, container, false);
        SharedViewModel viewModel = new ViewModelProvider(getActivity()).get(SharedViewModel.class);

        setNavigation(viewFragment);
        PublicExercise publicExerciseLocal;
        viewModel.getPublicExerciseByID((Integer) getArguments().get("exerciseID"))
                 .observe(getActivity(), publicExercise ->  publicExercise);
        return viewFragment;
    }
}