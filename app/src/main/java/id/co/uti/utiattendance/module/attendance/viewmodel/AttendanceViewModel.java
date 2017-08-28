package id.co.uti.utiattendance.module.attendance.viewmodel;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import id.co.uti.utiattendance.helper.model.ResultModel;
import id.co.uti.utiattendance.helper.utils.DataUtils;
import id.co.uti.utiattendance.module.attendance.apiservice.AttendanceApiService;
import id.co.uti.utiattendance.module.attendance.model.CheckinModel;
import id.co.uti.utiattendance.module.attendance.model.CheckoutModel;
import rx.Subscription;
import zambelz.library.core.base.BaseViewModel;

/**
 * Created by DELL on 8/25/2017.
 */

public class AttendanceViewModel extends BaseViewModel<AttendanceViewModel.VMListener> {

    private AttendanceApiService attendanceApiService;
    private final Activity activity;
    private DataUtils dataUtils;

    public AttendanceViewModel(VMListener vmListener,
                               AttendanceApiService attendanceApiService,
                               Activity activity, DataUtils dataUtils) {
        super(vmListener);
        this.attendanceApiService = attendanceApiService;
        this.activity = activity;
        this.dataUtils = dataUtils;
    }

    public interface VMListener {
        void onPostCheckinSuccess(ResultModel<CheckinModel> checkinModelResultModel);
        void onPostCheckinError(Throwable error);
        void onPostCheckoutSuccess(ResultModel<CheckoutModel> checkoutModelResultModel);
        void onPostCheckoutError(Throwable error);
    }

    public Subscription postCheckin(CheckinModel checkinModel) {
        return attendanceApiService.postCheckin(checkinModel)
                .compose(defaultScheduler())
                .subscribe(vmListener::onPostCheckinSuccess,
                        vmListener::onPostCheckinError);
    }

    public Subscription postCheckout(CheckoutModel checkoutModel) {
        return attendanceApiService.postCheckout(checkoutModel)
                .compose(defaultScheduler())
                .subscribe(vmListener::onPostCheckoutSuccess,
                        vmListener::onPostCheckoutError);
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

    public void setLocationIn(String locationIn) {
        dataUtils.setLocationIn(locationIn);
    }

    public String getLocationIn() {
       return dataUtils.getLocationIn();
    }

    public long getTimeStartElapsed() {
        return dataUtils.getTimeStartElapsed();
    }

    public long getTimeEndElapsed() {
        return dataUtils.getTimeEndElapsed();
    }

    public String getUserId() {
        return dataUtils.getPrefUserId();
    }

    public String getUserName() {
        return dataUtils.getPrefUserName();
    }

    public void setStartElapsedTime(long start) {
        dataUtils.setTimeStartElapsed(start);
    }

    public void setEndElapsedTime(long end) {
        dataUtils.setTimeEndElapsed(end);
    }

}
