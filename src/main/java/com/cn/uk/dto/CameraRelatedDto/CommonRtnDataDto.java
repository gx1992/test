package com.cn.uk.dto.CameraRelatedDto;

public class CommonRtnDataDto {

    private int code;
    private String message;
    private boolean success;
    private Object data;


    public static CommonRtnDataDto ok(Object data){
        CommonRtnDataDto commonRtnDataDto = new CommonRtnDataDto();
        commonRtnDataDto.setCode(0);
        commonRtnDataDto.setSuccess(true);
        commonRtnDataDto.setMessage("SUCCESS");
        commonRtnDataDto.setData(data);
        return commonRtnDataDto;
    }

    public static CommonRtnDataDto fail(Object data){
        CommonRtnDataDto commonRtnDataDto = new CommonRtnDataDto();
        commonRtnDataDto.setCode(1);
        commonRtnDataDto.setSuccess(false);
        commonRtnDataDto.setMessage("FAIL");
        commonRtnDataDto.setData(data);
        return commonRtnDataDto;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
