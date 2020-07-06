package com.cn.uk.dto.CameraRelatedDto;

import java.util.List;

public class CameraAndPointsInfoDto {

    private String camBrands;
    private List<CameraPointDto> cameraPointList;
    private List<CameraPresettingCodeDto> cameraPresettingList;
    private String channelAlias;
    private String devAddress;
    private String devPort;
    private String devUser;
    private String devPwd;
    private int channelId;
    private String orgName;
    private String powerName;
    private String powerNum;
    private String openTasks;
    private String camType;
    private String camModel;
    private String remark;
    private String status;
    private String cameraCode;


    public CameraAndPointsInfoDto(CameraAndPointsInfoDtoBuilder cameraAndPointsInfoDtoBuilder){
         this.camBrands = cameraAndPointsInfoDtoBuilder.camBrands;
         this.cameraPointList =  cameraAndPointsInfoDtoBuilder.cameraPointList;
         this.cameraPresettingList =  cameraAndPointsInfoDtoBuilder.cameraPresettingList;
         this.channelAlias =  cameraAndPointsInfoDtoBuilder.channelAlias;
         this.devAddress =  cameraAndPointsInfoDtoBuilder.devAddress;
         this.devPort =  cameraAndPointsInfoDtoBuilder.devPort;
         this.devUser =  cameraAndPointsInfoDtoBuilder.devUser;
         this.devPwd =  cameraAndPointsInfoDtoBuilder.devPwd;
         this.channelId  =  cameraAndPointsInfoDtoBuilder.channelId;
         this.orgName =  cameraAndPointsInfoDtoBuilder.orgName;
         this.powerName =  cameraAndPointsInfoDtoBuilder.powerName;
         this.powerNum =  cameraAndPointsInfoDtoBuilder.powerNum;
         this.openTasks =  cameraAndPointsInfoDtoBuilder.openTasks;
         this.camType =  cameraAndPointsInfoDtoBuilder.camType;
         this.camModel =  cameraAndPointsInfoDtoBuilder.camModel;
         this.remark =  cameraAndPointsInfoDtoBuilder.remark;
         this.status =  cameraAndPointsInfoDtoBuilder.status;
         this.cameraCode =  cameraAndPointsInfoDtoBuilder.cameraCode;
    }
    public static class CameraAndPointsInfoDtoBuilder{
        private String camBrands;
        private List<CameraPointDto> cameraPointList;
        private List<CameraPresettingCodeDto> cameraPresettingList;
        private String channelAlias;
        private String devAddress;
        private String devPort;
        private String devUser;
        private String devPwd;
        private int channelId;
        private String orgName;
        private String powerName;
        private String powerNum;
        private String openTasks;
        private String camType;
        private String camModel;
        private String remark;
        private String status;
        private String cameraCode;

        public CameraAndPointsInfoDto build(){
            return new CameraAndPointsInfoDto(this);
        }

        public CameraAndPointsInfoDtoBuilder setCamBrands(String camBrands) {
            this.camBrands = camBrands;
            return this;
        }

        public CameraAndPointsInfoDtoBuilder setCameraPointList(List<CameraPointDto> cameraPointList) {
            this.cameraPointList = cameraPointList;
            return this;
        }

        public CameraAndPointsInfoDtoBuilder setCameraPresettingList(List<CameraPresettingCodeDto> cameraPresettingList) {
            this.cameraPresettingList = cameraPresettingList;
            return this;
        }

        public CameraAndPointsInfoDtoBuilder setChannelAlias(String channelAlias) {
            this.channelAlias = channelAlias;
            return this;
        }

        public CameraAndPointsInfoDtoBuilder setDevAddress(String devAddress) {
            this.devAddress = devAddress;
            return this;
        }

        public CameraAndPointsInfoDtoBuilder setDevPort(String devPort) {
            this.devPort = devPort;
            return this;
        }

        public CameraAndPointsInfoDtoBuilder setDevUser(String devUser) {
            this.devUser = devUser;
            return this;
        }

        public CameraAndPointsInfoDtoBuilder setDevPwd(String devPwd) {
            this.devPwd = devPwd;
            return this;
        }

        public CameraAndPointsInfoDtoBuilder  setChannelId(int channelId) {
            this.channelId = channelId;
            return this;
        }

        public CameraAndPointsInfoDtoBuilder setOrgName(String orgName) {
            this.orgName = orgName;
            return this;
        }

        public CameraAndPointsInfoDtoBuilder setPowerName(String powerName) {
            this.powerName = powerName;
            return this;
        }

        public CameraAndPointsInfoDtoBuilder setPowerNum(String powerNum) {
            this.powerNum = powerNum;
            return this;
        }

        public CameraAndPointsInfoDtoBuilder setOpenTasks(String openTasks) {
            this.openTasks = openTasks;
            return this;
        }

        public CameraAndPointsInfoDtoBuilder setCamType(String camType) {
            this.camType = camType;
            return this;
        }

        public CameraAndPointsInfoDtoBuilder setCamModel(String camModel) {
            this.camModel = camModel;
            return this;
        }

        public CameraAndPointsInfoDtoBuilder setRemark(String remark) {
            this.remark = remark;
            return this;
        }

        public CameraAndPointsInfoDtoBuilder setStatus(String status) {
            this.status = status;
            return this;
        }

        public CameraAndPointsInfoDtoBuilder setCameraCode(String cameraCode) {
            this.cameraCode = cameraCode;
            return this;
        }
    }
}
