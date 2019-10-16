package com.noname.smatech.presentation.screens.repositriesfragment

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.noname.smatech.datalayer.apidata.ServerGateway
import com.noname.smatech.datalayer.localdata.deo.RepoDao
import com.noname.smatech.model.entities.Repo
import com.noname.smatech.model.usecases.isConnected
import com.noname.smatech.model.usecases.retrievRepositriesData
import com.noname.smatech.model.usecases.retrieveLocal

class RepositriesViewModel(apiService: ServerGateway, val repoDeo1: RepoDao, val con: Context) : ViewModel() {
    val reposdata: MutableLiveData<ArrayList<Repo>> = MutableLiveData()
    val errorLivedat: MutableLiveData<Throwable> = MutableLiveData()
    val loadingLivedat: MutableLiveData<Boolean> = MutableLiveData()
    val server:ServerGateway =apiService
    val repoDeo: RepoDao=repoDeo1
    var page = 1
    var loadnow = false
    init {
        if (con.isConnected&&!loadnow)
        getRepositries()
        else
            getLocalData()
    }

    fun getRepositries()
    {
        loadingLivedat.postValue(true)
        loadnow = true
        retrievRepositriesData(page,server,reposdata,errorLivedat,loadingLivedat,repoDeo)
    }

    fun getLocalData()
    {
        loadingLivedat.postValue(true)
        retrieveLocal(repoDeo,reposdata,errorLivedat,loadingLivedat)
    }

}
