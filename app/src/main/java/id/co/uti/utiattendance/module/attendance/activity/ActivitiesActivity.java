package id.co.uti.utiattendance.module.attendance.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.uti.utiattendance.R;
import id.co.uti.utiattendance.helper.HelperModule;
import id.co.uti.utiattendance.helper.model.ResultModel;
import id.co.uti.utiattendance.helper.navigator.AppNavigator;
import id.co.uti.utiattendance.helper.views.DefaultRecyclerScrollListener;
import id.co.uti.utiattendance.module.attendance.AttendanceComponent;
import id.co.uti.utiattendance.module.attendance.AttendanceModule;
import id.co.uti.utiattendance.module.attendance.DaggerAttendanceComponent;
import id.co.uti.utiattendance.module.attendance.adapter.ActivitiesListAdapter;
import id.co.uti.utiattendance.module.attendance.model.ActivitiesModel;
import id.co.uti.utiattendance.module.attendance.viewmodel.ActivitiesViewModel;
import zambelz.library.core.base.BaseActivity;
import zambelz.library.core.interfaces.ComponentIntegrator;

/**
 * Created by DELL on 8/23/2017.
 */

public class ActivitiesActivity extends BaseActivity implements ComponentIntegrator<AttendanceComponent>,
        ActivitiesListAdapter.Listener,
        ActivitiesViewModel.VMListener {

    @Inject ActivitiesListAdapter activitiesListAdapter;
    @Inject ActivitiesViewModel activitiesViewModel;
    @Inject AppNavigator appNavigator;

    @BindView(R.id.recyclerLayout) RecyclerView recyclerView;
    @BindView(R.id.swipeLayout) SwipeRefreshLayout swipeRefreshLayout;



    @Override
    public Intent getCallingIntent(Context context, @Nullable Object... params) {
        return new Intent(context, ActivitiesActivity.class);
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_list_layout);
        ButterKnife.bind(this);
    }

    @Override
    public void initializeInjector() {
        getComponent().inject(this);
    }

    @Override
    public void initialContent() {
        fetchData();
        initializeView();
    }

    private void initializeView() {
        swipeRefreshLayout.setOnRefreshListener(this::fetchData);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(activitiesListAdapter);
        recyclerView.addOnScrollListener(new DefaultRecyclerScrollListener(swipeRefreshLayout));
    }

    private void fetchData() {
        swipeRefreshLayout.setRefreshing(true);
        addSubscription(activitiesViewModel.getDataWithId(10103));
    }


    @Override
    public void onItemClicked(ActivitiesModel activitiesModel) {
        Toast.makeText(this, activitiesModel.duration, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadActivitiesSuccess(ResultModel<ActivitiesModel> activitiesModelResultModel) {
        swipeRefreshLayout.setRefreshing(false);
        activitiesListAdapter.clearAll();
        activitiesListAdapter.addAll(activitiesModelResultModel.data);
    }

    @Override
    public void onLoadActivitiesError(Throwable error) {
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();
    }
}
