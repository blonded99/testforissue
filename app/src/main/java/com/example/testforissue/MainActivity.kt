package com.example.testforissue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
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
//        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)

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


//        binding.button.setOnClickListener {
//            viewModel.addItem(Item(binding.editText.text.toString()))
//        }

        viewModel.itemsListData.observe(this){
            RecyclerViewAdapter(viewModel,applicationContext).notifyDataSetChanged()
        }

        viewModel.queryitemsListData.observe(this){
            RecyclerViewAdapter2(viewModel,applicationContext).notifyDataSetChanged()
        }

        binding.editText.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                RecyclerViewAdapter2(viewModel,applicationContext).notifyDataSetChanged()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val text = binding.editText.text


                Log.e("onTextChange","onTextChanged called")

                if(text.isNotEmpty()){
                    viewModel.deleteQueryItemAll()

                    binding.recyclerView.visibility= View.GONE
                    binding.recyclerView2.visibility= View.VISIBLE

                    viewModel.items.forEach{
                        if(it.cities.contains(text)) {
                            viewModel.addQueryItem(it)
//                            RecyclerViewAdapter2(viewModel,applicationContext).notifyDataSetChanged()
                        }
                    }
                }
                else{
                    viewModel.deleteQueryItemAll()
                    binding.recyclerView2.visibility= View.GONE
                    binding.recyclerView.visibility= View.VISIBLE
//                    RecyclerViewAdapter2(viewModel,applicationContext).notifyDataSetChanged()
                }

                viewModel.queryitems.forEach {
                    System.out.println(it.cities)
                }


                RecyclerViewAdapter2(viewModel,applicationContext).notifyDataSetChanged()


            }

            override fun afterTextChanged(s: Editable?) {
//                RecyclerViewAdapter2(viewModel,applicationContext).notifyDataSetChanged()
            }


        })


    }

//    override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
//        return when (keyCode) {
//            KeyEvent.KEYCODE_DEL -> {
//                viewModel.deleteQueryItemAll()
//                Log.e("test","delete key pushed")
//                true
//            }
//            else -> super.onKeyUp(keyCode, event)
//        }
//    }
}