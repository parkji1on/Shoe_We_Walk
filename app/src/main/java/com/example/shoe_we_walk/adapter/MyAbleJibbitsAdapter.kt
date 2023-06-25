package com.example.shoe_we_walk.adapter

import android.content.Context
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoe_we_walk.R

class MyAbleJibbitsAdapter(private val context : Context) :RecyclerView.Adapter<MyAbleJibbitsAdapter.ViewHolder>(){
    var data = mutableListOf<MyAbleJibbitsData>()

    inner class ViewHolder(view : View) :RecyclerView.ViewHolder(view){
        init {
            view.layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT
        }

        val jibbitsImage : ImageView = view.findViewById(R.id.itemJibbits)

        fun bind(item :MyAbleJibbitsData)
        {
            //아이템 처리
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.myjibbits_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, p1: Int) {
        holder.bind(data[p1])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun removeData(position :Int)
    {
        data.removeAt(position)
        notifyItemRemoved(position)
    }

    class ItemSpacingDecoration(private val spacing: Int) : RecyclerView.ItemDecoration() {
        //아이템 간격 설정
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.right = spacing
        }
    }

}