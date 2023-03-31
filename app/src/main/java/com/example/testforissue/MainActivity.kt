package com.example.testforissue

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.testforissue.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MyViewModel
    lateinit var binding: ActivityMainBinding

    private var list = ArrayList<String>() // post image 넘어오는 array

    private val adapter = ViewPagerAdapter(list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MyViewModel::class.java]

        list.add("R.drawable.helmet")
        list.add("R.drawable.helmet")

        binding.bodyImage.adapter = adapter
        binding.bodyImage.orientation = ViewPager2.ORIENTATION_HORIZONTAL


        binding.button.setOnClickListener {
            binding.bodyImage.adapter?.notifyDataSetChanged()
        }

        /*

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

        binding.recyclerView.adapter = RecyclerViewAdapter(viewModel,applicationContext)
        binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)

        binding.recyclerView2.adapter = RecyclerViewAdapter2(viewModel,applicationContext)
        binding.recyclerView2.layoutManager = LinearLayoutManager(applicationContext)



        viewModel.itemsListData.observe(this){
            RecyclerViewAdapter(viewModel,applicationContext).notifyDataSetChanged()
        }

        viewModel.queryitemsListData.observe(this){
            println("observer called")
            RecyclerViewAdapter2(viewModel,applicationContext).notifyDataSetChanged()
        }

        binding.editText.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.e("t","beforeTextChanged Called")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.e("onTextChange","onTextChanged called")




            }

            override fun afterTextChanged(s: Editable?) {
                Log.e("t","afterTextChanged Called")

                val text = binding.editText.text

                if(text.isNotEmpty()){
                    viewModel.deleteQueryItemAll()

                    binding.recyclerView.visibility= View.GONE
                    binding.recyclerView2.visibility= View.VISIBLE

                    viewModel.items.forEach{
                        if(it.cities.contains(text)) {
                            viewModel.addQueryItem(it)
                        }
                    }
                }
                else{
                    viewModel.deleteQueryItemAll()
                    binding.recyclerView2.visibility= View.GONE
                    binding.recyclerView.visibility= View.VISIBLE
                }

                viewModel.queryitems.forEach {
                    System.out.println(it.cities)
                }

            }


        })

        */


    }


}