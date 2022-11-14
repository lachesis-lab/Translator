package ru.lachesis.translator.view.main.rvadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.lachesis.translator.R
import ru.lachesis.translator.model.data.DataModel
import java.util.zip.Inflater

class MainAdapter(
    private var onListItemClickListener: OnListItemClickListener,
    private var data: List<DataModel>

) : RecyclerView.Adapter<MainAdapter.RecyclerViewHolder>() {

    fun setData(data: List<DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }
    inner class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: DataModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.findViewById<TextView>(R.id.rv_header).text = data.text
                itemView.findViewById<TextView>(R.id.rv_text).text =
                    data.meanings?.get(0)?.translation?.translation
                itemView.setOnClickListener { openInNewWindow(data) }
            }
        }

    }
    private fun openInNewWindow(listItemData: DataModel) {
        onListItemClickListener.onItemClick(listItemData)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_item,parent,false) as View)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(data.get(position))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface OnListItemClickListener {
        fun onItemClick(data: DataModel)
    }

}