package id.co.uti.utiattendance.module.attendance.viewmodel;

import id.co.uti.utiattendance.helper.model.ResultModel;
import id.co.uti.utiattendance.module.attendance.apiservice.AttendanceApiService;
import id.co.uti.utiattendance.module.attendance.model.ActivitiesModel;
import rx.Subscription;
import zambelz.library.core.base.BaseViewModel;

/**
 * Created by DELL on 8/24/2017.
 */

public class ActivitiesViewModel extends BaseViewModel<ActivitiesViewModel.VMListener> {

    private AttendanceApiService attendanceApiService;

    public ActivitiesViewModel(VMListener vmListener,
                               AttendanceApiService attendanceApiService) {
        super(vmListener);
        this.attendanceApiService = attendanceApiService;
    }

    public interface VMListener {
        void onLoadActivitiesSuccess(ResultModel<ActivitiesModel> activitiesModelResultModel);
        void onLoadActivitiesError(Throwable error);
    }

    public Subscription getDataWithId(int id) {
        return attendanceApiService.getDataWithId(id)
                .compose(defaultScheduler())
                .subscribe(vmListener::onLoadActivitiesSuccess,
                        vmListener::onLoadActivitiesError);
    }


}
