package com.example.instabus.db

import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.instabus.R
import com.example.instabus.model.Memory


class MemoriesAdapter(
    context: Context?,
    cursor: Cursor?,
    autoRequery: Boolean
) :
    CursorAdapter(context, cursor, autoRequery) {
    override fun newView(
        context: Context,
        cursor: Cursor,
        viewGroup: ViewGroup
    ): View {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.memory_list_item, viewGroup, false)
        view.tag = ViewHolder(view)
        return view
    }

    override fun bindView(
        view: View,
        context: Context,
        cursor: Cursor
    ) {
        val holder = view.tag as ViewHolder
        val memory = Memory(cursor)
        holder.titleTextView.setText(Memory.getTitle(memory))
        holder.imageView.setImageBitmap(memory.getImage())
    }

    private inner class ViewHolder internal constructor(view: View) {
        val imageView: ImageView
        val titleTextView: TextView

        init {
            imageView = view.findViewById(R.id.list_item_image_view)
            titleTextView = view.findViewById(R.id.list_item_text_view)
        }
    }
}
