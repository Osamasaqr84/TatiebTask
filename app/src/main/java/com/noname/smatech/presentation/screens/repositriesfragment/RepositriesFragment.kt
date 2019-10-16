package com.noname.smatech.presentation.screens.repositriesfragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.noname.smatech.R
import com.noname.smatech.model.entities.Repo
import com.noname.smatech.model.usecases.showToastBasedOnThrowable
import kotlinx.android.synthetic.main.repositries_fragment.*
import kotlinx.android.synthetic.main.repositries_fragment.view.*
import java.util.*

class RepositriesFragment : Fragment() {

    val viewModel: RepositriesViewModel by lazy {
        ViewModelProviders.of(this,getViewModelFactory()).get(RepositriesViewModel::class.java)
    }

    lateinit var RepositriesAdapter: RepositriesAdapter
    internal lateinit var reposList: ArrayList<Repo>
    var againdata = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.repositries_fragment, container, false)
        viewModel.reposdata.observe(this, Observer {
            viewModel.loadnow = false
            if (viewModel.page == 1) {
                reposList = ArrayList(it)
                if (reposList.size>0) {
                    RepositriesAdapter =
                        RepositriesAdapter(
                            activity!!,
                            reposList
                        )
                    view.repositries.adapter = RepositriesAdapter
                }
                else
                    notfound.visibility=View.VISIBLE

            } else {
                if (it!!.size>0) {
                    reposList.addAll(it!!)
                    RepositriesAdapter.notifyDataSetChanged()
                 //   view.repositries.scrollToPosition(RepositriesAdapter.getItemCount() - 14)
                }else
                    againdata = false
            }
        })

        view.repositries.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastVisibleItem =
                    (Objects.requireNonNull(recyclerView.layoutManager) as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                if (lastVisibleItem == RepositriesAdapter.getItemCount() - 3&& dy>0) {
                    if (!viewModel.loadnow) {
                        if (againdata)
                            viewModel.page++;
                        viewModel.getRepositries()
                    }
                }
            }
        })

        viewModel.errorLivedat.observe(this, Observer {
            if (viewModel.page==1)
            showToastBasedOnThrowable(context,it)
            viewModel.loadnow = false
           // Toast.makeText(context,it.toString(),Toast.LENGTH_LONG).show()
        })

        viewModel.loadingLivedat.observe(this,
            Observer { loading ->
                view.progress.setVisibility(if (loading!!) View.VISIBLE else View.GONE) })

        return view
    }


    fun getViewModelFactory(): ViewModelFactory {
        return ViewModelFactory(
            activity?.application
        )
    }

}
