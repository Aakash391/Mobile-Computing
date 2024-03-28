package com.example.weatherapp



import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class Database (context: Context?) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION){
    override fun onCreate(db: SQLiteDatabase?) {
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + DATE + " TEXT PRIMARY KEY,"
                + MAX_TEMP + " TEXT,"
                + MIN_TEMP + " TEXT)")
        // at last we are calling a exec sql method to execute above sql query
        if (db != null) {
            db.execSQL(query)
        }
    }
    fun addtemp(date:String?,
                maxtemp:String?,
                mintemp:String?){

        val db=this.writableDatabase
        val values = ContentValues()
        values.put(DATE,date)
        values.put(MAX_TEMP,maxtemp.toString())
        values.put(MIN_TEMP,mintemp.toString())

        db.insert(TABLE_NAME,null,values)
        Log.d("InsertingMax",maxtemp.toString())
        Log.d("InsertingMin",mintemp.toString())
//        db.close()

    }

    fun readtemp(date:String):ArrayList<String>{
        val db = this.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $DATE = ?", arrayOf(date))

        var list: ArrayList<String> = ArrayList()
        if(cursor.moveToFirst()){
            val readDate = cursor.getString(0)
            val readMax = cursor.getString(1)
            val readMin = cursor.getString(2)
            list.add(readDate.toString())
            list.add(readMax.toString())
            list.add(readMin.toString())

            Log.d("Retrieved Max",readMax.toString())
            Log.d("Retrieved Min",readMin.toString())
        }



        return list

    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    companion object {
        private const val DB_NAME = "Weather"
        private const val DB_VERSION = 1
        private const val TABLE_NAME = "Temperature"
//        private const val ID_COL = "id"
        private const val DATE = "date"
        private const val MAX_TEMP = "maxtemp"
        private const val MIN_TEMP = "mintemp"
    }

}