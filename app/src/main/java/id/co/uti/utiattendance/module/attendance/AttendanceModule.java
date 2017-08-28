package id.co.uti.utiattendance.module.attendance;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import id.co.uti.utiattendance.helper.data.DataHelper;
import id.co.uti.utiattendance.helper.location.GPSTracker;
import id.co.uti.utiattendance.helper.utils.DataUtils;
import id.co.uti.utiattendance.module.attendance.activity.ActivitiesActivity;
import id.co.uti.utiattendance.module.attendance.activity.AttendanceActivity;
import id.co.uti.utiattendance.module.attendance.activity.ProfileActivity;
import id.co.uti.utiattendance.module.attendance.adapter.ActivitiesListAdapter;
import id.co.uti.utiattendance.module.attendance.apiservice.AttendanceApiService;
import id.co.uti.utiattendance.module.attendance.fragment.AddDetailLocationDialogFragment;
import id.co.uti.utiattendance.module.attendance.viewmodel.ActivitiesViewModel;
import id.co.uti.utiattendance.module.attendance.viewmodel.AddDetailLocationViewModel;
import id.co.uti.utiattendance.module.attendance.viewmodel.AttendanceViewModel;
import retrofit2.Retrofit;
import zambelz.library.core.scopes.AppScope;

/**
 * Created by DELL on 8/22/2017.
 */

@Module
public class AttendanceModule {

    private AttendanceActivity attendanceActivity;
    private ActivitiesActivity activitiesActivity;
    private ProfileActivity profileActivity;
    private AddDetailLocationDialogFragment addDetailLocationDialogFragment;

    public AttendanceModule() {

    }

    public AttendanceModule(ProfileActivity profileActivity) {
        this.profileActivity = profileActivity;
    }

    public AttendanceModule(AttendanceActivity attendanceActivity) {
        this.attendanceActivity = attendanceActivity;
    }

    public AttendanceModule(ActivitiesActivity activitiesActivity) {
        this.activitiesActivity = activitiesActivity;
    }

    public AttendanceModule(AddDetailLocationDialogFragment addDetailLocationDialogFragment) {
        this.addDetailLocationDialogFragment = addDetailLocationDialogFragment;
    }

    //    API SERVICE
    @AppScope
    @Provides
    AttendanceApiService provideAttendanceApiService(Retrofit retrofit, DataHelper dataHelper) {
        return new AttendanceApiService(retrofit, dataHelper);
    }

    //    ADAPTER
    @AppScope
    @Provides
    ActivitiesListAdapter provideActivitiesAdapter(){
        return new ActivitiesListAdapter(activitiesActivity);
    }

    //    VIEWMODEL
    @AppScope
    @Provides
    ActivitiesViewModel provideActivitiesViewModel(AttendanceApiService attendanceApiService) {
        return new ActivitiesViewModel(activitiesActivity, attendanceApiService);
    }

    @AppScope
    @Provides
    AttendanceViewModel provideAttendanceViewModel(AttendanceApiService attendanceApiService,
                                                   Activity activity,
                                                   DataUtils dataUtils) {
        return new AttendanceViewModel(attendanceActivity, attendanceApiService, activity, dataUtils);
    }

    @AppScope
    @Provides
    AddDetailLocationViewModel provideAddDetailLocationViewModel(AttendanceApiService attendanceApiService) {
        return new AddDetailLocationViewModel(addDetailLocationDialogFragment, attendanceApiService);
    }

//    DIALOG FRAGMENT
    @AppScope
    @Provides
    AddDetailLocationDialogFragment provideAddDetailLocationDialogFragment() {
    AddDetailLocationDialogFragment addDetailLocationDialogFragment = new AddDetailLocationDialogFragment();
    addDetailLocationDialogFragment.setListener(attendanceActivity);
    return addDetailLocationDialogFragment;
}

    @Provides
    @AppScope
    GPSTracker provideGpsTracker(Context context) {
        return new GPSTracker(context);
    }


}
