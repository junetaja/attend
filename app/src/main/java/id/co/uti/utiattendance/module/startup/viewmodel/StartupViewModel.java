package id.co.uti.utiattendance.module.startup.viewmodel;

import id.co.uti.utiattendance.module.startup.apiservice.StartupApiService;
import zambelz.library.core.base.BaseViewModel;

/**
 * Created by DELL on 8/22/2017.
 */

public class StartupViewModel extends BaseViewModel<StartupViewModel.VMListener> {

    private final StartupApiService startupApiService;

    public StartupViewModel(StartupViewModel.VMListener vmListener,
                            StartupApiService startupApiService) {
        super(vmListener);
        this.startupApiService = startupApiService;
    }

    public interface VMListener {
        void onLoginSuccess();
    }


}
