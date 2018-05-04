package com.example.chayut.test

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import java.util.*

class StickerFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var baseRecyclerAdapter: BaseRecyclerAdapter
    private val recyclerViewItems = ArrayList<BaseRecyclerViewItem>()

    private lateinit var packDao: StickerDao.PackItemDao

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_sticker, container, false)

        packDao = arguments?.getParcelable("packDao")!!

        if (recyclerViewItems.size == 0)
            for (stickerItemDao in packDao.sticker!!)
                if (stickerItemDao != null) {
                    recyclerViewItems.add(StickerItem(stickerItemDao))
                }

        baseRecyclerAdapter = BaseRecyclerAdapter(activity as BaseRecyclerAdapter.StickerClickListener)

        recyclerView = rootView.findViewById(R.id.recycler)
        recyclerView.layoutManager = GridLayoutManager(context, 4)

        baseRecyclerAdapter.setRecyclerList(recyclerViewItems)

        recyclerView.adapter = baseRecyclerAdapter

        return rootView
    }

    fun newInstance(packItemDao: StickerDao.PackItemDao?): StickerFragment {
        val fragment = StickerFragment()
        val args = Bundle()
        args.putParcelable("packDao", packItemDao)
        fragment.arguments = args
        return fragment
    }

}

