package id.co.uti.utiattendance.module.attendance.activity;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Chronometer;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.co.uti.utiattendance.R;
import id.co.uti.utiattendance.helper.HelperModule;
import id.co.uti.utiattendance.helper.location.GPSTracker;
import id.co.uti.utiattendance.helper.model.ResultModel;
import id.co.uti.utiattendance.helper.navigator.AppNavigator;
import id.co.uti.utiattendance.module.attendance.AttendanceComponent;
import id.co.uti.utiattendance.module.attendance.AttendanceModule;
import id.co.uti.utiattendance.module.attendance.DaggerAttendanceComponent;
import id.co.uti.utiattendance.module.attendance.fragment.AddDetailLocationDialogFragment;
import id.co.uti.utiattendance.module.attendance.model.CheckinModel;
import id.co.uti.utiattendance.module.attendance.model.CheckoutModel;
import id.co.uti.utiattendance.module.attendance.viewmodel.AttendanceViewModel;
import zambelz.library.core.base.BaseActivity;
import zambelz.library.core.interfaces.ComponentIntegrator;

/**
 * Created by DELL on 8/22/2017.
 */

public class AttendanceActivity extends BaseActivity implements ComponentIntegrator<AttendanceComponent>,
        AttendanceViewModel.VMListener, AddDetailLocationDialogFragment.Listener {

    @Inject GPSTracker gpsTracker;
    @Inject AppNavigator appNavigator;
    @Inject AttendanceViewModel attendanceViewModel;
    @Inject AddDetailLocationDialogFragment addDetailLocationDialogFragment;

    @BindView(R.id.card1) android.support.v7.widget.CardView checkIn;
    @BindView(R.id.card2) android.support.v7.widget.CardView checkOut;
    @BindView(R.id.card3) android.support.v7.widget.CardView activities;
    @BindView(R.id.card4) android.support.v7.widget.CardView profile;
    @BindView(R.id.chronometer) Chronometer chronometer;
    @BindView(R.id.txtLocation) TextView txtLocation;

    private static String locationIn;

    @Override
    public Intent getCallingIntent(Context context, @Nullable Object... params) {
        return new Intent(context, AttendanceActivity.class);
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendance_layout);
        ButterKnife.bind(this);
    }

    @Override
    public void initializeInjector() {
        getComponent().inject(this);
    }

    @Override
    public void initialContent() {
        attendanceViewModel.getPermission();
        if (!attendanceViewModel.getLocationIn().trim().isEmpty()) {
            txtLocation.setText("Check In Location : " + attendanceViewModel.getLocationIn());
        }

        if (attendanceViewModel.getTimeStartElapsed() != 0) {
            chronometer.setBase(attendanceViewModel.getTimeStartElapsed());
            chronometer.start();
        }
    }


//    private void initChrono() {
//        chronometer.setOnChronometerTickListener(chronometer1 -> {
//            long time = SystemClock.elapsedRealtime() - chronometer1.getBase();
//            int h   = (int)(time /3600000);
//            int m = (int)(time - h*3600000)/60000;
//            int s= (int)(time - h*3600000- m*60000)/1000 ;
//            String hh = h < 10 ? "0"+h: h+"";
//            String mm = m < 10 ? "0"+m: m+"";
//            String ss = s < 10 ? "0"+s: s+"";
//            chronometer1.setText(hh+":"+mm+":"+ss);
//        });
//    }

    @OnClick(R.id.card1)
    public void checkIn_Clicked() {

        if (gpsTracker.canGetLocation()){
            double latitude, longitude;
            latitude = gpsTracker.getLatitude();
            longitude = gpsTracker.getLongitude();
            if (chronometer.getText().toString().equals("00:00")) {
                addDetailLocationDialogFragment.setLocationIn(reverseGeocoder(latitude, longitude));
                addDetailLocationDialogFragment.showDialog(getSupportFragmentManager());
            }
//            if (chronometer.getText().toString().equals("00:00")) {
//                if (!chronometer.isEnabled()) {
//                    chronometer.setEnabled(true);
//                }
//
//                chronometer.setBase(SystemClock.elapsedRealtime());
//                chronometer.start();
//                attendanceViewModel.setStartElapsedTime(SystemClock.elapsedRealtime());

//                CheckinModel checkinModel = new CheckinModel();
//                checkinModel.userid = attendanceViewModel.getUserId();
//                checkinModel.latitude = gpsTracker.getLatitude();
//                checkinModel.longitude = gpsTracker.getLongitude();
//                checkinModel.location_in = "";
//                checkinModel.note = "";
//                checkinModel.device = "A";
//
//                attendanceViewModel.postCheckin(checkinModel);
//                relative2.setVisibility(View.VISIBLE);
//                txtLocation.setText("Check In Location : "+reverseGeocoder(latitude, longitude));
//                locationIn = reverseGeocoder(latitude, longitude);
//            }
        } else {
            gpsTracker.getCurrentLocation();
            Toast.makeText(this, "getting current location", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.card2)
    public void checkOut_Clicked() {
        chronometer.stop();
        chronometer.setEnabled(false);
        attendanceViewModel.setStartElapsedTime(0);
        chronometer.setText("00:00");
        attendanceViewModel.setLocationIn("");
        txtLocation.setText("");

        CheckoutModel checkoutModel = new CheckoutModel();
        checkoutModel.latitude = gpsTracker.getLatitude();
        checkoutModel.longitude = gpsTracker.getLongitude();
        checkoutModel.location_out = "";

        attendanceViewModel.postCheckout(checkoutModel);
    }

    private String reverseGeocoder(double latitude, double longitude) {
        String location = "";
        Geocoder myLocation = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> myList = myLocation.getFromLocation(latitude, longitude, 1);
            if (myList.size() != 0) {
                location  = myList.get(0).getAddressLine(0);
            } else {
                location = "Cant get location, just add detail location below";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return location;
    }

    @OnClick(R.id.card3)
    public void activities_Clicked() {
        appNavigator.toActivitiesActivity(this);
    }

    @OnClick(R.id.card4)
    public void profile_Clicked() {
        appNavigator.toProfileActivity(this);
    }


    @Override
    public void onPostCheckinSuccess(ResultModel<CheckinModel> checkinModelResultModel) {

    }

    @Override
    public void onPostCheckinError(Throwable error) {

    }

    @Override
    public void onPostCheckoutSuccess(ResultModel<CheckoutModel> checkoutModelResultModel) {

    }

    @Override
    public void onPostCheckoutError(Throwable error) {

    }

    @Override
    public void onPostCheckinSuccess(String locationIn) {
//        if (chronometer.getText().toString().equals("00:00")) {
            if (!chronometer.isEnabled()) {
                chronometer.setEnabled(true);
            }

            txtLocation.setText("Check In Location : " + locationIn);
            attendanceViewModel.setLocationIn(locationIn);
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
            attendanceViewModel.setStartElapsedTime(SystemClock.elapsedRealtime());
            addDetailLocationDialogFragment.dismissDialog();
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (gpsTracker.canGetLocation()){
            gpsTracker.stopUsingGPS();
        }
    }
}
