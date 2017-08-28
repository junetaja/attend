package id.co.uti.utiattendance.helper.model;

import java.util.List;

/**
 * Created by DELL on 7/20/2017.
 */

public class ResultModel<T> {
    public int code;
    public String status;
    public List<T> data;
}
