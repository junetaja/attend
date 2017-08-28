package id.co.uti.utiattendance.module.startup.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;
import id.co.uti.utiattendance.R;
import id.co.uti.utiattendance.helper.HelperModule;
import id.co.uti.utiattendance.module.startup.DaggerStartupComponent;
import id.co.uti.utiattendance.module.startup.StartupComponent;
import id.co.uti.utiattendance.module.startup.StartupModule;
import id.co.uti.utiattendance.module.startup.viewmodel.StartupViewModel;
import id.co.uti.utiattendance.module.user.DaggerUserComponent;
import id.co.uti.utiattendance.module.user.UserComponent;
import id.co.uti.utiattendance.module.user.UserModule;
import zambelz.library.core.base.BaseActivity;
import zambelz.library.core.interfaces.ComponentIntegrator;

/**
 * Created by DELL on 8/22/2017.
 */

public class StartupActivity extends BaseActivity implements ComponentIntegrator<StartupComponent>,
        StartupViewModel.VMListener {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        ButterKnife.bind(this);
    }

    @Override
    public StartupComponent getComponent() {
        return DaggerStartupComponent.builder()
                .mainApplicationComponent(getMainApplicationComponent())
                .activityModule(getActivityModule())
                .startupModule(new StartupModule(this))
                .build();
    }

    @Override
    public void initializeInjector() {
        getComponent().inject(this);
    }

    @Override
    public void initialContent() {

    }

    @Override
    public void onLoginSuccess() {

    }
}