package com.example.testforissue

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class Item(val cities: String)

class MyViewModel: ViewModel() {
    val itemsListData = MutableLiveData<ArrayList<Item>>()
    val items = ArrayList<Item>()

    val queryitemsListData = MutableLiveData<ArrayList<Item>>()
    val queryitems = ArrayList<Item>()

    /* follower list 관련 */

    fun addItem(item: Item){
        items.add(item)
        itemsListData.value = items
    }

    fun deleteItem(pos: Int){
        items.removeAt(pos)
        itemsListData.value = items
    }

    fun addQueryItem(item: Item){
        queryitems.add(item)
        queryitemsListData.value = queryitems
    }

    fun deleteQueryItemAll(){
        queryitems.clear()
        queryitemsListData.value = queryitems
    }



}