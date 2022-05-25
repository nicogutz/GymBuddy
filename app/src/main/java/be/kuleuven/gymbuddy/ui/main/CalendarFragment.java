package be.kuleuven.gymbuddy.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import be.kuleuven.gymbuddy.R;
import be.kuleuven.gymbuddy.data.local.entities.RecordedExercise;
import be.kuleuven.gymbuddy.ui.SharedViewModel;

public class CalendarFragment extends Fragment {


    public CalendarFragment() {

        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedViewModel viewModel = new ViewModelProvider(getActivity()).get(SharedViewModel.class);
        ListView exsOfDayList = view.findViewById(R.id.list_exercises_date);
        CalendarView calendarView = view.findViewById(R.id.calendarView);
        Map<Date, List<RecordedExercise>> exerciseMap = new TreeMap<>();

        viewModel.getRecordedExercisesByDate().observe(getActivity(), stringListMap -> {
                    exerciseMap.clear();
                    exerciseMap.putAll(stringListMap);
                    });

        Calendar cal = Calendar.getInstance();
        calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            if (exerciseMap.isEmpty())
                return;
            cal.set(year, month, dayOfMonth, 0, 0, 0);
            cal.set(Calendar.MILLISECOND, 0);
            Log.d("SELECTED: ", cal.getTime().toString());
            exerciseMap.keySet().forEach(i -> Log.d("KEY: ", i.toString()));
            ArrayList<RecordedExercise> selected = (ArrayList<RecordedExercise>) exerciseMap.get(cal.getTime());
            if (selected == null) {
                ArrayAdapter<String> calendarAdapter = new ArrayAdapter<>(getContext(),
                        R.layout.empty_layout_textview, new ArrayList<String>());
                exsOfDayList.setAdapter(calendarAdapter);
                return;
            }

            ArrayList<String> selectedStrings = (ArrayList<String>) selected.stream()
                                                                            .map(RecordedExercise::getNameFull)
                                                                            .collect(
                                                                                    Collectors.toList());
            ArrayAdapter<String> calendarAdapter = new ArrayAdapter<>(getContext(),
                    R.layout.empty_layout_textview, selectedStrings);

            selectedStrings.forEach(i -> Log.d("TEST", i));
            exsOfDayList.setAdapter(calendarAdapter);
        });


    }


}