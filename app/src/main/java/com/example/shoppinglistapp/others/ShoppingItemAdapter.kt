package com.example.shoppinglistapp.others

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglistapp.R
import com.example.shoppinglistapp.data.db.entities.ShoppingItem
import com.example.shoppinglistapp.ui.shoppinglist.ShoppingViewModal


class ShoppingItemAdapter(
    var items: List<ShoppingItem>,
    private val viewModal: ShoppingViewModal
) :
    RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {


    inner class ShoppingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvAmount: TextView = itemView.findViewById(R.id.tvAmount)
        val ivDelete: ImageView = itemView.findViewById(R.id.ivDelete)
        val ivMinus: ImageView = itemView.findViewById(R.id.ivMinus)
        val ivPlus: ImageView = itemView.findViewById(R.id.ivPlus)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return ShoppingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {

        val curShoppingItem : ShoppingItem = items[position]
        val viewModel: ShoppingViewModal = viewModal

        holder.tvName.text = curShoppingItem.name
        holder.tvAmount.text = "${curShoppingItem.amount}"


        holder.ivDelete.setOnClickListener {
            viewModel.delete(curShoppingItem)
        }

        holder.ivPlus.setOnClickListener {
            curShoppingItem.amount!!.plus(1)
            viewModel.upsert(curShoppingItem)
        }

        holder.ivMinus.setOnClickListener {
            if (curShoppingItem.amount!! > 0) {
                curShoppingItem.amount!!.minus(1)
                viewModel.upsert(curShoppingItem)
            }
        }
    }

    override fun getItemCount() = items.size
}