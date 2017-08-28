package id.co.uti.utiattendance.module.attendance;

import dagger.Component;
import id.co.uti.utiattendance.helper.HelperModule;
import id.co.uti.utiattendance.module.attendance.activity.ActivitiesActivity;
import id.co.uti.utiattendance.module.attendance.activity.AttendanceActivity;
import id.co.uti.utiattendance.module.attendance.activity.ProfileActivity;
import id.co.uti.utiattendance.module.attendance.fragment.AddDetailLocationDialogFragment;
import zambelz.library.core.components.MainApplicationComponent;
import zambelz.library.core.modules.ActivityModule;
import zambelz.library.core.scopes.AppScope;

/**
 * Created by DELL on 8/22/2017.
 */

@AppScope
@Component(
        modules = {
                AttendanceModule.class,
                ActivityModule.class,
                HelperModule.class
        },
        dependencies = MainApplicationComponent.class
)

public interface AttendanceComponent {
    void inject(AttendanceActivity attendanceActivity);
    void inject(ActivitiesActivity activitiesActivity);
    void inject(ProfileActivity profileActivity);
    void inject(AddDetailLocationDialogFragment addDetailLocationDialogFragment);
}
