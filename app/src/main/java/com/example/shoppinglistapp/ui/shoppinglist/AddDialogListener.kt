package com.example.shoppinglistapp.ui.shoppinglist

import com.example.shoppinglistapp.data.db.entities.ShoppingItem

interface AddDialogListener {

    fun onAddButtonClicked(item: ShoppingItem)

}