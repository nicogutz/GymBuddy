package be.kuleuven.gymbuddy.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.HashMap;
import java.util.Map;

import be.kuleuven.gymbuddy.R;
import be.kuleuven.gymbuddy.data.local.entities.RecordedExercise;

public class CalendarFragment extends Fragment {

    public CalendarView calendarView;
//    Map<Date, List<RecordedExercise>> exerciseMap = new HashMap<>();
    public Map<String, RecordedExercise[]> exerciseMap = new HashMap<>();
    public ListView exsOfDayList;
    String date;


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

        exsOfDayList = view.findViewById(R.id.list_exercises_date);
        calendarView = view.findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String dateString = dayOfMonth + "/" + month + "/" + year; //dd/mm/yyyy
                setDate(dateString);
                Log.d("calendar", "date is" + dateString );

            }
        });
        RecordedExercise array1[] = {};

        exerciseMap.put("12/5/2022", array1);
        //we are not showing
        ArrayAdapter<String> calendarAdapter = new ArrayAdapter<String>(getContext(),R.layout.fragment_calendar ,array1);
        exsOfDayList.setAdapter(calendarAdapter);


    }

    public void setDate(String date){
        this.date = date;
    }
//
//    public String getDateString(){
//        return this.dateString;
//    }

}