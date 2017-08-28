package id.co.uti.utiattendance.module.attendance.apiservice;

import id.co.uti.utiattendance.helper.data.DataHelper;
import id.co.uti.utiattendance.helper.model.ResultModel;
import id.co.uti.utiattendance.module.attendance.model.ActivitiesModel;
import id.co.uti.utiattendance.module.attendance.model.CheckinModel;
import id.co.uti.utiattendance.module.attendance.model.CheckoutModel;
import retrofit2.Retrofit;
import rx.Observable;
import zambelz.library.core.base.BaseApiService;

/**
 * Created by DELL on 8/22/2017.
 */

public class AttendanceApiService extends BaseApiService {

    private final DataHelper dataHelper;

    public AttendanceApiService(Retrofit retrofit, DataHelper dataHelper) {
        super(retrofit);
        this.dataHelper = dataHelper;
    }

    public Observable<ResultModel<ActivitiesModel>> getDataWithId(int id) {
        return createNetworkRequest(AttendanceApiInterface.class)
                .getDataWhitId(dataHelper.getHeaderAuth(), id);
    }

    public Observable<ResultModel<CheckinModel>> postCheckin(CheckinModel checkinModel) {
        return createNetworkRequest(AttendanceApiInterface.class)
                .postCheckin(dataHelper.getHeaderAuth(), checkinModel);
    }

    public Observable<ResultModel<CheckoutModel>> postCheckout(CheckoutModel checkoutModel) {
        return createNetworkRequest(AttendanceApiInterface.class)
                .postCheckout(dataHelper.getHeaderAuth(), checkoutModel);
    }

}
