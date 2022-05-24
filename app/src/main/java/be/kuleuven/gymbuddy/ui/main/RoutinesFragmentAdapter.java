package be.kuleuven.gymbuddy.ui.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

import be.kuleuven.gymbuddy.R;
import be.kuleuven.gymbuddy.data.local.entities.SavedRoutine;

//depending on implementation we might just need to override the getCount() and getView()

@SuppressWarnings("SuspiciousMethodCalls")
public class RoutinesFragmentAdapter extends BaseExpandableListAdapter {

    Context context;
    ArrayList<SavedRoutine> routineList;

    public RoutinesFragmentAdapter(Context context,
                                   ArrayList<SavedRoutine> routineList) {
        this.context = context;
        this.routineList = routineList;
    }

    @Override
    public int getGroupCount() {
        return routineList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        //return Objects.requireNonNull(this.routinesMap.get(keyArray[groupPosition])).size();
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.routineList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.routineList.get(groupPosition).savedExercises.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @SuppressLint("InflateParams")

    @Override
    public View getGroupView(int groupPosition,
                             boolean isExpanded,
                             View convertView,
                             ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.fragment_routine, null);
        }

        TextView routine_textView = convertView.findViewById(R.id.list_parent);
        routine_textView.setText(routineList.get(groupPosition).name);
        return convertView;

    }

    @SuppressLint("InflateParams")

    @Override
    public View getChildView(int groupPosition,
                             int childPosition,
                             boolean isLastChild,
                             View convertView,
                             ViewGroup parent) {
        String child = (getChild(groupPosition, childPosition).toString());
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.routine_subgroup, null);
        }

//        //exercise 1
//        TextView ex1Name_textView = convertView.findViewById(R.id.ex1_textView);
//        ex1Name_textView.setText(child);
//
//        TextView sets1_textView = convertView.findViewById(R.id.sets1);
//        ex1Name_textView.setText(child);
//
//        TextView reps1_textView = convertView.findViewById(R.id.reps1);
//        ex1Name_textView.setText(child);
//
//        TextView w1_textView = convertView.findViewById(R.id.weight1);
//        ex1Name_textView.setText(child);
//
//        //exercise2
//        TextView ex2Name_textView = convertView.findViewById(R.id.ex2_textView);
//        ex1Name_textView.setText(child);
//
//        TextView sets2_textView = convertView.findViewById(R.id.sets2);
//        ex1Name_textView.setText(child);
//
//        TextView reps2_textView = convertView.findViewById(R.id.reps2);
//        ex1Name_textView.setText(child);
//
//        TextView w2_textView = convertView.findViewById(R.id.weight2);
//        ex1Name_textView.setText(child);
//
//        //make till exercise10(?)
//
//        //buttons
//        Button buttonRecord = convertView.findViewById(R.id.record_button);
//        buttonRecord.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                View view;
//                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(
//                        Context.LAYOUT_INFLATER_SERVICE);
//                view = layoutInflater.inflate(R.layout.fragment_calendar, null);
//            }
//        });
//
//        Button buttonEdit = convertView.findViewById(R.id.edit_routine_button);
//        buttonEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //make checkboxes visible now?
//                View view;
//                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(
//                        Context.LAYOUT_INFLATER_SERVICE);
//                view = layoutInflater.inflate(R.layout.fragment_exercises, null);
//
//            }
//        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
