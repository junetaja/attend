package id.co.uti.utiattendance.module.user;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import id.co.uti.utiattendance.helper.data.DataHelper;
import id.co.uti.utiattendance.module.startup.activity.StartupActivity;
import id.co.uti.utiattendance.module.user.activity.LoginActivity;
import id.co.uti.utiattendance.module.user.apiservice.UserApiService;
import id.co.uti.utiattendance.module.user.viewmodel.UserViewModel;
import retrofit2.Retrofit;
import zambelz.library.core.scopes.AppScope;

/**
 * Created by DELL on 8/22/2017.
 */

@Module
public class UserModule {

    private LoginActivity loginActivity;
    private StartupActivity startupActivity;

    public UserModule() { }

    public UserModule(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
    }


//    API SERVICE
    @AppScope
    @Provides
    UserApiService provideUserApiService(Retrofit retrofit, DataHelper dataHelper) {
        return new UserApiService(retrofit, dataHelper);
    }


//    VIEW MODEL
    @AppScope
    @Provides
    UserViewModel provideUserViewModel(UserApiService userApiService, Activity activity) {
        return new UserViewModel(loginActivity, userApiService, activity);
    }



}
