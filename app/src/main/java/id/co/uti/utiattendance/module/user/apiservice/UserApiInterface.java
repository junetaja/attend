package id.co.uti.utiattendance.module.user.apiservice;

import java.util.Map;

import id.co.uti.utiattendance.helper.model.ResultModel;
import id.co.uti.utiattendance.module.user.model.LoginModel;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by DELL on 8/22/2017.
 */

public interface UserApiInterface {

    @POST("login/")
    Observable<ResultModel> login(@HeaderMap Map<String, String> headers,
                                  @Body LoginModel model);
}
