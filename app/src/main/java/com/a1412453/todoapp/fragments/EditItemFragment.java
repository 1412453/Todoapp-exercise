package com.a1412453.todoapp.fragments;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.a1412453.todoapp.R;

public class EditItemFragment extends DialogFragment {

    private static final String TAG = "EditItemFragment";
    Spinner mPriorityLevelSpinner,mStatusSpinner;
    DatePicker mDueDatePicker;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_edit_item, container, false);

        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar_fragment_edit);
        toolbar.setTitle("Listly");
        toolbar.setTitleTextColor(ContextCompat.getColor(getContext(), R.color.color_text_logo));
        toolbar.setLogo(getResources().getDrawable(R.mipmap.ic_listly));

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        /*if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(android.R.drawable.ic_menu_close_clear_cancel);
        }*/
        setHasOptionsMenu(true);
        return rootView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        getActivity().getMenuInflater().inflate(R.menu.menu_edit_task, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_save_task) {

            return true;
        } else if (id == android.R.id.home || id == R.id.action_cancel_task) {
            // handle close button click here
            dismiss();
            return true;
        }

        return super.onOptionsItemSelected(item);
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

}
