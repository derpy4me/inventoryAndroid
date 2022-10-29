package com.derpy.inventorymanagement

import android.content.Context
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import kotlin.system.exitProcess

fun addButton(context: Context, text: String, execute : () -> Boolean) : Button {
    val button = Button(context)
    button.text = text
    button.setOnClickListener {
        execute()
    }
    button.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    return button
}

fun itemButton(context: Context, text: String, item: PurchasableItem, execute : (item: PurchasableItem) -> Boolean) : Button {
    val button = Button(context)
    button.text = text
    button.setOnClickListener {
        execute(item)
    }
    button.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    return button
}

fun exitButton(context: Context) : Button {
    val button = Button(context)
    button.text = "Exit"
    button.setOnClickListener {
        exitProcess(0)
    }
    button.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    return button
}

fun addText(context: Context, text: String) : TextView {
    val view = TextView(context)
    view.text = text
    view.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    return view
}