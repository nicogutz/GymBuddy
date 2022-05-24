package be.kuleuven.gymbuddy.ui.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import be.kuleuven.gymbuddy.R;
import be.kuleuven.gymbuddy.data.model.RecordedExerciseValue;
import be.kuleuven.gymbuddy.ui.SharedViewModel;

public class HomeFragment extends Fragment {
    View fragView;
    Spinner spinner;
    GraphView graph;
    Map<String, List<RecordedExerciseValue>> stringListMapLocal;

    public HomeFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        fragView = inflater.inflate(R.layout.fragment_home, container, false);

        spinner = fragView.findViewById(R.id.homeExerciseSpinner);
        spinner.setEnabled(false);

        @SuppressLint("SimpleDateFormat") DateFormat dateFormat =
                new SimpleDateFormat("dd-MM-yy");

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView,
                                       View selectedItemView,
                                       int position,
                                       long id) {
                String selectedName = (String) parentView.getItemAtPosition(position);
                List<RecordedExerciseValue> exerciseValues = stringListMapLocal.get(selectedName);
                DataPoint[] reps = new DataPoint[exerciseValues.size()];
                int count = 0;

                for (RecordedExerciseValue recordedExerciseValue : exerciseValues) {
                    reps[count] = new DataPoint(recordedExerciseValue.date.getTime(),
                            recordedExerciseValue.reps);
                    count++;
                }
                LineGraphSeries<DataPoint> seriesReps = new LineGraphSeries<>(reps);

                graph.getGridLabelRenderer()
                     .setNumHorizontalLabels(3);
                graph.getGridLabelRenderer()
                     .setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity(), dateFormat));
                graph.addSeries(seriesReps);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        makeGraph(fragView);

        return fragView;
    }

    private void makeGraph(View fragView) {
        graph = fragView.findViewById(R.id.graph);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        SharedViewModel viewModel = new ViewModelProvider(getActivity()).get(SharedViewModel.class);

        final Observer<Map<String, List<RecordedExerciseValue>>> exerciseObserver =
                stringListMap -> {
                    spinner.setEnabled(true);
                    spinner.setAdapter(new ArrayAdapter<String>(getContext(),
                            android.R.layout.simple_spinner_item,
                            stringListMap.keySet().toArray(new String[0])
                    ));
                    stringListMapLocal =
                            stringListMap;
                };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        viewModel.getRecordedExerciseValues().observe(getActivity(), exerciseObserver);

    }
}