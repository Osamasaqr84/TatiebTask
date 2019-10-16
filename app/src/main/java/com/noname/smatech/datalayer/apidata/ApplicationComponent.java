package com.noname.smatech.datalayer.apidata;




import dagger.Component;

@ApplicationScope
@Component(modules = {ApiServiceModule.class})
public interface ApplicationComponent {
    ServerGateway getApplicationService();


}
