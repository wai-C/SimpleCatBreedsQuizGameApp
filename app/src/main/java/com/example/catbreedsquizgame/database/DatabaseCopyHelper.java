package com.example.catbreedsquizgame.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseCopyHelper extends SQLiteOpenHelper {

    //The Android's default system path of your app db
    String DB_PATH = null;

    private static String DB_NAME = "catbreedsquizgame.db";
    private SQLiteDatabase myDb;
    private final Context myContext;

    public DatabaseCopyHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.myContext = context;
        DB_PATH = "/data/data/" + context.getPackageName() + "/" + "databases/";
    }

    //Creates a empty db on the system and rewrites it with your own db
    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if (dbExist) {
            //do nothing
        } else {
            this.getReadableDatabase();
            try {
                copyDataBase();
            }catch (IOException e){
                throw new Error("Error copying db");
            }
        }
    }
    private boolean checkDataBase(){
        SQLiteDatabase checkDb = null;
        try{
            String myPath = DB_PATH + DB_NAME;
            checkDb = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READONLY);
        }catch (SQLiteException e){
            //db doesn't exist yet
        }
        if(checkDb!= null){
            checkDb.close();
        }
        return checkDb != null ? true : false;
    }
    private void copyDataBase() throws IOException{
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutPut = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int len;
        while ((len = myInput.read(buffer))>0){
            myOutPut.write(buffer, 0, len);
        }
        myOutPut.flush();
        myOutPut.close();
        myInput.close();
    }
    public void openDataBase() throws SQLException {
        //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDb = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {
        if(myDb != null)
            myDb.close();
        super.close();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
            db.disableWriteAheadLogging();
        }
    }
}
