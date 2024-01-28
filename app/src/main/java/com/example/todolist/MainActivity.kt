package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter

class MainActivity : AppCompatActivity() {
    var itemList = ArrayList<String>()
    var fileHelper = FileHelper()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemList = fileHelper.readData(this)
        var arrayAdapter = ArrayAdapter(this,
        android.R.layout.simple_list_item_1,
        android.R.id.text1, itemList)
        lvCongViec.adapter = arrayAdapter

        btnAdd.setOnClickListener {
            var itemName = edtInput.text.toString()
            itemList.add(itemName)
            edtInput.setText("")
        }
    }
}