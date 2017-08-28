package id.co.uti.utiattendance.module.user.viewmodel;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import id.co.uti.utiattendance.helper.model.ResultModel;
import id.co.uti.utiattendance.module.user.UserModule;
import id.co.uti.utiattendance.module.user.apiservice.UserApiService;
import id.co.uti.utiattendance.module.user.model.LoginModel;
import id.co.uti.utiattendance.module.user.model.UserModel;
import rx.Subscription;
import zambelz.library.core.base.BaseViewModel;

/**
 * Created by DELL on 8/22/2017.
 */

public class UserViewModel extends BaseViewModel<UserViewModel.VMListener> {

    private final UserApiService userApiService;
    private final Activity activity;

    public UserViewModel(VMListener vmListener,
                         UserApiService userApiService, Activity activity) {
        super(vmListener);
        this.userApiService = userApiService;
        this.activity = activity;
    }

    public interface VMListener {
        void onLoginSuccess(ResultModel<UserModel> userModelResultModel);
        void onLoginError(Throwable error);
    }

    public Subscription postLogin(LoginModel loginModel) {
        return userApiService.postLogin(loginModel)
                .compose(defaultScheduler())
                .subscribe(vmListener::onLoginSuccess,
                        vmListener::onLoginError);
    }

    public void getPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activity.checkSelfPermission(
                    Manifest.permission_group.LOCATION) != PackageManager.PERMISSION_GRANTED) {
                activity.requestPermissions(new String[]{
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION}, 0);
            }
        }
    }

}
