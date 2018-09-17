package com.example.android.restful.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.restful.model.DataItem;

import java.util.ArrayList;
import java.util.List;

public class DataSource {
    private Context mContext;
    private SQLiteDatabase mDatabase;
    SQLiteOpenHelper mDbHelper;

    public DataSource(Context context){
        this.mContext = context;
        mDbHelper = new DBHelper(mContext);
        mDatabase = mDbHelper.getWritableDatabase();
    }
    public void open(){
        mDatabase = mDbHelper.getWritableDatabase();
    }
    public void close(){
        mDbHelper.close();
    }
    public DataItem createItem(DataItem item){
        ContentValues values = item.toValues();
        mDatabase.insert(ItemsTable.TABLE_ITEMS, null, values);
        return item;
    }
    public long getDataItemsCount(){
        return DatabaseUtils.queryNumEntries(mDatabase, ItemsTable.TABLE_ITEMS);
    }
    public void seedDatabase(List<DataItem> dataItemList){
        long numItems = getDataItemsCount();
        if(numItems==0){
            for (DataItem item : dataItemList){
                createItem(item);
            }
        }
    }
    public List<DataItem> getAllItems(String category){
        List<DataItem> dataItems = new ArrayList<>();
        Cursor cursor = null;
        if(category==null){
            cursor=mDatabase.query(ItemsTable.TABLE_ITEMS, ItemsTable.ALL_COLUMNS, null,null,null,null,ItemsTable.COLUMN_NAME);

        }else {
            String[] categories = {category};
            cursor = mDatabase.query(ItemsTable.TABLE_ITEMS, ItemsTable.ALL_COLUMNS, ItemsTable.COLUMN_CATEGORY + "?", categories, null,null, ItemsTable.CR);

        }
        while (cursor.moveToNext()){
            DataItem item = new DataItem();
            item.setItemId(cursor.getString(cursor.getColumnIndex(ItemsTable.COLUMN_ID)));

            dataItems.add(item);

        }
        cursor.close();
        return dataItems;
    }
}