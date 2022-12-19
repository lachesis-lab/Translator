package ru.lachesis.translator.view.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ru.lachesis.translator.R
import ru.lachesis.translator.model.data.DataModel

class HistoryAdapter():RecyclerView.Adapter<HistoryViewHolder>()  {

    private var data: List<DataModel> = arrayListOf()

    fun setData(data: List<DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.history_recyclerview_item, parent, false))
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}

class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(data: DataModel) {
        if (layoutPosition != RecyclerView.NO_POSITION){
            itemView.findViewById<TextView>(R.id.header_history_textview_recycler_item).text = data.text
            itemView.setOnClickListener{Toast.makeText(itemView.context,"on click: ${data.text}",Toast.LENGTH_LONG)}
        }
    }

}
