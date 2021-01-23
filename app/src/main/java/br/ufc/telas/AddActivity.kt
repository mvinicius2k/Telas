package br.ufc.telas

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception

class AddActivity : AppCompatActivity() {


    private val TAG = "AddActivity"

    private lateinit var btnAdd : Button
    private lateinit var etName : EditText
    private lateinit var spnId : Spinner
    private lateinit var etnValue : EditText
    private lateinit var etnQtd : EditText

    private var id = -1
    private var item : Item? = null
    private var line : Int = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        btnAdd = findViewById(R.id.btn_add)
        etName = findViewById(R.id.et_name)
        etnValue = findViewById(R.id.etn_value)
        etnQtd = findViewById(R.id.etn_qtd)

        if(intent != null){
            id = intent.getIntExtra("id", -1)
            line = intent.getIntExtra("line", -1)

            item = intent.getSerializableExtra("item") as Item?

            if(item != null){
                id = item?.type?.ordinal!!
                btnAdd.text = "Atualizar"
            }


            title = "Telas - " + resources.getStringArray(R.array.spn_id_array)[id]
        }








        if(item != null){
            etName.setText(item?.name)
            etnValue.setText(item?.price.toString())
            etnQtd.setText(item?.qtd.toString())


        }





        btnAdd.setOnClickListener {

            var item : Item

            try {
                item = Item(etName.text.toString(), etnQtd.text.toString().toInt(), etnValue.text.toString().toFloat(), Item.toItemType(id))
            } catch (e : Exception){
                Toast.makeText(this, "Valor e quantidade escritos errados", Toast.LENGTH_LONG).show()
                e.printStackTrace()
                return@setOnClickListener
            }



            val returnIntent = Intent()
            if(item == null)
                setResult(Activity.RESULT_CANCELED,returnIntent)
            else{



                returnIntent.putExtra("item",item)
                returnIntent.putExtra("id", id)
                returnIntent.putExtra("line", line)
                setResult(Activity.RESULT_OK, returnIntent)
            }
            finish()






        }



    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }
}