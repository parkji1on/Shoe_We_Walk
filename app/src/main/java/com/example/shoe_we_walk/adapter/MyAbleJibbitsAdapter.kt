package com.example.shoe_we_walk.adapter

import android.content.Context
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoe_we_walk.EditshoesActivity
import com.example.shoe_we_walk.R
import com.example.shoe_we_walk.dialog.JibbitsSetDialog

class MyAbleJibbitsAdapter(private val context : Context) :RecyclerView.Adapter<MyAbleJibbitsAdapter.ViewHolder>(), JibbitsSetDialog.MySetDialogListener{
    var data = mutableListOf<MyAbleJibbitsData>()
    var AP = 0

    inner class ViewHolder(view : View) :RecyclerView.ViewHolder(view){
        init {
            view.layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT
        }

        val jibbitsImage : ImageView = view.findViewById(R.id.itemJibbits)



        fun bind(item :MyAbleJibbitsData)
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

            itemView.setOnClickListener{
                AP = adapterPosition
                val dialog = JibbitsSetDialog(context, item)
                dialog.setDialogListener(this@MyAbleJibbitsAdapter)
                dialog.showDialog()
            }
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

    override fun onDialogOkClicked() {
        val position = AP
        if (position != RecyclerView.NO_POSITION) {
            removeData(position)

            val activity = context as EditshoesActivity
            activity.recreate()
        }
    }
}