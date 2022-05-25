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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import be.kuleuven.gymbuddy.R;
import be.kuleuven.gymbuddy.data.local.entities.RecordedExercise;

public class CalendarFragment extends Fragment {

    public CalendarView calendarView;
    public Map<Date, ArrayList<RecordedExercise>> exerciseMap;
    public ListView exsOfDayList;
    Calendar cal;

    public CalendarFragment() {

        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        exerciseMap = new TreeMap<>();
        cal = Calendar.getInstance();
        cal.set(2022, Calendar.MAY, 25, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);

        exerciseMap.put(cal.getTime(), new ArrayList<>());
        exerciseMap.get(cal.getTime()).add(new RecordedExercise("TEST", 2, 2.1f, 2));
        exerciseMap.get(cal.getTime()).add(new RecordedExercise("TEST 2", 2, 2.1f, 2));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        exsOfDayList = view.findViewById(R.id.list_exercises_date);
        calendarView = view.findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            cal.set(year, month, dayOfMonth, 0, 0, 0);
            cal.set(Calendar.MILLISECOND, 0);
            Log.d("SELECTED: ", cal.getTime().toString());
            exerciseMap.keySet().forEach(i -> Log.d("KEY: ", i.toString()));
            ArrayList<RecordedExercise> selected = exerciseMap.get(cal.getTime());
            if (selected == null) {
                ArrayAdapter<String> calendarAdapter = new ArrayAdapter<>(getContext(),
                        R.layout.empty_layout_textview, new ArrayList<String>());
                exsOfDayList.setAdapter(calendarAdapter);
                return;
            }

            ArrayList<String> selectedStrings = (ArrayList<String>) selected.stream()
                .map(i -> i.getNameFull())
                                                                            .collect(
                                                                                    Collectors.toList());
            ArrayAdapter<String> calendarAdapter = new ArrayAdapter<>(getContext(),
                    R.layout.empty_layout_textview, selectedStrings);

            selectedStrings.forEach(i -> Log.d("TEST", i));
            exsOfDayList.setAdapter(calendarAdapter);
        });


    }


}