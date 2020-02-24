package com.example.instabus.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CursorAdapter
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import com.example.instabus.R
import com.example.instabus.db.MemoriesAdapter
import com.example.instabus.db.MemoryDbHelper


class SecondActivity : AppCompatActivity() {
    private var dbHelper: MemoryDbHelper? = null
    private var gridView: GridView? = null

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        gridView = findViewById(R.id.activity_main_grid_view) as GridView?
        dbHelper = MemoryDbHelper(this)
        gridView!!.adapter = MemoriesAdapter(this, dbHelper!!.readAllMemories(), false)
        gridView!!.emptyView = findViewById(R.id.activity_main_empty_view)
    }

     override fun onResume() {
        super.onResume()
        (gridView!!.adapter as CursorAdapter).swapCursor(dbHelper!!.readAllMemories())
    }

    fun addNewMemory(view: View?) {
        val intent = Intent(this, NewMemoryActivity::class.java)
        startActivity(intent)
    }
}
