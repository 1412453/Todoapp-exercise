package com.a1412453.todoapp.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.a1412453.todoapp.R;
import com.a1412453.todoapp.activities.DetailActivity;
import com.a1412453.todoapp.models.CustomDate;
import com.a1412453.todoapp.models.Task;
import com.a1412453.todoapp.utils.Utils;

public class EditItemFragment extends DialogFragment {

    private static final String TAG = "EditItemFragment";
    Spinner mPriorityLevelSpinner,mStatusSpinner;
    DatePicker mDueDatePicker;
    Task mTask;
    ArrayAdapter mStatusAdapter, mPriorityLevelAdapter;
    EditText mEdTaskName, mEdTaskNotes;
    String mMode;
    CustomDate mCustomDate;

    public interface EditItemListener {
        public void onFinish(Task task);
    }

    EditItemListener mListener=null;
    public void setCustomObjectListener(EditItemListener listener){
        this.mListener = listener;
    }


    public static EditItemFragment newInstance(Task task) {
        EditItemFragment frag = new EditItemFragment();
        Bundle args = new Bundle();
        args.putString(Utils.FRAGMENT_MODE,"hi");
        if (task!=null) {
            args.putSerializable(Utils.ITEM_TASK,task);
            //args.putString(Utils.FRAGMENT_MODE,"hi");
        }
        frag.setArguments(args);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_edit_item, container, false);

        try {
            mTask = (Task) getArguments().getSerializable(Utils.ITEM_TASK);
        } catch(NullPointerException e){
            e.printStackTrace();
        }

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
        //put it here
        /*try {
            mTask = (Task) getArguments().getSerializable(Utils.ITEM_TASK);
        } catch(NullPointerException e){
            e.printStackTrace();
        }*/
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
            //process here
            if (mListener!=null) {
                mTask.setName(mEdTaskName.getText().toString());
                mCustomDate= new CustomDate(mDueDatePicker.getDayOfMonth(),mDueDatePicker.getMonth(),mDueDatePicker.getYear());
                mTask.setDate(mCustomDate.convert2String());
                mTask.setNotes(mEdTaskNotes.getText().toString());
                mTask.setPriorityLevel(mPriorityLevelSpinner.getSelectedItem().toString());
                mTask.setStatus(mStatusSpinner.getSelectedItem().toString());
                mListener.onFinish(mTask);
            }
            dismiss();
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

        mEdTaskName = (EditText) view.findViewById(R.id.ed_task_name);
        mEdTaskNotes = (EditText) view.findViewById(R.id.ed_task_notes);

        mPriorityLevelSpinner= (Spinner) view.findViewById(R.id.spinner_priority_level);
        ArrayAdapter mPriorityLevelAdapter = ArrayAdapter.createFromResource(getActivity(),R.array.priority_level,android.R.layout.simple_spinner_item);
        mPriorityLevelSpinner.setAdapter(mPriorityLevelAdapter);
        //String value = mPriorityLevelSpinner.getSelectedItem().toString();

        mStatusSpinner= (Spinner) view.findViewById(R.id.spinner_status);
        ArrayAdapter mStatusAdapter = ArrayAdapter.createFromResource(getActivity(),R.array.status,android.R.layout.simple_spinner_item);
        mStatusSpinner.setAdapter(mStatusAdapter);

        mDueDatePicker = (DatePicker) view.findViewById(R.id.datepicker_due_date);
        /*mDueDatePicker.getDayOfMonth();
        mDueDatePicker.getMonth();
        mDueDatePicker.getYear();*/

    }

    @Override
    public void onResume() {
        super.onResume();

        try {
            if (mTask!=null){
                mEdTaskName.setText(mTask.getName());
                mEdTaskNotes.setText(mTask.getNotes());
                mCustomDate = new CustomDate(mTask.getDate());
                mDueDatePicker.updateDate(mCustomDate.getYear(), mCustomDate.getMonth(), mCustomDate.getMonth());
                int spinnerStatusPos = mStatusAdapter.getPosition(mTask.getStatus());
                mStatusSpinner.setSelection(spinnerStatusPos);
                int spinnerPriorLvPos = mPriorityLevelAdapter.getPosition(mTask.getPriorityLevel());
                mStatusSpinner.setSelection(spinnerPriorLvPos);
            } else {
                mTask = new Task();
            }
        } catch (NullPointerException e){
            e.printStackTrace();
        }

    }

}
