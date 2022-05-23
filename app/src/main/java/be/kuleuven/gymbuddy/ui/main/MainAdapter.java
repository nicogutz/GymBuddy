package be.kuleuven.gymbuddy.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import be.kuleuven.gymbuddy.R;
import be.kuleuven.gymbuddy.ui.objects.MuscleGroup;

//depending on implementation we might just need to override the getCount() and getView()

public class MainAdapter extends BaseExpandableListAdapter {

    Context context;
    ArrayList<MuscleGroup> muscleGroups;

    public MainAdapter(Context context,
                       ArrayList<MuscleGroup> muscleGroups) {
        this.context = context;
        this.muscleGroups = muscleGroups;
    }


    @Override
    public int getGroupCount() {
        return muscleGroups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.muscleGroups.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.muscleGroups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.muscleGroups.get(groupPosition).get(childPosition);
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

    @Override
    public View getGroupView(int groupPosition,
                             boolean isExpanded,
                             View convertView,
                             ViewGroup parent) {
        String group =  getGroup(groupPosition).toString();
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.custom_layout_ex_ategory, null);
        }

        TextView textView = convertView.findViewById(R.id.list_parent);
        textView.setText(group);
        return convertView;

    }

    @Override
    public View getChildView(int groupPosition,
                             int childPosition,
                             boolean isLastChild,
                             View convertView,
                             ViewGroup parent) {
        String child = (String) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.exercise_item, null);
        }
        TextView textView = convertView.findViewById(R.id.list_child);
        textView.setText(child);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
