package ru.lachesis.translator.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.lachesis.translator.R

class MainAdapter: RecyclerView.Adapter<MainAdapter.MainViewHolder>()
 {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item,parent,false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
     inner class MainViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
         fun bind(){

         }

     }}

