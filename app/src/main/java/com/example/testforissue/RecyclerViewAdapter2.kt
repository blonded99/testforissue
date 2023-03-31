package com.example.testforissue

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter2(private val viewModel: MyViewModel, val context: Context?):
    RecyclerView.Adapter<RecyclerViewAdapter2.RecyclerViewViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_view2,
            parent, false)
        println("onCreateViewHolder called")
//        val inflater = LayoutInflater.from(parent.context)
//        val binding = ItemViewBinding.inflate(inflater,parent,false)
        return RecyclerViewViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerViewViewHolder, position: Int) {
        println("onBindViewHolder called: ${position}")
        holder.setContents(position)
    }

    override fun getItemCount(): Int {
        // 검색결과에 표시될 item 개수
        return viewModel.queryitems.size
    }

    inner class RecyclerViewViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val SearchResult: TextView = itemView.findViewById(R.id.textView)

        fun setContents(pos: Int){
            with(viewModel.queryitems[pos]){
                //세팅
//                SearchResult.setImageResource(R.drawable.trailer_search_sample_image)
                println("도시${pos}: ${cities}")
                SearchResult.text = cities
            }

            /*

            trailerSearchResult.setOnClickListener {
                이런식으로 clickListener가 여기 있어야 하지만 이게 이제 MVVM 쓰면 xml로 들어가는거
            }

            */
        }
    }
}