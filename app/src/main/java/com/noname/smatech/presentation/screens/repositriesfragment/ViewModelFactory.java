package com.noname.smatech.presentation.screens.repositriesfragment;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import com.noname.smatech.datalayer.apidata.ApiClient;
import com.noname.smatech.datalayer.apidata.ServerGateway;
import com.noname.smatech.datalayer.apidata.SmartApp;
import com.noname.smatech.datalayer.localdata.LocalDatabase;
import com.noname.smatech.datalayer.localdata.deo.RepoDao;

public class ViewModelFactory implements ViewModelProvider.Factory {


    private Application application;

    public ViewModelFactory(Application application1) {
        application = application1;
    }

    @SuppressWarnings("SingleStatementInBlock")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
         if (modelClass == RepositriesViewModel.class)
        {
            return (T) new RepositriesViewModel(getApiService(),getRepoDao(),application.getBaseContext());
        }

        throw new IllegalArgumentException("Invalid view model class type");
    }

    private RepoDao getRepoDao() {
        return LocalDatabase.getInstance(application).taskDeo();
    }

    private ServerGateway getApiService() {
        return SmartApp.getAppInstance().getApiInterface();
    }

}
