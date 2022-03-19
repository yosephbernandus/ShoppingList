package mobile.android.shoppinglist.ui.shoppinglist

import mobile.android.shoppinglist.data.db.entities.ShoppingItem

interface AddDialogListener {
    fun onAddButtonClicked(item: ShoppingItem)
}