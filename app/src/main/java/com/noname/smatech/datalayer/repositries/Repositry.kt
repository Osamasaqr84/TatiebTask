package com.noname.smatech.datalayer.repositries

import android.annotation.SuppressLint
import com.noname.smatech.datalayer.apidata.ServerGateway
import com.noname.smatech.datalayer.localdata.deo.RepoDao
import com.noname.smatech.model.entities.Repo
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


fun retrieverepositries(server: ServerGateway,page: Int): Observable<ArrayList<Repo>> {
    return server.retrieveRepositries(page)
}


fun retrieveLocalData(repodeo :RepoDao): Single<List<Repo>>?
{
    return repodeo.selectAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

}


@SuppressLint("CheckResult")
fun AddRepo(repodeo: RepoDao,reposirtry: Repo) {
    Observable.fromCallable { repodeo.insertRepo(reposirtry) }.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe()

}

@SuppressLint("CheckResult")
fun DeleteAll(repodeo: RepoDao) {
    Observable.fromCallable { repodeo.removeAll() }.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe()

}
