package com.a1412453.todoapp.libs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.a1412453.todoapp.models.Task;

import java.util.ArrayList;

/**
 * Created by Pham Minh Sang on 17/11/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "tasksManager";
    private static final String TABLE_PACKAGES = "tasks";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DATE = "date";
    private static final String KEY_NOTES = "notes";
    private static final String KEY_PRIORITY = "priority";
    private static final String KEY_STATUS = "status";


    private static DatabaseHelper mInstance;

    public static synchronized DatabaseHelper getInstance(Context context) {

        if (mInstance == null) {
            mInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return mInstance;
    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PACKAGES_TABLE = "CREATE TABLE " + TABLE_PACKAGES + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT,"
                + KEY_DATE + " TEXT,"
                + KEY_NOTES + " TEXT,"
                + KEY_PRIORITY + " TEXT,"
                + KEY_STATUS + " TEXT" + ")";
        db.execSQL(CREATE_PACKAGES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PACKAGES);
        onCreate(db);
    }

    public void addTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME, task.getName());
        values.put(KEY_PRIORITY, task.getPriorityLevel());
        values.put(KEY_DATE, task.getDate());
        values.put(KEY_NOTES, task.getNotes());
        values.put(KEY_STATUS, task.getStatus());

        db.insert(TABLE_PACKAGES, null, values);
        db.close();
    }

    public void updateTask(Task task){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, task.getId());
        values.put(KEY_NAME, task.getName());
        values.put(KEY_PRIORITY, task.getPriorityLevel());
        values.put(KEY_DATE, task.getDate());
        values.put(KEY_NOTES, task.getNotes());
        values.put(KEY_STATUS, task.getStatus());

        int rows = db.update(TABLE_PACKAGES, values, KEY_ID + "=" + task.getId(),null);
    }

    public void deleteTask(Task task){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_PACKAGES,KEY_ID + "=" + task.getId(),null);

    }

    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> listTask = new ArrayList<Task>();

        String selectQuery = "SELECT * FROM " + TABLE_PACKAGES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Task task = new Task();

                task.setId(Integer.parseInt(cursor.getString(0)));
                task.setName(cursor.getString(1));
                task.setDate(cursor.getString(2));
                task.setNotes(cursor.getString(3));
                task.setPriorityLevel(cursor.getString(4));
                task.setStatus(cursor.getString(5));

                listTask.add(task);
            } while (cursor.moveToNext());
        }

        return listTask;
    }

    public void deleteAllPackages() {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            db.delete(TABLE_PACKAGES, null, null);
            db.setTransactionSuccessful();
        } catch (Exception e) {

        } finally {
            db.endTransaction();
        }
    }

}
