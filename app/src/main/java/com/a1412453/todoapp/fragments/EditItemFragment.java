package com.a1412453.todoapp.fragments;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.a1412453.todoapp.R;

public class EditItemFragment extends Fragment {

    private Context mContext;
    Spinner mPriorityLevelSpinner,mStatusSpinner;
    DatePicker mDueDatePicker;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set Action-bar for the fragment
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_item, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPriorityLevelSpinner= (Spinner) view.findViewById(R.id.spinner_priority_level);
        ArrayAdapter priorityLevelAdapter = ArrayAdapter.createFromResource(getActivity(),R.array.priority_level,android.R.layout.simple_spinner_item);
        mPriorityLevelSpinner.setAdapter(priorityLevelAdapter);
        //String value = mPriorityLevelSpinner.getSelectedItem().toString();

        mStatusSpinner= (Spinner) view.findViewById(R.id.spinner_status);
        ArrayAdapter statusAdapter = ArrayAdapter.createFromResource(getActivity(),R.array.status,android.R.layout.simple_spinner_item);
        mStatusSpinner.setAdapter(statusAdapter);

        mDueDatePicker = (DatePicker) view.findViewById(R.id.datepicker_due_date);
        //mDueDatePicker.getDayOfMonth();
        //mDueDatePicker.getMonth();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar.
        //inflater.inflate(R.menu.menu_main, menu);
        //super.onCreateOptionsMenu(menu, inflater);

        if (menu != null) {
            menu.findItem(R.id.action_add_task).setVisible(false);
            menu.findItem(R.id.action_cancel_task).setVisible(true);
            menu.findItem(R.id.action_save_task).setVisible(true);
            menu.findItem(R.id.action_delete_task).setVisible(false);
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

}
