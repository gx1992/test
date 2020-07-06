package com.cn.uk.dto.CameraRelatedDto;

import java.util.List;

public class CameraBriefInfoListDto {
    private List<CameraBriefInfoDto> cameraBriefInfo;

    private CameraBriefInfoListDto(CameraBriefInfoListDtoBuilder cameraBriefInfoListDtoBuilder){
               this.cameraBriefInfo = cameraBriefInfoListDtoBuilder.cameraBriefInfo;
    }

    public static class CameraBriefInfoListDtoBuilder{
        private List<CameraBriefInfoDto> cameraBriefInfo;

        public CameraBriefInfoListDtoBuilder setCameraBriefInfo(List<CameraBriefInfoDto> cameraBriefInfo) {
            this.cameraBriefInfo = cameraBriefInfo;
            return this;
        }

        public CameraBriefInfoListDto build(){
            return new CameraBriefInfoListDto(this);
        }
    }
}
