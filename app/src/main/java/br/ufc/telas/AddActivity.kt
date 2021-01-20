package br.ufc.telas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.recyclerview.widget.RecyclerView

class AddActivity : AppCompatActivity() {

    private lateinit var item : Item

    private lateinit var btnAdd : Button
    private lateinit var rvItems : RecyclerView
    private lateinit var edInsert : EditText
    private lateinit var spnId : Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        btnAdd = findViewById(R.id.btn_add)
        rvItems = findViewById(R.id.rv_items)
        edInsert = findViewById(R.id.et_insert)
        spnId = findViewById(R.id.spn_id)

        spnId = findViewById(R.id.spn_id)

        ArrayAdapter.createFromResource(this, R.array.spn_id_array, android.R.layout.simple_spinner_item)
            .also { arrayAdapter ->
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spnId.adapter = arrayAdapter
            }

        btnAdd.setOnClickListener {
            val item = Item(edInsert.text.toString(), Item.toItemType(spnId.selectedItemId.toInt()))

            //rvAdapter.notifyDataSetChanged();
        }
    }
}