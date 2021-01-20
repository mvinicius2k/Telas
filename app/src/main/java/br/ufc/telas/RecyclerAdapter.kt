package br.ufc.telas

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class RecyclerAdapter (private val items: ArrayList<Item>) : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    private val TAG = "RecyclerAdapter"

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val txtLeft: TextView = view.findViewById(R.id.txt_left)
        val txtRight: TextView = view.findViewById(R.id.txt_right)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.MyViewHolder, position: Int) {
        Log.d(TAG,"Adicionado")
        holder.txtLeft.text = items[position].getTypeChar().toString()
        holder.txtRight.text = items[position].name
    }


}