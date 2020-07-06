package com.cn.uk.model;

public class TigCameraSetup {
    private int ig_preview_rows;
    private int ig_preview_columns;
    private int ig_preview_interval_secs;
    private int ig_capture_interval_secs;
    private String ig_capture_path;
    private String ig_analyze_path;
    private String ig_alarm_img_path;

    public int getIg_preview_rows() {
        return ig_preview_rows;
    }

    public void setIg_preview_rows(int ig_preview_rows) {
        this.ig_preview_rows = ig_preview_rows;
    }

    public int getIg_preview_columns() {
        return ig_preview_columns;
    }

    public void setIg_preview_columns(int ig_preview_columns) {
        this.ig_preview_columns = ig_preview_columns;
    }

    public int getIg_preview_interval_secs() {
        return ig_preview_interval_secs;
    }

    public void setIg_preview_interval_secs(int ig_preview_interval_secs) {
        this.ig_preview_interval_secs = ig_preview_interval_secs;
    }

    public int getIg_capture_interval_secs() {
        return ig_capture_interval_secs;
    }

    public void setIg_capture_interval_secs(int ig_capture_interval_secs) {
        this.ig_capture_interval_secs = ig_capture_interval_secs;
    }

    public String getIg_capture_path() {
        return ig_capture_path;
    }

    public void setIg_capture_path(String ig_capture_path) {
        this.ig_capture_path = ig_capture_path;
    }

    public String getIg_analyze_path() {
        return ig_analyze_path;
    }

    public void setIg_analyze_path(String ig_analyze_path) {
        this.ig_analyze_path = ig_analyze_path;
    }

    public String getIg_alarm_img_path() {
        return ig_alarm_img_path;
    }

    public void setIg_alarm_img_path(String ig_alarm_img_path) {
        this.ig_alarm_img_path = ig_alarm_img_path;
    }
}
