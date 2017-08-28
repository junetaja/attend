package id.co.uti.utiattendance.module.attendance.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;
import id.co.uti.utiattendance.R;
import id.co.uti.utiattendance.helper.HelperModule;
import id.co.uti.utiattendance.module.attendance.AttendanceComponent;
import id.co.uti.utiattendance.module.attendance.AttendanceModule;
import id.co.uti.utiattendance.module.attendance.DaggerAttendanceComponent;
import zambelz.library.core.base.BaseActivity;
import zambelz.library.core.interfaces.ComponentIntegrator;

/**
 * Created by DELL on 8/24/2017.
 */

public class ProfileActivity extends BaseActivity implements ComponentIntegrator<AttendanceComponent> {



    @Override
    public Intent getCallingIntent(Context context, @Nullable Object... params) {
        return new Intent(context, ProfileActivity.class);
    }

    @Override
    public AttendanceComponent getComponent() {
        return DaggerAttendanceComponent.builder()
                .mainApplicationComponent(getMainApplicationComponent())
                .activityModule(getActivityModule())
                .attendanceModule(new AttendanceModule(this))
                .helperModule(new HelperModule())
                .build();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);
        ButterKnife.bind(this);
    }

    @Override
    public void initializeInjector() {
        getComponent().inject(this);
    }

    @Override
    public void initialContent() {
        super.initialContent();
    }
}
