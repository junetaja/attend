package id.co.uti.utiattendance.module.attendance.apiservice;

import java.util.Map;

import id.co.uti.utiattendance.helper.model.ResultModel;
import id.co.uti.utiattendance.module.attendance.model.ActivitiesModel;
import id.co.uti.utiattendance.module.attendance.model.CheckinModel;
import id.co.uti.utiattendance.module.attendance.model.CheckoutModel;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by DELL on 8/22/2017.
 */

public interface AttendanceApiInterface {

    @GET("getcheckin")
    Observable<ResultModel<ActivitiesModel>> getDataWhitId(@HeaderMap Map<String, String> header,
                                                           @Query("userrid") int id);

    @POST("checkin")
    Observable<ResultModel<CheckinModel>> postCheckin(@HeaderMap Map<String, String> header,
                                                      @Body CheckinModel checkinModel);

    @POST("checkout")
    Observable<ResultModel<CheckoutModel>> postCheckout(@HeaderMap Map<String, String> header,
                                                      @Body CheckoutModel checkoutModel);


}
