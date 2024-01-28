package com.example.todolist

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter

import androidx.appcompat.app.AlertDialog


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
            fileHelper.writeData(itemList, applicationContext)
            arrayAdapter.notifyDataSetChanged()
        }

//        xoa item
        lvCongViec.setOnItemClickListener { parent, view, position, id ->
            var alert = AlertDialog.Builder(this)
            alert.setTitle("Delete")
            alert.setMessage("Bạn đã hoàn thành công việc, nhấn yes để xóa!")
            alert.setCancelable(true)
            alert.setPositiveButton("Yes", DialogInterface.OnClickListener { diglog, which ->
                itemList.removeAt(position)
                arrayAdapter.notifyDataSetChanged()
//                ghi danh sach moi vao tep khach hang
                fileHelper.writeData(itemList, applicationContext)
            })
            alert.setNegativeButton("No", DialogInterface.OnClickListener { diglog, which ->
               diglog.cancel()
            })
            alert.create()
            alert.show()
        }
    }
}