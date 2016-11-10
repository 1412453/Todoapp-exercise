package com.a1412453.todoapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.a1412453.todoapp.R;

/**
 * Created by Pham Minh Sang on 10/11/2016.
 */
public class ListItemFragment extends Fragment {


    private Context mContext;

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
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar.
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);

        if (menu != null) {
            menu.findItem(R.id.action_add_task).setVisible(true);
            menu.findItem(R.id.action_cancel_task).setVisible(false);
            menu.findItem(R.id.action_save_task).setVisible(false);
            menu.findItem(R.id.action_delete_task).setVisible(false);
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

}
