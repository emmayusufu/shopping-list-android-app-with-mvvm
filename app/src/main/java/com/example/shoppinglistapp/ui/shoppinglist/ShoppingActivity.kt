package com.example.shoppinglistapp.ui.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglistapp.R
import com.example.shoppinglistapp.data.db.entities.ShoppingItem
import com.example.shoppinglistapp.others.ShoppingItemAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ShoppingActivity : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()
    private val factory : ShoppingViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)
        val viewModal = ViewModelProvider(this, factory).get(ShoppingViewModal::class.java)
        val adapter = ShoppingItemAdapter(listOf(), viewModal)
        val rvShoppingItems : RecyclerView = findViewById(R.id.rvShoppingItems)

        rvShoppingItems.layoutManager = LinearLayoutManager(this)
        rvShoppingItems.adapter = adapter

        viewModal.getAllShoppingItems().observe(this, {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            AddShoppingItemDialog(this, object: AddDialogListener {
                override fun onAddButtonClicked(item: ShoppingItem) {
                    viewModal.upsert(item)
                }
            }).show()
        }

    }
}