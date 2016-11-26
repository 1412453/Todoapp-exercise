package com.a1412453.todoapp.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.a1412453.todoapp.R;
import com.a1412453.todoapp.fragments.EditItemFragment;
import com.a1412453.todoapp.libs.DatabaseHelper;
import com.a1412453.todoapp.models.Task;
import com.a1412453.todoapp.utils.Utils;

public class DetailActivity extends AppCompatActivity {

    TextView mTvTaskName, mTvDueDate, mTvTaskNotes, mTvPriorityLevel, mTvStatus;
    Task mItemTask;
    DatabaseHelper mDatabase;

    private void attachItemTask(){
        if (mItemTask!=null) {
            mTvTaskName.setText(mItemTask.getName());
            mTvDueDate.setText(mItemTask.getDate());
            mTvTaskNotes.setText(mItemTask.getNotes());
            mTvPriorityLevel.setText(mItemTask.getPriorityLevel());
            mTvStatus.setText(mItemTask.getStatus());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);




        mTvTaskName = (TextView) findViewById(R.id.text_task_name);
        mTvDueDate = (TextView) findViewById(R.id.text_due_date);
        mTvTaskNotes = (TextView) findViewById(R.id.text_task_notes);
        mTvPriorityLevel = (TextView) findViewById(R.id.text_priority_level);
        mTvStatus = (TextView) findViewById(R.id.text_status);

        mItemTask = (Task) getIntent().getSerializableExtra(Utils.ITEM_TASK);
        attachItemTask();
    }

    @Override
    public void onResume() {
        super.onResume();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_activity_detail);
        toolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(), R.color.color_text_logo));
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(R.mipmap.ic_listly);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name).toString());
        //getSupportActionBar().setHomeButtonEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_back);

        /*ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            //actionBar.setHomeAsUpIndicator(android.R.drawable.back);
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       // Inflate the menu; this adds items to the action bar if it is present.
       getMenuInflater().inflate(R.menu.menu_task_detail, menu);
       return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit_task:
                showFragment();
                return true;
            case R.id.action_delete_task:
                //showFragment(new ListItemFragment());
                DatabaseHelper mDatabase = DatabaseHelper.getInstance(getApplicationContext());
                mDatabase.deleteTask(mItemTask);
                startActivity(new Intent(DetailActivity.this,MainActivity.class));
                return true;
            case R.id.action_detail_close:
                startActivity(new Intent(DetailActivity.this,MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showFragment() {
        FragmentManager fm = getSupportFragmentManager();
        EditItemFragment newFragment = EditItemFragment.newInstance(mItemTask);
        newFragment.setCustomObjectListener(new EditItemFragment.EditItemListener() {
            @Override
            public void onFinish(Task task) {
                mItemTask = task;
                attachItemTask();
                //update the database
                mDatabase = DatabaseHelper.getInstance(getApplicationContext());
                mDatabase.updateTask(task);
            }
        });
        FragmentTransaction ft = fm.beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.add(android.R.id.content, newFragment).addToBackStack(null).commit();
    }
}
