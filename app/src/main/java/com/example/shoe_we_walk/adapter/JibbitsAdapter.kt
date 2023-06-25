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
import com.example.shoe_we_walk.dialog.JibbitsBuyDialog

class JibbitsAdapter(private val context : Context) : RecyclerView.Adapter<JibbitsAdapter.ViewHolder>(){
    var data = mutableListOf<JibbitsData>()

    inner class ViewHolder(view : View) :RecyclerView.ViewHolder(view){
        init {
            view.layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT
        }

        val jibbitsImage :ImageView = view.findViewById(R.id.itemJibbits)
        val jibbitsPrice :TextView = view.findViewById(R.id.itemJibbitsPriceText)

        fun bind(item :JibbitsData)
        {
            if(item.code == 1)
                jibbitsImage.setImageResource(R.drawable.jibbits_bee_front)
            else if(item.code == 2)
                jibbitsImage.setImageResource(R.drawable.jibbits_duck_front)
            else if(item.code == 3)
                jibbitsImage.setImageResource(R.drawable.jibbits_cat_front)
            else if(item.code == 4)
                jibbitsImage.setImageResource(R.drawable.jibbits_clover_front)
            else if(item.code == 5)
                jibbitsImage.setImageResource(R.drawable.jibbits_flower_front)
            else if(item.code == 6)
                jibbitsImage.setImageResource(R.drawable.jibbits_orange_front)
            else if(item.code == 7)
                jibbitsImage.setImageResource(R.drawable.jibbits_paeng_front)
            else if(item.code == 8)
                jibbitsImage.setImageResource(R.drawable.jibbits_bear_front)
            else if(item.code == 9)
                jibbitsImage.setImageResource(R.drawable.jibbits_octopus_front)
            else if(item.code == 10)
                jibbitsImage.setImageResource(R.drawable.jibbits_strawberry_front)
            jibbitsPrice.text = item.price.toString()

            itemView.setOnClickListener{
                val dialog = JibbitsBuyDialog(context, item)
                dialog.showDialog()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.jibbits_item, parent, false)
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

    class ItemSpacingDecoration(private val spacing: Int) : RecyclerView.ItemDecoration() {//아이템 간격 설정
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

    class ItemSpacingDecoration2(private val spacing: Int) : RecyclerView.ItemDecoration() {
        //아이템 간격 설정
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.left = spacing
            outRect.right = spacing
            outRect.top = spacing
            outRect.bottom = spacing
        }
    }
}