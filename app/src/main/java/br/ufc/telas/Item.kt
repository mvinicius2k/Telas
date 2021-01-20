package br.ufc.telas

import android.util.Log

enum class ItemType{
    VEICULO, LIVRO, HARDWARE, COMIDA, OUTRO
}

class Item (var name: String, var type: ItemType) {



    fun getTypeChar(): Char {
        return type.name[0]
    }

    companion object{
        private const val TAG = "ItemCompanionObject"

        fun toItemType(index : Int) : ItemType{
            return when(index){
                0 -> ItemType.VEICULO
                1 -> ItemType.LIVRO
                2 -> ItemType.HARDWARE
                3 -> ItemType.COMIDA
                4 -> ItemType.OUTRO
                else -> {
                    Log.d(TAG,"Index inexistente na conversão, retornando como " + ItemType.OUTRO.toString())
                    ItemType.OUTRO
                }
            }
        }
    }

}