package com.noname.smatech.datalayer.apidata;


import com.noname.smatech.model.entities.Repo;

import java.util.ArrayList;
import io.reactivex.Observable;
import retrofit2.http.*;


public interface ServerGateway {

    @GET("https://api.github.com/users/JakeWharton/repos?per_page=15")
    Observable<ArrayList<Repo>> retrieveRepositries(@Query("page") int page);


}
