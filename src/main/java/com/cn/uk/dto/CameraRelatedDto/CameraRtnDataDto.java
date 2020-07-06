package com.cn.uk.dto.CameraRelatedDto;

public class CameraRtnDataDto {

    private String substation;
    private int resultCode;
    private String rtspURL;
    private CameraBriefsDto cameraBriefInfos;
    private AudioBriefsDto audioBriefInfos;
    private AlarmBriefsDto alarmBriefInfos;

    public String getRtspURL() {
        return rtspURL;
    }

    public void setRtspURL(String rtspURL) {
        this.rtspURL = rtspURL;
    }

    public String getSubstation() {
        return substation;
    }

    public void setSubstation(String substation) {
        this.substation = substation;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public CameraBriefsDto getCameraBriefInfos() {
        return cameraBriefInfos;
    }

    public void setCameraBriefInfos(CameraBriefsDto cameraBriefInfos) {
        this.cameraBriefInfos = cameraBriefInfos;
    }

    public AudioBriefsDto getAudioBriefInfos() {
        return audioBriefInfos;
    }

    public void setAudioBriefInfos(AudioBriefsDto audioBriefInfos) {
        this.audioBriefInfos = audioBriefInfos;
    }

    public AlarmBriefsDto getAlarmBriefInfos() {
        return alarmBriefInfos;
    }

    public void setAlarmBriefInfos(AlarmBriefsDto alarmBriefInfos) {
        this.alarmBriefInfos = alarmBriefInfos;
    }
}
