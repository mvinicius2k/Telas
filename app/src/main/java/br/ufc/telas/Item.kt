package br.ufc.telas

import android.util.Log
import java.io.Serializable

enum class ItemType{
    PLACA_DE_VIDEO, CPU, MEMORIA_RAM, FONTE, DISCO_RIGIDO, GABINETE, OUTRO
}

class Item (var name: String, var qtd:Int, var price: Float, var type: ItemType) : Serializable {



    fun getTypeChar(): Char {
        return type.name[0]
    }

    fun getPriceStr(): String {

        var mark : Char
        if(price.toString().contains('.'))
            mark = '.'
        else
            mark = ','

        val numL = price.toString().split(mark)[0]
        var numR = price.toString().split(mark)[1]
        if(numR.length == 1)
            numR += 0
        else if(numR.length > 1)
            numR = numR.substring(0,2)
        return "$numL,$numR"
    }

    companion object{
        private const val TAG = "ItemCompanionObject"

        fun toItemType(index : Int) : ItemType{
            return when(index){
                0 -> ItemType.PLACA_DE_VIDEO
                1 -> ItemType.CPU
                2 -> ItemType.MEMORIA_RAM
                3 -> ItemType.FONTE
                4 -> ItemType.DISCO_RIGIDO
                5 -> ItemType.GABINETE
                6 -> ItemType.OUTRO
                else -> {
                    Log.d(TAG,"Index inexistente na conversão, retornando como " + ItemType.OUTRO.toString())
                    ItemType.OUTRO
                }
            }
        }
    }



}