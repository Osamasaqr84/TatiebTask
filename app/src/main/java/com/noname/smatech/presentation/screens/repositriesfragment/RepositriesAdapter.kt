package com.noname.smatech.presentation.screens.repositriesfragment

import android.content.Context
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.noname.smatech.R
import com.noname.smatech.databinding.RepoBinding
import com.noname.smatech.model.entities.Repo
import kotlin.collections.ArrayList

class RepositriesAdapter(activity: FragmentActivity, repos: ArrayList<Repo>) :
    RecyclerView.Adapter<RepositriesAdapter.CustomView>() {

    private val context: Context
    private var repsData: ArrayList<Repo> = repos

    init {
        this.context = activity
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomView {
        val binding : RepoBinding  = DataBindingUtil.inflate<RepoBinding>(
            LayoutInflater.from(parent.context), R.layout.repo_item,
            parent, false
        )
        return CustomView(binding)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: CustomView, position: Int) {
        val repositry = repsData.get(position)
        holder.customCartItemBinding.repo= repositry
        if (position%2==0)
            holder.customCartItemBinding.card.setCardBackgroundColor(context.getColor(R.color.color_item1))
        else
            holder.customCartItemBinding.card.setCardBackgroundColor(context.getColor(R.color.color_item2))

        holder.customCartItemBinding.repourl.text=repsData.get(position).url

    }

    override fun getItemCount(): Int {
        return repsData.size
    }

    inner class CustomView(var customCartItemBinding: RepoBinding) :
        RecyclerView.ViewHolder(customCartItemBinding.getRoot())
}
