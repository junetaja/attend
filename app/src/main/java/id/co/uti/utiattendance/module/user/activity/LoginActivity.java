package id.co.uti.utiattendance.module.user.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.co.uti.utiattendance.R;
import id.co.uti.utiattendance.helper.HelperModule;
import id.co.uti.utiattendance.helper.model.ResultModel;
import id.co.uti.utiattendance.helper.navigator.AppNavigator;
import id.co.uti.utiattendance.module.user.DaggerUserComponent;
import id.co.uti.utiattendance.module.user.UserComponent;
import id.co.uti.utiattendance.module.user.UserModule;
import id.co.uti.utiattendance.module.user.model.LoginModel;
import id.co.uti.utiattendance.module.user.model.UserModel;
import id.co.uti.utiattendance.module.user.viewmodel.UserViewModel;
import zambelz.library.core.base.BaseActivity;
import zambelz.library.core.interfaces.ComponentIntegrator;

/**
 * Created by DELL on 8/22/2017.
 */

public class LoginActivity extends BaseActivity implements ComponentIntegrator<UserComponent>,
        UserViewModel.VMListener {

    @Inject UserViewModel userViewModel;
    @Inject AppNavigator appNavigator;

    @BindView(R.id.etUserId) EditText etUserId;
    @BindView(R.id.etPassword) EditText etPassword;

    @Override
    public Intent getCallingIntent(Context context, @Nullable Object... params) {
        return new Intent(context, LoginActivity.class);
    }


    @Override
    public UserComponent getComponent() {
        return DaggerUserComponent.builder()
                .mainApplicationComponent(getMainApplicationComponent())
                .activityModule(getActivityModule())
                .userModule(new UserModule(this))
                .helperModule(new HelperModule())
                .build();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        ButterKnife.bind(this);
    }

    @Override
    public void initializeInjector() {
        getComponent().inject(this);
    }

    @Override
    public void initialContent() {
        userViewModel.getPermission();
    }

    @Override
    public void onLoginSuccess(ResultModel<UserModel> userModelResultModel) {
//        if (userModelResultModel.status.equals(200)) {
            appNavigator.toAttendaceActivity(this);
//        } else {
//            Toast.makeText(this, userModelResultModel.status, Toast.LENGTH_LONG).show();
//        }
    }

    @Override
    public void onLoginError(Throwable error) {
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();
    }

    private boolean validate() {
        if (etUserId.getText().toString().length() == 0) {
            etUserId.setError("required");
            return false;
        } else if (etPassword.getText().toString().length() == 0) {
            etPassword.setError("required");
            return false;
        }
        return true;
    }

    @OnClick(R.id.btnLogin)
    public void btnLogin_Clicked() {
        if (validate()) {
            LoginModel loginModel = new LoginModel();
            loginModel.userid = Integer.parseInt(etUserId.getText().toString());
            loginModel.passsword = etPassword.getText().toString();
            addSubscription(userViewModel.postLogin(loginModel));
//            finish();
        }
    }
}
