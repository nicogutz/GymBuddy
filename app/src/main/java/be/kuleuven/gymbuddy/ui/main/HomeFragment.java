package be.kuleuven.gymbuddy.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        double x,y;
        x = 0;

        GraphView graph = (GraphView) requireActivity().findViewById(R.id.graph);
        series1 = new LineGraphSeries<>();

        int numDataPoints = 500;
        for (int i = 0; i <numDataPoints; i++) {
            x += 0.1;
            y = Math.sin(x);
            series1.appendData(new DataPoint(x,y), true, 100);
        }
        graph.addSeries(series1);
    } //class or interface expected here?

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}