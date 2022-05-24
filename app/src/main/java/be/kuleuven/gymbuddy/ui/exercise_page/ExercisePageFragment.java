package be.kuleuven.gymbuddy.ui.exercise_page;

import static be.kuleuven.gymbuddy.common.Constants.BASE_VIDEO_URL;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import be.kuleuven.gymbuddy.R;
import be.kuleuven.gymbuddy.data.local.entities.PublicExercise;
import be.kuleuven.gymbuddy.ui.SharedViewModel;

public class ExercisePageFragment extends Fragment {
    MediaController mediaController;
    BottomNavigationView navBar;

    public ExercisePageFragment() {
    }

    private void setNavigation(View viewFragment) {
        viewFragment.setFocusableInTouchMode(true);
        viewFragment.requestFocus();
        viewFragment.setOnKeyListener((v, keyCode, event) -> {
            navBar.setVisibility(View.VISIBLE);
            Navigation.findNavController(v)
                      .navigate(R.id.action_exercise_page_to_exercises);
            return keyCode == KeyEvent.KEYCODE_BACK;
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mediaController = new MediaController(getActivity());
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        navBar.setVisibility(View.VISIBLE);
        Navigation.findNavController(getView())
                  .navigate(R.id.action_exercise_page_to_exercises);
        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewFragment = inflater.inflate(R.layout.fragment_exercise_page, container, false);
        navBar = getActivity().findViewById(R.id.nav_view);
        SharedViewModel viewModel = new ViewModelProvider(getActivity()).get(SharedViewModel.class);
        setNavigation(viewFragment);

        viewModel.getPublicExerciseByID((Integer) getArguments().get("exerciseID"))
                 .observe(getActivity(), getPublicExerciseObserver(viewFragment));
        return viewFragment;
    }

    @NonNull
    private Observer<PublicExercise> getPublicExerciseObserver(View v) {
        return publicExercise -> {
            ((TextView) v.findViewById(R.id.exerciseNameText)).setText(publicExercise.name);
            ((TextView) v.findViewById(R.id.utilityText)).setText(publicExercise.utility);
            ((TextView) v.findViewById(R.id.forceText)).setText(publicExercise.force);
            ((TextView) v.findViewById(R.id.targetMuscleText)).setText(
                    publicExercise.targetMuscles);
            ((TextView) v.findViewById(R.id.synergistMuscleText)).setText(
                    publicExercise.synergistMuscles);
            ((TextView) v.findViewById(R.id.executionText)).setText(publicExercise.execution);
            ((TextView) v.findViewById(R.id.commentsText)).setText(publicExercise.comments);
            ((TextView) v.findViewById(R.id.preparationText)).setText(publicExercise.preparation);

            VideoView videoView = v.findViewById(R.id.ex_VideoView);
            videoView.setVideoPath(BASE_VIDEO_URL + publicExercise.identifier + "/video.mp4");
            videoView.start();
        };
    }

}