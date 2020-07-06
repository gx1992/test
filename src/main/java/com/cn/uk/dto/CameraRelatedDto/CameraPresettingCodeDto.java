package com.cn.uk.dto.CameraRelatedDto;

public class CameraPresettingCodeDto {
    private int id;
    private int cameraId;
    private String presettingCode;
    private String presettingName;
    private int discernType;

    public CameraPresettingCodeDto(CameraPresettingCodeDtoBuilder cameraPresettingCodeDtoBuilder){
        this.id = cameraPresettingCodeDtoBuilder.id;
        this.cameraId = cameraPresettingCodeDtoBuilder.cameraId;
        this.presettingCode = cameraPresettingCodeDtoBuilder.presettingCode;
        this.presettingName = cameraPresettingCodeDtoBuilder.presettingName;
        this.discernType = cameraPresettingCodeDtoBuilder.discernType;
    }

    public static class CameraPresettingCodeDtoBuilder{
        private int id;
        private int cameraId;
        private String presettingCode;
        private String presettingName;
        private int discernType;

        public CameraPresettingCodeDto build(){
            return new CameraPresettingCodeDto(this);
        }
        public CameraPresettingCodeDtoBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public CameraPresettingCodeDtoBuilder setCameraId(int cameraId) {
            this.cameraId = cameraId;
            return this;
        }

        public CameraPresettingCodeDtoBuilder setPresettingCode(String presettingCode) {
            this.presettingCode = presettingCode;
            return this;
        }

        public CameraPresettingCodeDtoBuilder setPresettingName(String presettingName) {
            this.presettingName = presettingName;
            return this;
        }

        public CameraPresettingCodeDtoBuilder setDiscernType(int discernType) {
            this.discernType = discernType;
            return this;
        }
    }
}
