package com.example.instabus.db

import android.provider.BaseColumns


import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.instabus.model.Memory


class MemoryDbHelper(context: Context?) :
    SQLiteOpenHelper(
        context,
        DATABASE_NAME,
        null,
        DATABASE_VERSION
    ) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        i: Int,
        i1: Int
    ) { //This method has been intentionally left empty. There is only one version of the database.
    }

    fun readAllMemories(): Cursor {
        val db = readableDatabase
        return db.query(
            MemoryContract.MemoryEntry.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )
    }

    fun addMemory(memory: Memory): Boolean {
        val db = writableDatabase
        val values = ContentValues()
        values.put(MemoryContract.MemoryEntry.COLUMN_TITLE, Memory.getTitle(memory))
        values.put(MemoryContract.MemoryEntry.COLUMN_IMAGE, Memory.getImageAsString(memory))
        return db.insert(MemoryContract.MemoryEntry.TABLE_NAME, null, values) != -1L
    }

    companion object : BaseColumns{
        private const val TEXT_TYPE = " TEXT"
        private const val INTEGER_TYPE = " INTEGER"
        private const val COMMA_SEP = " ,"
        private const val DATABASE_NAME = " memories.db"
        private const val DATABASE_VERSION = 1
        private val SQL_CREATE_ENTRIES =
            "CREATE TABLE " + MemoryContract.MemoryEntry.TABLE_NAME + " (" +
                     MemoryContract.MemoryEntry._ID  + INTEGER_TYPE + " PRIMARY KEY" + COMMA_SEP +
                    MemoryContract.MemoryEntry.COLUMN_TITLE + TEXT_TYPE + COMMA_SEP +
                    MemoryContract.MemoryEntry.COLUMN_IMAGE + TEXT_TYPE + " )"
    }
}
