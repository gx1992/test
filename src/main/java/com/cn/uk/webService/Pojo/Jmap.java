/**
 * Copyright (C), 2015-2020, 南京悠阔电气科技有限公司
 * 类名: Jmap
 * 创建者:   高旭
 * 生成日期:     2020/6/5 13:42
 * 描述: 地图点
 * 修改历史:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.uk.webService.Pojo;

/**
 * 〈功能简述〉<br> 
 * 〈地图点〉
 *
 * @author gaoxu
 * @create 2020/6/5 13:42
 * @since 1.0.0
 */
public class Jmap {

    private String file_name;
    private Integer jpoint_type;
    private Double  map_x;
    private Double map_y;
    private Double map_z;
    private String link_id;
    private String next_link_id;
    private Float distance;
    private String time	;


    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public Integer getJpoint_type() {
        return jpoint_type;
    }

    public void setJpoint_type(Integer jpoint_type) {
        this.jpoint_type = jpoint_type;
    }

    public Double getMap_x() {
        return map_x;
    }

    public void setMap_x(Double map_x) {
        this.map_x = map_x;
    }

    public Double getMap_y() {
        return map_y;
    }

    public void setMap_y(Double map_y) {
        this.map_y = map_y;
    }

    public Double getMap_z() {
        return map_z;
    }

    public void setMap_z(Double map_z) {
        this.map_z = map_z;
    }

    public String getLink_id() {
        return link_id;
    }

    public void setLink_id(String link_id) {
        this.link_id = link_id;
    }

    public String getNext_link_id() {
        return next_link_id;
    }

    public void setNext_link_id(String next_link_id) {
        this.next_link_id = next_link_id;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
