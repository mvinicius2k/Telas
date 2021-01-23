package br.ufc.telas

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception


class MainActivity : AppCompatActivity() {



    private val TAG = "MainActivity"

    private lateinit var listItems : ArrayList<Item>
    private lateinit var rvAdapter : RecyclerView.Adapter<*>
    private lateinit var rvManager : RecyclerView.LayoutManager
    private lateinit var tbNew : ToggleButton
    private lateinit var tbEdit : ToggleButton
    private lateinit var etId : EditText
    private lateinit var txtEmpty : TextView
    private lateinit var spnId : Spinner



    private lateinit var rvItems : RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvItems = findViewById(R.id.rv_items)
        tbNew = findViewById(R.id.tb_new)
        tbEdit = findViewById(R.id.tb_edit)
        etId = findViewById(R.id.et_id)
        txtEmpty = findViewById(R.id.txt_empty)


        listItems = ArrayList()
        rvManager = LinearLayoutManager(this)
        rvAdapter = RecyclerAdapter(listItems)
        spnId = findViewById(R.id.spn_id)

        ArrayAdapter.createFromResource(this, R.array.spn_id_array, android.R.layout.simple_spinner_item)
            .also { arrayAdapter ->
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spnId.adapter = arrayAdapter
            }


        rvItems.apply {
            setHasFixedSize(false)
            layoutManager = rvManager
            adapter = rvAdapter
        }

        tbNew.setOnCheckedChangeListener { _, isChecked ->

            if(isChecked){
                tbEdit.visibility = View.GONE
                spnId.visibility = View.VISIBLE
            } else {


                val intent = Intent(this,AddActivity::class.java)
                intent.putExtra("id", spnId.selectedItemId.toInt())
                startActivityForResult(intent, ActivityKind.AddActivity_New.ordinal)
                tbEdit.visibility = View.VISIBLE
                spnId.visibility = View.GONE
            }


        }

        tbEdit.setOnCheckedChangeListener { _, isChecked ->

            if(isChecked){

                tbNew.visibility = View.GONE
                etId.visibility = View.VISIBLE


            } else {

                var pos : Int

                try{
                    pos = etId.text.toString().toInt()
                } catch (e : Exception){
                    e.printStackTrace()
                    tbEdit.isChecked = false
                    tbNew.visibility = View.VISIBLE
                    etId.visibility = View.GONE
                    return@setOnCheckedChangeListener

                }


                if(pos >= listItems.size || pos < 0){
                    Toast.makeText(this, "Item " + etId.text.toString() + " não existe", Toast.LENGTH_SHORT).show()
                    tbEdit.isChecked = true
                    return@setOnCheckedChangeListener
                }

                val intent = Intent(this,AddActivity::class.java)



                intent.putExtra("item", listItems[pos])
                intent.putExtra("line", pos)
                startActivityForResult(intent, ActivityKind.AddActivity_Edit.ordinal)
                tbNew.visibility = View.VISIBLE
                etId.visibility = View.GONE
            }


        }













    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode in intArrayOf(ActivityKind.AddActivity_New.ordinal, ActivityKind.AddActivity_Edit.ordinal)){
            if(resultCode == Activity.RESULT_OK){
                val item = data?.getSerializableExtra("item") as Item
                val line = data?.getIntExtra("line",-1)

                if(requestCode == ActivityKind.AddActivity_New.ordinal)
                    listItems.add(item)
                else if(line > -1)
                    listItems[line] = item

                rvItems.visibility = View.VISIBLE
                rvAdapter.notifyDataSetChanged();
                txtEmpty.visibility = View.GONE



            } else if(resultCode == Activity.RESULT_CANCELED)
                return


        }





    }
}