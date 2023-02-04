package com.example.testforissue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testforissue.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = ArrayList<String>()
        list.add("서울")
        list.add("대전")
        list.add("대구")
        list.add("부산")
        list.add("제주")
        list.add("일본")
        binding.recyclerView.adapter = RecyclerViewAdapter(applicationContext,list)
        binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)

    }
}