package com.example.testforissue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testforissue.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MyViewModel
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MyViewModel::class.java]

        val list = ArrayList<String>()
        list.add("서울")
        list.add("대전")
        list.add("대구")
        list.add("부산")
        list.add("제주")
        list.add("일본")

        viewModel.addItem(Item("서울"))
        viewModel.addItem(Item("대전"))
        viewModel.addItem(Item("대구"))
        viewModel.addItem(Item("부산"))
        viewModel.addItem(Item("제주"))
        viewModel.addItem(Item("일본"))

        binding.recyclerView.adapter = RecyclerViewAdapter(viewModel,applicationContext,list)
        binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)


        binding.button.setOnClickListener {
            viewModel.addItem(Item(binding.editText.text.toString()))
        }

        viewModel.itemsListData.observe(this){
            RecyclerViewAdapter(viewModel,applicationContext,list).notifyDataSetChanged()
        }

    }
}