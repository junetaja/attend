package id.co.uti.utiattendance.module.startup;

import dagger.Module;
import dagger.Provides;
import id.co.uti.utiattendance.helper.data.DataHelper;
import id.co.uti.utiattendance.module.startup.activity.StartupActivity;
import id.co.uti.utiattendance.module.startup.apiservice.StartupApiService;
import id.co.uti.utiattendance.module.startup.viewmodel.StartupViewModel;
import retrofit2.Retrofit;
import zambelz.library.core.scopes.AppScope;

/**
 * Created by DELL on 8/22/2017.
 */

@Module
public class StartupModule {

    private StartupActivity startupActivity;


    public StartupModule(StartupActivity startupActivity) {
        this.startupActivity = startupActivity;
    }


    @AppScope
    @Provides
    StartupApiService provideStartupApiService(Retrofit retrofit, DataHelper dataHelper) {
        return new StartupApiService(retrofit, dataHelper);
    }


    @AppScope
    @Provides
    StartupViewModel provideStartupViewModel(StartupApiService startupApiService) {
        return new StartupViewModel(startupActivity, startupApiService);
    }


}
