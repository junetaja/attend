package id.co.uti.utiattendance.module.attendance.viewmodel;

import id.co.uti.utiattendance.helper.model.ResultModel;
import id.co.uti.utiattendance.module.attendance.apiservice.AttendanceApiService;
import id.co.uti.utiattendance.module.attendance.model.CheckinModel;
import rx.Subscription;
import zambelz.library.core.base.BaseViewModel;

/**
 * Created by DELL on 8/28/2017.
 */

public class AddDetailLocationViewModel extends BaseViewModel<AddDetailLocationViewModel.VMListener> {

    private AttendanceApiService attendanceApiService;

    public AddDetailLocationViewModel(VMListener vmListener,
                                      AttendanceApiService attendanceApiService) {
        super(vmListener);
        this.attendanceApiService = attendanceApiService;
    }

    public interface VMListener {
        void onPostCheckinSuccess(ResultModel<CheckinModel> checkinModelResultModel);
        void onPostCheckinError(Throwable error);
    }

    public Subscription postCheckIn(CheckinModel checkinModel) {
        return attendanceApiService.postCheckin(checkinModel)
                .compose(defaultScheduler())
                .subscribe(vmListener::onPostCheckinSuccess,
                        vmListener::onPostCheckinError);
    }
}
