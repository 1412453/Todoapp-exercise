package com.a1412453.todoapp.activities;

import android.app.DatePickerDialog;
import android.support.v4.content.ContextCompat;
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

import com.a1412453.todoapp.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(), R.color.color_text_logo));
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(R.mipmap.ic_listly);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name).toString());
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_main, menu);


        if (menu != null) {
            menu.findItem(R.id.action_add_task).setVisible(false);
            menu.findItem(R.id.action_cancel_task).setVisible(true);
            menu.findItem(R.id.action_save_task).setVisible(true);
            menu.findItem(R.id.action_delete_task).setVisible(false);
        }

        return true;
    }



}
