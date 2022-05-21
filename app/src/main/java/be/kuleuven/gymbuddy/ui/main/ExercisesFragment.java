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

import be.kuleuven.gymbuddy.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExercisesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExercisesFragment extends Fragment {

    //private ListView listView;
    //private ArrayList<String> exCatList =  new ArrayList<String>();
    ExpandableListView expandableListView;
    List<ArrayList> listGroup = new ArrayList<>(); //where we place the categories (legs, chest, glutes, etc)
    HashMap<String,List<String>> listItem;
    MainAdapter adapter;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public ExercisesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExercisesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExercisesFragment newInstance(String param1, String param2) {
        ExercisesFragment fragment = new ExercisesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        expandableListView = view.findViewById(R.id.expandable_listview);
        listGroup = new ArrayList<>();
        listItem = new HashMap<>();
        initListData();




//        listView = (ListView) view.findViewById(R.id.listExerciseCategory);
//        exCatList.add("legs");
//        exCatList.add("glutes");
//        exCatList.add("chest");
//
//        CustomAdapter customAdapter = new CustomAdapter();
//        listView.setAdapter(customAdapter);

    }

    private void initListData() {

        //optimize data architecture later
        ArrayList<String> group1 = new ArrayList();
        ArrayList<String> group2 = new ArrayList();
        ArrayList<String> group3 = new ArrayList();
        ArrayList<String> group4 = new ArrayList();
        ArrayList<String> group5 = new ArrayList();

        listGroup.add(group1); //add the groups here
        listGroup.add(group2);
        listGroup.add(group3);
        listGroup.add(group4);
        listGroup.add(group5);

        for(int i = 0; i < listGroup.size(); i++) {
            for (int j = 0; j < 5; j++)
            listGroup.get(i).add("subgroup" + j);
        }
    }

    /**
    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return exCatList.size();  //or should we use .size() -1?
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.custom_layout_ex_ategory, null);

            TextView textView_title = (TextView) convertView.findViewById(R.id.textView_exCatItem);

            textView_title.setText(exCatList.get(position));
            return convertView;
        }

    }
     **/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exercises, container, false);

    }

}