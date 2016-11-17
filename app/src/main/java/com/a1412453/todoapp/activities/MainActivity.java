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

import com.a1412453.todoapp.R;
import com.a1412453.todoapp.fragments.EditItemFragment;

public class MainActivity extends AppCompatActivity {

    private String mBackStateName;
    private Menu mMenu;

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
                startActivity(new Intent(MainActivity.this,DetailActivity.class));
                //showFragment(new EditItemFragment());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showFragment(Fragment f) {
        FragmentManager fm = getSupportFragmentManager();
        EditItemFragment newFragment = new EditItemFragment();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.add(android.R.id.content, newFragment).addToBackStack(null).commit();
    }
}
