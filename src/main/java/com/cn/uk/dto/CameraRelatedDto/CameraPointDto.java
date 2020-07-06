package com.cn.uk.dto.CameraRelatedDto;

public class CameraPointDto {

    private int cameraId;//摄像头id
    private String deviceId;//所属设备id
    private int discernType;//识别类型
    private int meterType;//表计类型
    private String pointName;//点位名称
    private int id;//点位id

    public CameraPointDto(CameraPointDtoBuilder cameraPointDtoBuilder){
        this.cameraId = cameraPointDtoBuilder.cameraId;
        this.deviceId = cameraPointDtoBuilder.deviceId;
        this.discernType = cameraPointDtoBuilder.discernType;
        this.meterType = cameraPointDtoBuilder.meterType;
        this.pointName = cameraPointDtoBuilder.pointName;
        this.id = cameraPointDtoBuilder.id;

    }

    public static class CameraPointDtoBuilder{
        private int cameraId;//摄像头id
        private String deviceId;//所属设备id
        private int discernType;//识别类型
        private int meterType;//表计类型
        private String pointName;//点位名称
        private int id;//点位id

        public CameraPointDto build(){
            return new CameraPointDto(this);
        }

        public CameraPointDtoBuilder setCameraId(int cameraId) {
            this.cameraId = cameraId;
            return this;
        }

        public CameraPointDtoBuilder setDeviceId(String deviceId) {
            this.deviceId = deviceId;
            return this;
        }

        public CameraPointDtoBuilder setDiscernType(int discernType) {
            this.discernType = discernType;
            return this;
        }

        public CameraPointDtoBuilder setMeterType(int meterType) {
            this.meterType = meterType;
            return this;
        }

        public CameraPointDtoBuilder setPointName(String pointName) {
            this.pointName = pointName;
            return this;
        }

        public CameraPointDtoBuilder setId(int id) {
            this.id = id;
            return this;
        }
    }
}
