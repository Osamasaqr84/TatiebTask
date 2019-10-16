package com.noname.smatech.datalayer.localdata.deo;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.noname.smatech.model.entities.Repo;
import java.util.List;

import io.reactivex.Single;

@Dao
public interface RepoDao {

    @Insert
    public void insertRepo(Repo repositry);


    @Query("select * from Repositry")
    public Single<List<Repo>> selectAll();

    @Query("delete  from Repositry")
    public void removeAll();


}
