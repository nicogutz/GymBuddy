package be.kuleuven.gymbuddy.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import be.kuleuven.gymbuddy.R;

public class HomeFragment extends Fragment {

    //    graph jjoe64
    private LineGraphSeries<DataPoint> series1;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        //        for jjoe64 graph:


    } //class or interface expected here?

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View fragView = inflater.inflate(R.layout.fragment_home, container,
                false); //inflate up here and assign to variable

        GraphView graph = fragView.findViewById(R.id.graph); //change getActivity() to fragView
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3)
        });
        graph.addSeries(series);

        return fragView; //return fragView        return fragView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }
}