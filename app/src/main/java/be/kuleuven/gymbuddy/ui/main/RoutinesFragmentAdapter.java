package be.kuleuven.gymbuddy.ui.main;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

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
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.routineList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.routineList.get(groupPosition).savedExercises;
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
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.custom_layout_ex_ategory, null);
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
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.routine_subgroup, null);

            TableLayout tableLayout = (TableLayout) convertView.findViewById(
                    R.id.routineExerciseListTable);

            ArrayList<String> savedExercises = routineList.get(groupPosition).savedExercises;

            for (String name : savedExercises) {
                TableRow tableRow = new TableRow(context);
                tableRow.setLayoutParams(
                        new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.WRAP_CONTENT));

                TextView nameTextView = new TextView(context);
                nameTextView.setText(name);
                nameTextView.setLayoutParams(
                        new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                tableRow.addView(nameTextView);

                makeEditText(name, tableRow);
                makeEditText(name, tableRow);
                makeEditText(name, tableRow);

                tableLayout.addView(tableRow,
                        new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                                TableLayout.LayoutParams.WRAP_CONTENT));
            }

            Button recordButton = (Button) convertView.findViewById(R.id.record_button);
            Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

            DatePickerDialog datePickerDialog = new DatePickerDialog(context, (l, y, m, d) -> {
                calendar.set(y, m, d);
            },
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));

            recordButton.setOnClickListener(v -> {
                for (int i = 1; i < tableLayout.getChildCount(); i++) {
                    TableRow row = (TableRow) tableLayout.getChildAt(i);
                    String name = (String) ((TextView) row.getChildAt(0)).getText().toString();
                    // Who says Java is verbose
                    int sets = Integer.parseInt(
                            (String) ((TextView) row.getChildAt(1)).getText().toString());
                    int reps = Integer.parseInt(
                            (String) ((TextView) row.getChildAt(2)).getText().toString());
                    int weight = Integer.parseInt(
                            (String) ((TextView) row.getChildAt(3)).getText().toString());
                }
                datePickerDialog.show();
            });

        }

        return convertView;
    }

    private void makeEditText(String name, TableRow tableRow) {
        EditText editText = new EditText(context);
        editText.setText("0");
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        tableRow.addView(editText);
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
