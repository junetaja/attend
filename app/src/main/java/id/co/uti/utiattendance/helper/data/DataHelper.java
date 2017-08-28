package id.co.uti.utiattendance.helper.data;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by DELL on 7/21/2017.
 */

public class DataHelper {

    @Inject
    DataHelper() {

    }

    public Map<String, String> getHeaderAuth() {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");
        return headerMap;
    }
}
