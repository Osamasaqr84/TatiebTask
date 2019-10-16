package com.noname.smatech.model.usecases

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import com.noname.smatech.datalayer.apidata.ServerGateway
import com.noname.smatech.datalayer.localdata.deo.RepoDao
import com.noname.smatech.datalayer.repositries.AddRepo
import com.noname.smatech.datalayer.repositries.DeleteAll
import com.noname.smatech.datalayer.repositries.retrieveLocalData
import com.noname.smatech.datalayer.repositries.retrieverepositries
import com.noname.smatech.model.entities.Repo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@SuppressLint("CheckResult")
fun retrievRepositriesData( page: Int,serverGateway: ServerGateway,repositriesliveData: MutableLiveData<ArrayList<Repo>>,
    errorLiveData: MutableLiveData<Throwable>,
    loadingLivedata: MutableLiveData<Boolean>,
    repoDeo: RepoDao) {
    retrieverepositries(serverGateway, page).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread()).subscribe({

        storinDB(it, repoDeo,page)
        repositriesliveData.postValue(it);
        loadingLivedata.postValue(false) },
        {
            errorLiveData.postValue(it);
            loadingLivedata.postValue(false)
        })
}

@SuppressLint("CheckResult")
fun retrieveLocal(
    repoDeo: RepoDao, repositriesliveData: MutableLiveData<ArrayList<Repo>>,
    errorLiveData: MutableLiveData<Throwable>,
    loadingLivedata: MutableLiveData<Boolean>
) {
    retrieveLocalData(repoDeo)?.subscribeOn(Schedulers.io())
        ?.observeOn(AndroidSchedulers.mainThread())?.subscribe({ myData ->
            repositriesliveData.postValue(myData as ArrayList<Repo>);
            loadingLivedata.postValue(false)// handle data fetched successfully and API call completed
        }, { throwable ->
            errorLiveData.postValue(throwable);
            loadingLivedata.postValue(false)
        });
}


fun storinDB(
    alldata: ArrayList<Repo>,
    deo: RepoDao,
    page: Int
) {
    if (page==1)
    DeleteAll(deo)
    alldata.map { repositry -> AddRepo(deo, repositry) }
}
