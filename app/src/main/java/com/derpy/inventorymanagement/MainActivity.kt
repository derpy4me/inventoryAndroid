package com.derpy.inventorymanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var menuText: TextView? = null
    private var moneyText: TextView? = null
    private var layoutTop: LinearLayout? = null
    private var layoutBottom: LinearLayout? = null
    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.menuText = findViewById<TextView>(R.id.actionTitle)
        this.moneyText = findViewById<TextView>(R.id.userMoney)
        this.layoutTop = findViewById<LinearLayout>(R.id.linLayoutTop)
        this.layoutBottom  = findViewById<LinearLayout>(R.id.linLayoutBottom)
        this.user = User()
        this.moneyText?.text = user?.money.toString()
        mainLoop()
    }

    private fun mainLoop() {
        this.layoutTop?.removeAllViews()
        this.layoutBottom?.removeAllViews()
        val item = showRandomItem()
        showMenu(item)
    }

    private fun showRandomItem() : PurchasableItem {
        val items = listOf<PurchasableItem>(Table(), Dresser())
        val item = items[Random.nextInt(items.size)]
        this.layoutTop?.addView(addText(this, item.description))
        return item
    }

    private fun showMenu(item: PurchasableItem) {
        this.menuText?.text = """Menu"""
        this.layoutBottom?.addView(addButton(this, "Buy Item") { buyItemForUser(item) })
        this.layoutBottom?.addView(addButton(this, "Show Inventory") { showUserInventory() })
//        this.layoutBottom?.addView(addButton(this, "Sell Item"))
        this.layoutBottom?.addView(exitButton(this))
    }

    private fun showUserInventory() : Boolean {
        this.layoutTop?.removeAllViews()
        this.layoutBottom?.removeAllViews()
        user?.purchasedItems?.forEach { item -> this.layoutTop?.addView(addText(this, item.description))}
        user?.purchasedItems?.forEachIndexed() { index, item -> this.layoutBottom?.addView(addButton(this, "Sell ${item.name}") { sellUserItem(index, item)})}
        return true
    }

    private fun buyItemForUser(item: PurchasableItem) : Boolean {
        val purchased = user?.buyItem(item)
        if (purchased!!) {
            this.makeToast("${item.name} purchased")
            this.moneyText?.text = user?.money.toString()
        } else {
            this.makeToast("Unable to purchase item")
        }
        this.mainLoop()
        return true
    }

    private fun sellUserItem(itemIndex: Int, item: PurchasableItem) : Boolean {
        val sold = user?.sellItem(itemIndex)
        if (sold !!) {
            this.makeToast("${item.name} sold")
            this.moneyText?.text = user?.money.toString()
        } else {
            this.makeToast("Unable to sell item")
        }
        this.mainLoop()
        return true
    }

    private fun makeToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}
