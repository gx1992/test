package com.cn.uk.dto.CameraRelatedDto;

public class CameraBriefsDto {

    private int total;
    private IndexRange indexRange;
    private CameraBriefInfoListDto cameraBriefInfoList;

    public CameraBriefsDto(CameraBriefsDtoBuilder cameraBriefsDtoBuilder){
        this.total = cameraBriefsDtoBuilder.total;
        this.indexRange = cameraBriefsDtoBuilder.indexRange;
        this.cameraBriefInfoList = cameraBriefsDtoBuilder.cameraBriefInfoList;
    }
    public static class CameraBriefsDtoBuilder{
        private int total;
        private IndexRange indexRange;
        private CameraBriefInfoListDto cameraBriefInfoList;

        public CameraBriefsDto build(){
            return new CameraBriefsDto(this);
        }

        public CameraBriefsDtoBuilder setTotal(int total) {
            this.total = total;
            return this;
        }

        public CameraBriefsDtoBuilder setIndexRange(IndexRange indexRange) {
            this.indexRange = indexRange;
            return this;
        }

        public CameraBriefsDtoBuilder setCameraBriefInfoList(CameraBriefInfoListDto cameraBriefInfoList) {
            this.cameraBriefInfoList = cameraBriefInfoList;
            return this;
        }
    }
}
