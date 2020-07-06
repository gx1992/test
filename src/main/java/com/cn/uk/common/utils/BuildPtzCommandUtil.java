package com.cn.uk.common.utils;

import com.alibaba.fastjson.JSON;
import net.bytebuddy.asm.Advice.This;

import java.util.HashMap;
import java.util.Map;

public class BuildPtzCommandUtil {

    private String command;
    private PtzCommandDetail data;
    private static Map<Integer,String> controlModeMap = new HashMap<>();
    static {
        controlModeMap.put(1, "PTZ_STOP");
        controlModeMap.put(2, "PTZ_UP");
        controlModeMap.put(3, "PTZ_DOWN");
        controlModeMap.put(4, "PTZ_LEFT");
        controlModeMap.put(5, "PTZ_UP_LEFT");
        controlModeMap.put(6, "PTZ_DOWN_LEFT");
        controlModeMap.put(7, "PTZ_RIGHT");
        controlModeMap.put(8, "PTZ_UP_RIGHT");
        controlModeMap.put(9, "PTZ_DOWN_RIGHT");
        controlModeMap.put(10, "PTZ_AUTO");
        controlModeMap.put(11, "PTZ_PREFAB_BIT_RUN");
        controlModeMap.put(12, "PTZ_CRUISE_RUN");
        controlModeMap.put(13, "PTZ_CRUISE_STOP");
        controlModeMap.put(14, "PTZ_MODE_CRUISE_RUN");
        controlModeMap.put(15, "PTZ_MODE_CRUISE_STOP");
        controlModeMap.put(16, "PTZ_MENU_OPEN");
        controlModeMap.put(17, "PTZ_MENU_EXIT");
        controlModeMap.put(18, "PTZ_MENU_ENTER");
        controlModeMap.put(19, "PTZ_FLIP");
        controlModeMap.put(20, "PTZ_START");
        controlModeMap.put(21, "PTZ_LENS_APERTURE_OPEN");
        controlModeMap.put(22, "PTZ_LENS_APERTURE_CLOSE");
        controlModeMap.put(23, "PTZ_LENS_ZOOM_IN");
        controlModeMap.put(24, "PTZ_LENS_ZOOM_OUT");
        controlModeMap.put(25, "PTZ_LENS_FOCAL_NEAR");
        controlModeMap.put(26, "PTZ_LENS_FOCAL_FAR");
        controlModeMap.put(27, "PTZ_AUX_OPEN");
        controlModeMap.put(28, "PTZ_AUX_STOP");
        controlModeMap.put(29, "MODE_SET_START");
        controlModeMap.put(30, "MODE_SET_STO");
        controlModeMap.put(31, "PTZ_FAST_LOCATE");
        controlModeMap.put(32, "PTZ_HORIZONTAL_SCAN");
        controlModeMap.put(33, "PTZ_VERTICAL_SCAN");
        controlModeMap.put(34, "PTZ_LOCK");
    }
    public static String buildPtzCommand(String cameraCode, int controlCode,String controlPara1,String controlPara2){
        BuildPtzCommandUtil buildPtzCommandUtil = new BuildPtzCommandUtil();
        buildPtzCommandUtil.setCommand(controlModeMap.get(controlCode));
        PtzCommandDetail ptzCommandDetail = buildPtzCommandUtil.new PtzCommandDetail(cameraCode,controlCode, controlPara1,controlPara2);
        buildPtzCommandUtil.setData(ptzCommandDetail);
        return JSON.toJSONString(buildPtzCommandUtil);
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public PtzCommandDetail getData() {
        return data;
    }

    public void setData(PtzCommandDetail data) {
        this.data = data;
    }

    class PtzCommandDetail{
        private String cameraNo;
        private int controlCode;
        private String param1;
        private String param2;
        PtzCommandDetail(String cameraNo,int controlCode,String param1,String param2){
               this.cameraNo = cameraNo;
               this.controlCode = controlCode;
               this.param1 = param1;
               this.param2 = param2;
        }

        public String getCameraNo() {
            return cameraNo;
        }

        public void setCameraNo(String cameraNo) {
            this.cameraNo = cameraNo;
        }

        public int getControlCode() {
            return controlCode;
        }

        public void setControlCode(int controlCode) {
            this.controlCode = controlCode;
        }

        public String getParam1() {
            return param1;
        }

        public void setParam1(String param1) {
            this.param1 = param1;
        }

        public String getParam2() {
            return param2;
        }

        public void setParam2(String param2) {
            this.param2 = param2;
        }
    }

    public static void main(String[] args){

        String theCommand = BuildPtzCommandUtil.buildPtzCommand("123", 1, "", "");
        System.out.println(theCommand);
    }
}
