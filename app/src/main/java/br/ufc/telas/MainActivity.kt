package br.ufc.telas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private lateinit var listItems : ArrayList<Item>
    private lateinit var rvAdapter : RecyclerView.Adapter<*>
    private lateinit var rvManager : RecyclerView.LayoutManager
    private lateinit var btnNew : Button
    private lateinit var btnEdit : Button
    private lateinit var etId : EditText

    private lateinit var rvItems : RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvItems = findViewById(R.id.rv_items)



        listItems = ArrayList()
        rvManager = LinearLayoutManager(this)
        rvAdapter = RecyclerAdapter(listItems)

        rvItems.apply {
            setHasFixedSize(false)
            layoutManager = rvManager
            adapter = rvAdapter
        }

        btnNew.setOnClickListener {
            val intent = Intent(this,AddActivity::class.java)
           // startActivityForResult(intent)
        }













    }
}