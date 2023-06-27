package com.example.shoe_we_walk.ui.market

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoe_we_walk.Data.Auth
import com.example.shoe_we_walk.R
import com.example.shoe_we_walk.adapter.JibbitsAdapter
import com.example.shoe_we_walk.adapter.JibbitsData
import com.example.shoe_we_walk.databinding.FragmentMarketBinding
import java.text.NumberFormat

class MarketFragment : Fragment() {

    private var _binding: FragmentMarketBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var jibbitsAdapter :JibbitsAdapter
    lateinit var jibbitsAllAdapter: JibbitsAdapter
    val data = mutableListOf<JibbitsData>()

    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    private val scrollDelay: Long = 2000 // 스크롤 간격 (2초)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val marketViewModel =
            ViewModelProvider(this).get(MarketViewModel::class.java)

        _binding = FragmentMarketBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initRecycler()
        startAutoScroll()
        return root
    }

    private fun initRecycler() {
        recyclerView = binding.MarketNewItemRecyclerView

        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager

        val spacing = resources.getDimensionPixelSize(R.dimen.item_spacing)
        recyclerView.addItemDecoration(JibbitsAdapter.ItemSpacingDecoration(spacing))

        jibbitsAdapter = JibbitsAdapter(requireContext())
        recyclerView.adapter = jibbitsAdapter

        val cointext :TextView = binding.MarketMycoinValue
        val totalitemcnttext :TextView = binding.matketMycollectionValue

        Auth.coin.observe(viewLifecycleOwner) { coinValue ->
            val formattedNumber = NumberFormat.getInstance().format(coinValue)
            cointext.text = formattedNumber + " 코인"
        }

        Auth.total_item_cnt.observe(viewLifecycleOwner) { itemCount ->
            val formattedNumber2 = NumberFormat.getInstance().format(itemCount)
            totalitemcnttext.text = formattedNumber2 + " 개"
        }

        //Add Data
        jibbitsAdapter.data.add(JibbitsData(1, "jibbits_bee", "행복한꿀벌", 250))
        jibbitsAdapter.data.add(JibbitsData(2, "jibbits_duck", "아기오리", 400))
        jibbitsAdapter.data.add(JibbitsData(3, "jibbits_cat", "고양이", 50))
        jibbitsAdapter.data.add(JibbitsData(4, "jibbits_clover", "네잎클로버", 100))
        jibbitsAdapter.data.add(JibbitsData(5, "jibbits_flower", "보라색 꽃", 50))
        jibbitsAdapter.data.add(JibbitsData(6, "jibbits_orange", "오렌지", 50))
        jibbitsAdapter.data.add(JibbitsData(7, "jibbits_paeng", "펭귄", 200))
        jibbitsAdapter.data.add(JibbitsData(8, "jibbits_bear", "분홍곰", 300))
        jibbitsAdapter.data.add(JibbitsData(9, "jibbits_octopus", "문어", 150))
        jibbitsAdapter.data.add(JibbitsData(10, "jibbits_strawberry", "딸기", 120))


        jibbitsAdapter.data.addAll(data)
        jibbitsAdapter.notifyDataSetChanged()

        val recyclerView2 = binding.MarketAllItemRecyclerView
        val spanCount = 3 // 가로로 표시할 아이템 개수
        val verticalItemCount = 0 // 세로로 표시할 아이템 개수 N%3==0, N/3 else N/3 + 1

        val layoutManager2 = GridLayoutManager(context, spanCount)
        layoutManager2.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position < verticalItemCount) spanCount else 1
            }
        }

        recyclerView2.layoutManager = layoutManager2

        recyclerView2.addItemDecoration(JibbitsAdapter.ItemSpacingDecoration2(10))

        jibbitsAllAdapter = JibbitsAdapter(requireContext())
        recyclerView2.adapter = jibbitsAllAdapter

        jibbitsAllAdapter.data.add(JibbitsData(1, "jibbits_bee", "행복한꿀벌", 250))
        jibbitsAllAdapter.data.add(JibbitsData(2, "jibbits_duck", "아기오리", 400))
        jibbitsAllAdapter.data.add(JibbitsData(3, "jibbits_cat", "고양이", 50))
        jibbitsAllAdapter.data.add(JibbitsData(4, "jibbits_clover", "네잎클로버", 100))
        jibbitsAllAdapter.data.add(JibbitsData(5, "jibbits_flower", "보라색 꽃", 50))
        jibbitsAllAdapter.data.add(JibbitsData(6, "jibbits_orange", "오렌지", 50))
        jibbitsAllAdapter.data.add(JibbitsData(7, "jibbits_paeng", "펭귄", 200))
        jibbitsAllAdapter.data.add(JibbitsData(8, "jibbits_bear", "분홍곰", 300))
        jibbitsAllAdapter.data.add(JibbitsData(9, "jibbits_octopus", "문어", 150))
        jibbitsAllAdapter.data.add(JibbitsData(10, "jibbits_strawberry", "딸기", 120))

        jibbitsAllAdapter.notifyDataSetChanged()
    }

    private fun startAutoScroll() {
        val scrollDistance = 10 // 한 번에 이동할 픽셀 거리
        val scrollDelay :Long= 50 // 이동 간격 (밀리초)

        val recyclerView = binding.MarketNewItemRecyclerView
        //val layoutManager = recyclerView.layoutManager as LinearLayoutManager

        val handler = Handler()

        val runnable = object : Runnable {
            override fun run() {
                recyclerView.smoothScrollBy(scrollDistance, 0)
                handler.postDelayed(this, scrollDelay)
            }
        }

        recyclerView.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP || event.action == MotionEvent.ACTION_CANCEL) {
                handler.removeCallbacks(runnable) // 스크롤 멈추기
                handler.postDelayed(runnable, 3000) // 3초 후에 runnable 다시 예약
            }
            false
        }

        handler.postDelayed(runnable, scrollDelay)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}