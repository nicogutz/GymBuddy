package be.kuleuven.gymbuddy.ui.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;

import be.kuleuven.gymbuddy.R;
import be.kuleuven.gymbuddy.data.model.ExerciseValue;

//depending on implementation we might just need to override the getCount() and getView()

@SuppressWarnings("SuspiciousMethodCalls")
public class ExercisesFragmentAdapterChecked extends BaseExpandableListAdapter {

    private Context context;
    private Map<String, List<ExerciseValue>> exercisesGroupedByMuscle;
    private Object[] keyArray;
    private List<String> savedExercisesList;

    public ExercisesFragmentAdapterChecked(Context context,
                                           Map<String, List<ExerciseValue>> exercisesGroupedByMuscle,
                                           List<String> savedExerciseslist) {
        this.savedExercisesList = savedExerciseslist;
        this.context = context;
        this.exercisesGroupedByMuscle = exercisesGroupedByMuscle;

        keyArray = exercisesGroupedByMuscle.keySet().toArray();
    }

    static String splitCamelCase(String s) {
        return s.replaceAll(
                String.format("%s|%s|%s",
                        "(?<=[A-Z])(?=[A-Z][a-z])",
                        "(?<=[^A-Z])(?=[A-Z])",
                        "(?<=[A-Za-z])(?=[^A-Za-z])"
                ),
                " "
        );
    }

    @Override
    public int getGroupCount() {
        return exercisesGroupedByMuscle.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.exercisesGroupedByMuscle.get(keyArray[groupPosition]).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.exercisesGroupedByMuscle.get(keyArray[groupPosition]);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return Objects.requireNonNull(this.exercisesGroupedByMuscle.get(keyArray[groupPosition]))
                      .get(childPosition);
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
        String group = splitCamelCase(keyArray[groupPosition].toString());
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.custom_layout_ex_ategory, null);
        }

        TextView textView = convertView.findViewById(R.id.list_parent);
        textView.setText(group);
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
            convertView = layoutInflater.inflate(R.layout.exercise_item, null);
        }
        CheckBox checkBox = convertView.findViewById(R.id.checkBox);
        checkBox.setVisibility(View.VISIBLE);
        checkBox.setChecked(savedExercisesList.stream().anyMatch(i -> i.equals(child)));
//        checkBox.setText(child);
//
//        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> buttonView.getParent());
        TextView textView = convertView.findViewById(R.id.list_child);
        textView.setText(child);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public void filterData(String query) {
    }

}
