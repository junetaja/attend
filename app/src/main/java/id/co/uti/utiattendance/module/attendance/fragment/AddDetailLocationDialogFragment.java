package id.co.uti.utiattendance.module.attendance.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.co.uti.utiattendance.R;
import id.co.uti.utiattendance.helper.HelperModule;
import id.co.uti.utiattendance.helper.model.ResultModel;
import id.co.uti.utiattendance.module.attendance.AttendanceComponent;
import id.co.uti.utiattendance.module.attendance.AttendanceModule;
import id.co.uti.utiattendance.module.attendance.DaggerAttendanceComponent;
import id.co.uti.utiattendance.module.attendance.model.CheckinModel;
import id.co.uti.utiattendance.module.attendance.viewmodel.AddDetailLocationViewModel;
import zambelz.library.core.base.BaseDialogFragment;
import zambelz.library.core.base.BaseFragment;
import zambelz.library.core.interfaces.ComponentIntegrator;

/**
 * Created by DELL on 8/28/2017.
 */

public class AddDetailLocationDialogFragment extends BaseDialogFragment implements ComponentIntegrator<AttendanceComponent>,
        AddDetailLocationViewModel.VMListener {

    @Inject AddDetailLocationViewModel addDetailLocationViewModel;

    @BindView(R.id.etDetailLocation) TextInputEditText etDetailLocation;
    @BindView(R.id.txtLocationIn) TextView txtLocationIn;


    private static final String BUNDLE_LOCAION_IN = "BUNDLE_LOCAION_IN";
    private static final String BUNDLE_SERIALIZE_DATA = "BUNDLE_SERIALIZE_DATA";


    private AddDetailLocationDialogFragment.Listener listener;

    public interface Listener {
        void onPostCheckinSuccess(String location);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void serializedData(String data) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_LOCAION_IN, data);
        setArguments(bundle);
    }

    public void setLocationIn(String location) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_LOCAION_IN, location);
        setArguments(bundle);
    }

    private String getLocationIn() {
        return getArguments().getString(BUNDLE_LOCAION_IN,"");
    }

    private String getBundleSerializeData() {
        return getArguments().getString(BUNDLE_SERIALIZE_DATA,"");
    }

    @Override
    public AttendanceComponent getComponent() {
        return DaggerAttendanceComponent.builder()
                .mainApplicationComponent(getMainApplicationComponent())
                .activityModule(getActivityModule())
                .attendanceModule(new AttendanceModule(this))
                .helperModule(new HelperModule())
                .build();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_detail_location_dialog, container, false);
        ButterKnife.bind(this, view);
        return view;

    }

    @Override
    protected String dialogTag() {
        return "AddDetailLocationFragment";
    }


    @Override
    public void showDialog(FragmentManager manager) {
        super.showDialog(manager);
    }

    @Override
    public void dismissDialog() {
        super.dismissDialog();
    }

    @Override
    public void initializeInjector() {
        getComponent().inject(this);
    }

    @Override
    public void initialContent() {
        txtLocationIn.setText(getLocationIn());
    }



    @OnClick(R.id.btnAdd)
    public void addDetailLocation_Clicked() {
//        addSubscription(addDetailLocationViewModel.postCheckIn());
        listener.onPostCheckinSuccess(getLocationIn());
    }

    @Override
    public void onPostCheckinSuccess(ResultModel<CheckinModel> checkinModelResultModel) {
//        listener.onPostCheckinSuccess(checkinModelResultModel.data.get(0));
    }

    @Override
    public void onPostCheckinError(Throwable error) {

    }
}
