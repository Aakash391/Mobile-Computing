package com.example.sensor.ui.theme

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import androidx.compose.ui.graphics.drawscope.DrawScope

class Database(context: Context) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {

        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ROLL + " TEXT,"
                + PITCH + " TEXT,"
                + YAW + " TEXT,"
                + TIME + " TEXT)")

        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun addData(roll:String, pitch:String, yaw:String, time:String){
        val db = this.writableDatabase

        val values = ContentValues()

        values.put(ROLL, roll)
        values.put(PITCH, pitch)
        values.put(YAW, yaw)
        values.put(TIME,time)
        db.insert(TABLE_NAME, null, values)
        Log.d("Database Add",roll+" ,"+pitch+" ,"+yaw+" ,"+time)
        db.close()
    }

    fun readRoll(): ArrayList<String> {

        val db = this.readableDatabase
//        val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $DATE = ?", arrayOf(date))
        val cursor:Cursor =  db.rawQuery("SELECT ROLL FROM $TABLE_NAME", null)
        var list: ArrayList<String> = ArrayList()
        if (cursor.moveToFirst()) {

            do {
                val readRoll = cursor.getString(0)
                list.add(readRoll)
            } while (cursor.moveToNext())
        }
        cursor.close()
        Log.d("Database Read", list.toString())
        return list

    }
    fun readPitch(): ArrayList<String> {

        val db = this.readableDatabase
//        val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $DATE = ?", arrayOf(date))
        val cursor:Cursor =  db.rawQuery("SELECT PITCH FROM $TABLE_NAME", null)
        var list: ArrayList<String> = ArrayList()
        if (cursor.moveToFirst()) {

            do {
                val readPitch = cursor.getString(0)
                list.add(readPitch)
            } while (cursor.moveToNext())
        }
        return list

    }
    fun readYaw(): ArrayList<String> {

        val db = this.readableDatabase
//        val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $DATE = ?", arrayOf(date))
        val cursor:Cursor =  db.rawQuery("SELECT YAW FROM $TABLE_NAME", null)
        var list: ArrayList<String> = ArrayList()
        if (cursor.moveToFirst()) {

            do {
                val readYaw = cursor.getString(0)
                list.add(readYaw)
            } while (cursor.moveToNext())
        }
        return list

    }
    fun readTime(): ArrayList<String> {

        val db = this.readableDatabase
//        val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $DATE = ?", arrayOf(date))
        val cursor:Cursor =  db.rawQuery("SELECT TIME FROM $TABLE_NAME", null)
        var list: ArrayList<String> = ArrayList()
        if (cursor.moveToFirst()) {

            do {
                val readTime = cursor.getString(0)
                list.add(readTime)
            } while (cursor.moveToNext())
        }
        return list

    }
    companion object {
        private const val DB_NAME = "orientation"

        private const val DB_VERSION = 1
        private const val TABLE_NAME = "orientations"
        private const val ID_COL = "id"
        private const val ROLL = "roll"
        private const val PITCH = "pitch"
        private const val YAW = "yaw"
        private const val TIME ="time"
    }
}