package edu.foothill.tlilley.quickchemdraw

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_row.view.*

/**
 * ListAdapter.kt
 * Final Project / CS 64A
 * Foothill College / R.Scovil
 * Trent Lilley / trent.vanlilley@gmail.com
 */

// adapter for our recyclerview, handles appearance and function of  list items
class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var documentList = emptyList<Document>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    // attach recycler view row layout here
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_row, parent, false))

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = documentList[position]
        holder.itemView.tvRecyclerTitle.text = currentItem.title
        holder.itemView.layoutRecyclerRow.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToExistingDrawFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return documentList.size
    }

    fun setData(document: List<Document>) {
        this.documentList = document
        notifyDataSetChanged()
    }
}