package be.kuleuven.gymbuddy.ui.main;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

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
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import be.kuleuven.gymbuddy.R;
import be.kuleuven.gymbuddy.data.local.entities.RecordedExercise;
import be.kuleuven.gymbuddy.data.model.RecordedExerciseValue;
import be.kuleuven.gymbuddy.ui.SharedViewModel;

/**
 * This fragment holds the graph and a spinner to select the exercise we want to display.
 * TODO: Add max, avg, etc.
 */
@SuppressWarnings("ALL")
public class HomeFragment extends Fragment {
    View fragView;
    Spinner spinner;
    GraphView graph;
    Map<String, List<RecordedExercise>> stringListMapLocal;
    TextView maxSetsText;
    TextView maxRepsText;
    TextView maxWeightText;
    TextView maxVolumeText;
    TextView avgSetsText;
    TextView avgRepsText;
    TextView avgWeightText;
    TextView avgVolumeText;

    public HomeFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    /**
     * This method connects the objects with their respective class. we also add a listener to the
     * spinner. When a new item is selected then the graph changes.
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        fragView = inflater.inflate(R.layout.fragment_home, container, false);

        //Setup the spinner, we want to disable it until we get data.
        spinner = fragView.findViewById(R.id.homeExerciseSpinner);
        spinner.setEnabled(false);

        //Setup for the graphs TODO: Changethe range, etc
        DateFormat dateFormat = new SimpleDateFormat("dd/MM", Locale.GERMANY);

        graph = fragView.findViewById(R.id.graph);
        graph.getGridLabelRenderer().setNumHorizontalLabels(5);
        graph.getGridLabelRenderer()
             .setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity(), dateFormat));
        graph.setCursorMode(true);

        maxSetsText = fragView.findViewById(R.id.maxSetsText);
        maxRepsText = fragView.findViewById(R.id.maxRepsText);
        maxWeightText = fragView.findViewById(R.id.maxWeightText);
        avgSetsText = fragView.findViewById(R.id.avgSetsText);
        avgRepsText = fragView.findViewById(R.id.avgRepsText);
        avgWeightText = fragView.findViewById(R.id.avgWeightText);

        createGraph();

        return fragView;
    }

    /**
     * Here we setup a listener to make a new graph every time the spinner changes.
     * Not super efficient but the library is not super mature, it is nevertheless the easiest
     * interactive graph we could find. Also a lot of comments because its the only thing that
     * keeps me sane.
     */
    private void createGraph() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
                                       int position, long id) {
                graph.removeAllSeries();

                // Kinda shady way of getting the values from the map.
                List<RecordedExercise> exerciseValues = stringListMapLocal.get(
                        (String) parentView.getItemAtPosition(position));

                // Quick mafs
                Integer[] setArray = exerciseValues.stream()
                                                   .map(i -> i.sets)
                                                   .toArray(Integer[]::new);
                Integer[] repsArray = exerciseValues.stream()
                                                    .map(i -> i.reps)
                                                    .toArray(Integer[]::new);
                Float[] weightArray = exerciseValues.stream()
                                                    .map(i -> i.weight)
                                                    .toArray(Float[]::new);

                // Would be nice to make it a function but who has the time
                maxSetsText.setText(String.valueOf(
                        Arrays.stream(setArray).max(Comparator.comparingInt(x -> x)).get()));
                maxRepsText.setText(String.valueOf(
                        Arrays.stream(repsArray).max(Comparator.comparingInt(x -> x)).get()));
                maxWeightText.setText(String.format("%.1f",
                        Arrays.stream(weightArray).max(Comparator.comparingDouble(x -> x)).get()));

                avgSetsText.setText(String.format("%.1f",
                        Arrays.stream(setArray).mapToDouble(i -> i).average().getAsDouble()));
                avgRepsText.setText(String.format("%.1f",
                        Arrays.stream(repsArray).mapToDouble(i -> i).average().getAsDouble()));
                avgWeightText.setText(String.format("%.1f",
                        Arrays.stream(weightArray).mapToDouble(i -> i).average().getAsDouble()));

                // Actually super proud of this one. It has a bit of boilerplate code because of
                // all the type conversion going on but *shrugs*. Since the graph only takes
                // DataPoint[] we need to map the arraylist of values to that
                // I guess I could've made the mapping happen in the SQL query but if my
                // grandma had wheels she'd be a bike.
                LineGraphSeries<DataPoint> seriesReps = new LineGraphSeries<>(
                        exerciseValues.stream().map(i -> new DataPoint(i.date, i.reps))
                                      .toArray(DataPoint[]::new));

                //Make it a bit pretty.
                seriesReps.setColor(Color.RED);
                seriesReps.setTitle("Repetitions");
                seriesReps.setDrawDataPoints(true);
                graph.addSeries(seriesReps);


                // Programmers should not copy paste, but we need to do it for reps and weight too.
                LineGraphSeries<DataPoint> seriesSets = new LineGraphSeries<>(
                        exerciseValues.stream().map(i -> new DataPoint(i.date, i.sets))
                                      .toArray(DataPoint[]::new));

                seriesSets.setColor(Color.YELLOW);
                seriesSets.setTitle("Sets");
                seriesSets.setDrawDataPoints(true);
                graph.addSeries(seriesSets);

                LineGraphSeries<DataPoint> seriesWeight = new LineGraphSeries<>(
                        exerciseValues.stream().map(i -> new DataPoint(i.date, i.weight))
                                      .toArray(DataPoint[]::new));
                seriesWeight.setColor(Color.GREEN);
                seriesWeight.setTitle("Weight");
                seriesWeight.setDrawDataPoints(true);
                graph.addSeries(seriesWeight);

                graph.getViewport().setMaxX(new Date().getTime());
                graph.getViewport().setXAxisBoundsManual(true);
            }

            // Doesn't really matter since we only care what happens on a new selection.
            // The spinner also defaults to the first item on the lists and triggers the on
            // selected as soon as it is created, so there is always a graph with the first
            // exercise.
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });
    }

    /**
     * We need to observe the live data from the database to register any changes on the spinner.
     */
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        SharedViewModel viewModel = new ViewModelProvider(getActivity()).get(SharedViewModel.class);

        final Observer<Map<String, List<RecordedExercise>>> exerciseObserver =
                stringListMap -> {
                    spinner.setAdapter(
                            new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,
                                    stringListMap.keySet().toArray(new String[0])));

                    // If the spinner is not empty then enable it.
                    if (!spinner.getAdapter().isEmpty())
                        spinner.setEnabled(true);

                    //We copy the data to the class, it is probably not a great idea but we only
                    //access the data when the spinner is active so I think its fine.
                    stringListMapLocal = stringListMap;
                };

        // Observe the LiveData, passing in the main activity as the LifecycleOwner and the
        // observer we just created.
        viewModel.getRecordedExerciseValues().observe(getActivity(), exerciseObserver);

    }
}