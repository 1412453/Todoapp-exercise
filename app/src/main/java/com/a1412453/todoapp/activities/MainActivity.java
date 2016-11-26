package com.a1412453.todoapp.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.a1412453.todoapp.R;
import com.a1412453.todoapp.adapters.TodoListAdapter;
import com.a1412453.todoapp.fragments.EditItemFragment;
import com.a1412453.todoapp.libs.DatabaseHelper;
import com.a1412453.todoapp.models.Task;
import com.a1412453.todoapp.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String mBackStateName;
    private Menu mMenu;
    private ListView mLvTask;
    private TodoListAdapter mTaskAdapter;
    private DatabaseHelper mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(), R.color.color_text_logo));
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(R.mipmap.ic_listly);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name).toString());

        //showFragment(new ListItemFragment());
    }

    @Override
    public void onResume() {
        super.onResume();

        mDatabase = DatabaseHelper.getInstance(getApplicationContext());

        mLvTask = (ListView) findViewById(R.id.listview_task);

        updateList();

        mLvTask.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent mIntent = new Intent(MainActivity.this,DetailActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable(Utils.ITEM_TASK,(Task) mTaskAdapter.getItem(i));
                mIntent.putExtras(mBundle);
                startActivity(mIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        mMenu = menu;
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_task:
                showFragment();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateList(){
        ArrayList<Task> listTasks = mDatabase.getAllTasks();
        mTaskAdapter = new TodoListAdapter(R.layout.item_custom_task,getLayoutInflater(),getBaseContext(),listTasks);
        mLvTask.setAdapter(mTaskAdapter);
    }

    private void showFragment() {
        FragmentManager fm = getSupportFragmentManager();
        EditItemFragment newFragment = EditItemFragment.newInstance(null);
        newFragment.setCustomObjectListener(new EditItemFragment.EditItemListener() {
            @Override
            public void onFinish(Task task) {
                mDatabase.addTask(task);
                updateList();
            }
        });
        FragmentTransaction ft = fm.beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.add(android.R.id.content, newFragment).addToBackStack(null).commit();
    }
}
