package com.cn.uk.dto.CameraRelatedDto;

public class CameraBriefInfoDto {
    private String code;
    private String name;
    private String deviceGroupCode;
    private String parentCode;
    private String domainCode;
    private String deviceModelType;
    private String vendorType;
    private int deviceFormType;
    private int type;
    private String cameraLocation;
    private int cameraStatus;
    private int status;
    private int netType;
    private int isSupportIntelligent;
    private int enableVoice;
    private String nvrCode;
    private String deviceCreateTime;
    private int isExDomain;
    private String deviceIP;
    private String reserve;

    private CameraBriefInfoDto(CameraBriefInfoDtoBuilder cameraBriefInfoDtoBuilder){
        this.code  = cameraBriefInfoDtoBuilder.code;
        this.name = cameraBriefInfoDtoBuilder.name;
        this.deviceGroupCode = cameraBriefInfoDtoBuilder.deviceGroupCode;
        this.parentCode = cameraBriefInfoDtoBuilder.parentCode;
        this.domainCode = cameraBriefInfoDtoBuilder.domainCode;
        this.deviceModelType = cameraBriefInfoDtoBuilder.deviceModelType;
        this.vendorType = cameraBriefInfoDtoBuilder.vendorType;
        this.deviceFormType = cameraBriefInfoDtoBuilder.deviceFormType;
        this.type = cameraBriefInfoDtoBuilder.type;
        this.cameraLocation = cameraBriefInfoDtoBuilder.cameraLocation;
        this.cameraStatus = cameraBriefInfoDtoBuilder.cameraStatus;
        this.status = cameraBriefInfoDtoBuilder.status;
        this.netType = cameraBriefInfoDtoBuilder.netType;
        this.isSupportIntelligent = cameraBriefInfoDtoBuilder.isSupportIntelligent;
        this.enableVoice = cameraBriefInfoDtoBuilder.enableVoice;
        this.nvrCode = cameraBriefInfoDtoBuilder.nvrCode;
        this.deviceCreateTime = cameraBriefInfoDtoBuilder.deviceCreateTime;
        this.isExDomain = cameraBriefInfoDtoBuilder.isExDomain;
        this.deviceIP  = cameraBriefInfoDtoBuilder.deviceIP;
        this.reserve = cameraBriefInfoDtoBuilder.reserve;
    }

    public static class CameraBriefInfoDtoBuilder{
        private String code;
        private String name;
        private String deviceGroupCode;
        private String parentCode;
        private String domainCode;
        private String deviceModelType;
        private String vendorType;
        private int deviceFormType;
        private int type;
        private String cameraLocation;
        private int cameraStatus;
        private int status;
        private int netType;
        private int isSupportIntelligent;
        private int enableVoice;
        private String nvrCode;
        private String deviceCreateTime;
        private int isExDomain;
        private String deviceIP;
        private String reserve;

        public CameraBriefInfoDtoBuilder setCode(String code) {
            this.code = code;
            return this;
        }

        public CameraBriefInfoDtoBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public CameraBriefInfoDtoBuilder setDeviceGroupCode(String deviceGroupCode) {
            this.deviceGroupCode = deviceGroupCode;
            return this;
        }

        public CameraBriefInfoDtoBuilder setParentCode(String parentCode) {
            this.parentCode = parentCode;
            return this;
        }

        public CameraBriefInfoDtoBuilder setDomainCode(String domainCode) {
            this.domainCode = domainCode;
            return this;
        }

        public CameraBriefInfoDtoBuilder setDeviceModelType(String deviceModelType) {
            this.deviceModelType = deviceModelType;
            return this;
        }

        public CameraBriefInfoDtoBuilder setVendorType(String vendorType) {
            this.vendorType = vendorType;
            return this;
        }

        public CameraBriefInfoDtoBuilder setDeviceFormType(int deviceFormType) {
            this.deviceFormType = deviceFormType;
            return this;
        }

        public CameraBriefInfoDtoBuilder setType(int type) {
            this.type = type;
            return this;
        }

        public CameraBriefInfoDtoBuilder setCameraLocation(String cameraLocation) {
            this.cameraLocation = cameraLocation;
            return this;
        }

        public CameraBriefInfoDtoBuilder setCameraStatus(int cameraStatus) {
            this.cameraStatus = cameraStatus;
            return this;
        }

        public CameraBriefInfoDtoBuilder setStatus(int status) {
            this.status = status;
            return this;
        }

        public CameraBriefInfoDtoBuilder setNetType(int netType) {
            this.netType = netType;
            return this;
        }

        public CameraBriefInfoDtoBuilder setIsSupportIntelligent(int isSupportIntelligent) {
            this.isSupportIntelligent = isSupportIntelligent;
            return this;
        }

        public CameraBriefInfoDtoBuilder setEnableVoice(int enableVoice) {
            this.enableVoice = enableVoice;
            return this;
        }

        public CameraBriefInfoDtoBuilder setNvrCode(String nvrCode) {
            this.nvrCode = nvrCode;
            return this;
        }

        public CameraBriefInfoDtoBuilder setDeviceCreateTime(String deviceCreateTime) {
            this.deviceCreateTime = deviceCreateTime;
            return this;
        }

        public CameraBriefInfoDtoBuilder setIsExDomain(int isExDomain) {
            this.isExDomain = isExDomain;
            return this;
        }

        public CameraBriefInfoDtoBuilder setDeviceIP(String deviceIP) {
            this.deviceIP = deviceIP;
            return this;
        }

        public CameraBriefInfoDtoBuilder setReserve(String reserve) {
            this.reserve = reserve;
            return this;
        }

        public CameraBriefInfoDto build(){
               return new CameraBriefInfoDto(this);
        }
    }
}
