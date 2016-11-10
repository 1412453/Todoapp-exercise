package com.a1412453.todoapp.adapters;

import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.a1412453.todoapp.R;
import com.a1412453.todoapp.models.Task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Pham Minh Sang on 09/11/2016.
 */
public class TodoListAdapter extends BaseAdapter {


    private int mLayout;
    private LayoutInflater mRootInflater;
    protected List<Task> listTask; //this is the list of the adapter

    public TodoListAdapter(int mLayout, LayoutInflater mRootInflater) {
        this.mLayout = mLayout;
        this.mRootInflater = mRootInflater;

        listTask = new ArrayList<>();
    }


    @Override
    public int getCount() {
        return listTask.size();
    }

    @Override
    public Object getItem(int i) {
        return listTask.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Task task = listTask.get(position);
        view = populateView(position,view,viewGroup,mRootInflater,mLayout,task);
        return view;
    }

    protected View populateView(int position, View view, ViewGroup viewGroup, LayoutInflater inflater, int layout, Task task){
        TaskItemHolder holder;
        if (view == null) {
            view = inflater.inflate(layout, viewGroup, false);
            holder = new TaskItemHolder();
            holder.text_task_name = (TextView) view.findViewById(R.id.text_item_task_name);
            holder.text_status = (TextView) view.findViewById(R.id.text_item_task_status);
            view.setTag(holder);
        } else {
            holder = (TaskItemHolder) view.getTag();
        }

        holder.text_status.setText(task.getStatus());
        holder.text_task_name.setText(task.getName());

        return view;
    }

    private static class TaskItemHolder {
        TextView text_task_name, text_status;
    }

}
