package com.example.catbreedsquizgame.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.catbreedsquizgame.database.BreedsDatabase;
import com.example.catbreedsquizgame.model.BreedsModel;

import java.util.ArrayList;

//pull data
public class BreedDAO {
    public ArrayList<BreedsModel> getRandom5Question(BreedsDatabase database){
        ArrayList<BreedsModel> modelArrayList = new ArrayList<>();
        SQLiteDatabase liteDatabase = database.getWritableDatabase();
        //read data line by line by using SQL
        Cursor cursor = liteDatabase.rawQuery("SELECT * FROM cat_breeds ORDER BY RANDOM () LIMIT 5",null);

        int flagIdIndex = cursor.getColumnIndex("id");
        int flagNameIndex = cursor.getColumnIndex("name");
        int flagImageIndex = cursor.getColumnIndex("image");

        while (cursor.moveToNext()){
            //depend on what data type of that column, then use cursor get the data by its data type
            BreedsModel model = new BreedsModel(cursor.getInt(flagIdIndex),cursor.getString(flagNameIndex),cursor.getString(flagImageIndex));
            modelArrayList.add(model);
        }
        return  modelArrayList;
    }
    public ArrayList<BreedsModel> getRandom3WrongAns(BreedsDatabase database, int breedId){
        ArrayList<BreedsModel> modelArrayList = new ArrayList<>();
        SQLiteDatabase liteDatabase = database.getWritableDatabase();
        //read data line by line by using SQL
        Cursor cursor = liteDatabase.rawQuery("SELECT * FROM cat_breeds WHERE id != "+breedId+" ORDER BY RANDOM () LIMIT 3",null);

        int flagIdIndex = cursor.getColumnIndex("id");
        int flagNameIndex = cursor.getColumnIndex("name");
        int flagImageIndex = cursor.getColumnIndex("image");

        while (cursor.moveToNext()){
            //depend on what data type of that column, then use cursor get the data by its data type
            BreedsModel model = new BreedsModel(cursor.getInt(flagIdIndex),cursor.getString(flagNameIndex),cursor.getString(flagImageIndex));
            modelArrayList.add(model);
        }
        return  modelArrayList;
    }
}
