package id.co.uti.utiattendance.helper.navigator;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by nandajulianda on 12/13/16.
 */

public class AppToolbar {

    private final AppCompatActivity activity;
    private Toolbar toolbar;

    public AppToolbar(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void setToolbar(@NonNull Toolbar toolbar) {
        this.toolbar = toolbar;
        setSupportActionBar();
    }

    public void setToolbar(Toolbar toolbar, int icon) {
        setToolbar(toolbar);
        setIcon(icon);
    }

    public void setToolbar(Toolbar toolbar, String title) {
        setToolbar(toolbar);
        setTitle(title);
    }

    private void setSupportActionBar() {
        activity.setSupportActionBar(toolbar);
    }

    private ActionBar getSupportActionBar() {
        ActionBar actionBar = activity.getSupportActionBar();
        if(actionBar == null) {
            throw new NullPointerException("toolbar is null");
        }
        return actionBar;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public void setDisplayHomeAsUpEnabled(boolean state) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(state);
    }

    public void setDisplayShowTitleEnabled(boolean state) {
        getSupportActionBar().setDisplayShowTitleEnabled(state);
    }

    public void setIcon(int icon) {
        setDisplayShowTitleEnabled(false);
        getSupportActionBar().setIcon(icon);
    }

    public void setIcon(int icon, View.OnClickListener listener) {
        setIcon(icon);
        setNavigationClick(listener);
    }

    public void setTitle(String title) {
        setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(title);
    }

    public void setTitle(int title) {
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(title);
    }

    public void setTitle(String title, View.OnClickListener listener) {
        setTitle(title);
        setNavigationClick(listener);
    }

    public void setTitle(int title, View.OnClickListener listener) {
        setTitle(title);
        setNavigationClick(listener);
    }

    public void setNavigationClick(@Nullable View.OnClickListener listener) {
        if(listener != null) {
            setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(listener);
        }
    }

}
