package com.cn.uk.dto.CameraRelatedDto;

public class PushPayLoadDto {
    private String message;
    private PushAlarmMessageDto alarmMsg;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PushAlarmMessageDto getAlarmMsg() {
        return alarmMsg;
    }

    public void setAlarmMsg(PushAlarmMessageDto alarmMsg) {
        this.alarmMsg = alarmMsg;
    }
}
