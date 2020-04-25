package com.omdb.cleanmovies.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<B : ViewDataBinding, I>(private var layoutRes: Int) :
    RecyclerView.Adapter<BaseAdapter.ViewHolder<B>>() {

    var data: List<I>? = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<B> {
        val binding = DataBindingUtil.inflate<B>(
            LayoutInflater.from(parent.context),
            layoutRes,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    class ViewHolder<B : ViewDataBinding>(var binding: B) : RecyclerView.ViewHolder(binding.root)

}