package com.example.instabus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView


class ResponseAdapter(
    val context: List<NearstationsItem>?): RecyclerView.Adapter<ResponseAdapter.ViewHolder>()
   // val data: List<NearstationsItem>)
{
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    val street_name = itemView.findViewById<TextView>(R.id.street_name)

}

    override fun getItemCount() = context!!.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.response_row, parent, false)
        return ViewHolder(view)

    }




    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            with(holder) {
                street_name?.let {
                it.text = context!![position].street_name
                it.contentDescription = context!![position].street_name
            }

        }
    }
}

