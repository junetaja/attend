package id.co.uti.utiattendance.helper.navigator;

import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;

import id.co.uti.utiattendance.MainActivity;
import id.co.uti.utiattendance.module.attendance.activity.ActivitiesActivity;
import id.co.uti.utiattendance.module.attendance.activity.AttendanceActivity;
import id.co.uti.utiattendance.module.attendance.activity.ProfileActivity;
import zambelz.library.core.utilities.Utilities;

/**
 * Created by junet on 7/19/17.
 */

public class AppNavigator {

    public AppNavigator() {
    }

    private <T> T getActivity(Class<T> activity) {
        return Utilities.getClass(activity);
    }

//    public void toMainActivity(Context context) {
//        Intent intent = getActivity(MainActivity.class).getCallingIntent(context);
//        context.startActivity(intent);
//    }

    public void toAttendaceActivity(Context context) {
        Intent intent = getActivity(AttendanceActivity.class).getCallingIntent(context);
        context.startActivity(intent);
    }

    public void toActivitiesActivity(Context context) {
        Intent intent = getActivity(ActivitiesActivity.class).getCallingIntent(context);
        context.startActivity(intent);
    }

    public void toProfileActivity(Context context) {
        Intent intent = getActivity(ProfileActivity.class).getCallingIntent(context);
        context.startActivity(intent);
    }

}
