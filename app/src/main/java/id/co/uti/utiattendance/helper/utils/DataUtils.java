package id.co.uti.utiattendance.helper.utils;

import android.content.SharedPreferences;

import javax.inject.Inject;

/**
 * Created by DELL on 8/25/2017.
 */

public class DataUtils {

    private final SharedPreferences sharedPreferences;

    private static final String USER_ID = "USER_ID";
    private static final String USER_NAME = "USER_NAME";
    private static final String IS_LOGED_IN = "IS_LOGED_IN";
    private static final String TIME_START_ELAPSED = "TIME_START_ELAPSED";
    private static final String TIME_END_ELAPSED = "TIME_END_ELAPSED";
    private static final String LOCATION_IN = "LOCATION_IN";

    @Inject
    public DataUtils(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public long getTimeStartElapsed() {
        return sharedPreferences.getLong(TIME_START_ELAPSED, 0);
    }

    public long getTimeEndElapsed() {
        return sharedPreferences.getLong(TIME_END_ELAPSED, 0);
    }

    public void setTimeStartElapsed(long startElapsed) {
        this.sharedPreferences.edit()
                .putLong(TIME_START_ELAPSED, startElapsed)
                .apply();
    }

    public void setTimeEndElapsed(long endElapsed) {
        this.sharedPreferences.edit()
                .putLong(TIME_END_ELAPSED, endElapsed)
                .apply();
    }

    public void storeUserData(String userId, String userName) {
        sharedPreferences.edit()
                .putString(USER_NAME, userName)
                .putString(USER_ID, userId)
                .putBoolean(IS_LOGED_IN, true)
                .apply();
    }

    public void setLocationIn(String location) {
        sharedPreferences.edit()
                .putString(LOCATION_IN, location)
                .apply();
    }

    public boolean isUserLogedIn() {
        return sharedPreferences.getBoolean(IS_LOGED_IN, false);
    }

    public void setLogout() {
        sharedPreferences.edit()
                .putBoolean(IS_LOGED_IN, false)
                .apply();
    }

    public String getPrefUserName() {
        return sharedPreferences.getString(USER_NAME,"");
    }

    public String getPrefUserId() {
        return sharedPreferences.getString(USER_ID,"");
    }

    public String getLocationIn() {
        return sharedPreferences.getString(LOCATION_IN, "");
    }

}
