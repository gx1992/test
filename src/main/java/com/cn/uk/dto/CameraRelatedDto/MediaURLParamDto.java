package com.cn.uk.dto.CameraRelatedDto;

public class MediaURLParamDto {
    private int serviceType;
    private int packProtocolType;
    private TimeSpanDto timeSpan;
    private int streamType;
    private int protocolType;
    private int transMode;
    private int broadCastType;
    private int clientType;

    public int getServiceType() {
        return serviceType;
    }

    public void setServiceType(int serviceType) {
        this.serviceType = serviceType;
    }

    public int getPackProtocolType() {
        return packProtocolType;
    }

    public void setPackProtocolType(int packProtocolType) {
        this.packProtocolType = packProtocolType;
    }

    public TimeSpanDto getTimeSpan() {
        return timeSpan;
    }

    public void setTimeSpan(TimeSpanDto timeSpan) {
        this.timeSpan = timeSpan;
    }

    public int getStreamType() {
        return streamType;
    }

    public void setStreamType(int streamType) {
        this.streamType = streamType;
    }

    public int getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(int protocolType) {
        this.protocolType = protocolType;
    }

    public int getTransMode() {
        return transMode;
    }

    public void setTransMode(int transMode) {
        this.transMode = transMode;
    }

    public int getBroadCastType() {
        return broadCastType;
    }

    public void setBroadCastType(int broadCastType) {
        this.broadCastType = broadCastType;
    }

    public int getClientType() {
        return clientType;
    }

    public void setClientType(int clientType) {
        this.clientType = clientType;
    }
}
