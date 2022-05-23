package be.kuleuven.gymbuddy.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import be.kuleuven.gymbuddy.R;
import be.kuleuven.gymbuddy.ui.objects.MuscleGroup;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ExercisesFragment extends Fragment {

    ExpandableListView expandableListView;
    MainAdapter adapter;
    ArrayList<MuscleGroup> muscleGroups;

    public ExercisesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        expandableListView = view.findViewById(R.id.expandable_listview);

        initListData();
        adapter = new MainAdapter(getContext(), muscleGroups);
        expandableListView.setAdapter(adapter);


//        listView = (ListView) view.findViewById(R.id.listExerciseCategory);
//        exCatList.add("legs");
//        exCatList.add("glutes");
//        exCatList.add("chest");
//
//        CustomAdapter customAdapter = new CustomAdapter();
//        listView.setAdapter(customAdapter);

    }

    private void initListData() {

        ArrayList<String> strings = new ArrayList<String>();
        strings.add("BWSupineRowFeetElevated");
        strings.add("BWSupineRowHigh");
        muscleGroups= new ArrayList<MuscleGroup>();

        muscleGroups.add(new MuscleGroup(strings, "BackGeneral"));
    }

    /**
     * class CustomAdapter extends BaseAdapter {
     *
     * @Override public int getCount() {
     * return exCatList.size();  //or should we use .size() -1?
     * }
     * @Override public Object getItem(int position) {
     * return null;
     * }
     * @Override public long getItemId(int position) {
     * return 0;
     * }
     * @Override public View getView(int position, View convertView, ViewGroup parent) {
     * convertView = getLayoutInflater().inflate(R.layout.custom_layout_ex_ategory, null);
     * <p>
     * TextView textView_title = (TextView) convertView.findViewById(R.id.textView_exCatItem);
     * <p>
     * textView_title.setText(exCatList.get(position));
     * return convertView;
     * }
     * <p>
     * }
     **/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exercises, container, false);

    }

}