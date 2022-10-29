package com.derpy.inventorymanagement

import kotlin.random.Random

data class Dimensions(val width: Int, val height: Int)

val colors = listOf<String>("Orange", "Red", "Green", "Yellow", "Purple")

open class PurchasableItem() {
    val dimensions: Dimensions = Dimensions(Random.nextInt(2, 10), Random.nextInt(2, 5))
    val price: Int = Random.nextInt(20, 250)
    open val name: String = ""
    open val description: String = ""
}

class Table() : PurchasableItem() {
    private val color: String = colors[Random.nextInt(colors.size)]
    override val name: String = "$color Table"
    val area: Int get() = dimensions.width * dimensions.height
    override val description: String = "Name: ${this.name}, price: ${this.price}"
}

class Dresser() : PurchasableItem() {
    val numDrawers: Int = Random.nextInt(1, 8)
    private val color: String = colors[Random.nextInt(colors.size)]
    override val name: String = "$color Dresser"
    val area: Int get() = dimensions.width * dimensions.height
    override val description: String = "Name: ${this.name}, price: ${this.price}"
}

class User() {
    var money: Int = 500
    var purchasedItems = mutableListOf<PurchasableItem>()

    fun buyItem(item: PurchasableItem) : Boolean {
        if (item.price > money) {
            return false
        }
        purchasedItems.add(item)
        this.money -= item.price
        return true
    }

    fun sellItem(value: Int) : Boolean {
        if (purchasedItems.isEmpty()) {
            return false
        }
        val item = purchasedItems[value]
        this.money += item.price
        purchasedItems.removeAt(value)
        return true
    }

}