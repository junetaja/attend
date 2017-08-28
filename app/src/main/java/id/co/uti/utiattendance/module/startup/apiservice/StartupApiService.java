package id.co.uti.utiattendance.module.startup.apiservice;

import id.co.uti.utiattendance.helper.data.DataHelper;
import id.co.uti.utiattendance.helper.model.ResultModel;
import id.co.uti.utiattendance.module.user.apiservice.UserApiInterface;
import id.co.uti.utiattendance.module.user.model.LoginModel;
import retrofit2.Retrofit;
import rx.Observable;
import zambelz.library.core.base.BaseApiService;

/**
 * Created by DELL on 8/22/2017.
 */

public class StartupApiService extends BaseApiService{

    private final DataHelper dataHelper;

    public StartupApiService(Retrofit retrofit, DataHelper dataHelper) {
        super(retrofit);
        this.dataHelper = dataHelper;
    }

    public Observable<ResultModel> login(LoginModel model) {
        return createNetworkRequest(UserApiInterface.class)
                .login(dataHelper.getHeaderAuth(), model);
    }
}
