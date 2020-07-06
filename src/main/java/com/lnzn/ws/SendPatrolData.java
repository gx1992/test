
package com.lnzn.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>send_patrol_data complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="send_patrol_data">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="substation_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="jrt_device_rl_list" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "send_patrol_data", propOrder = {
    "substationId",
    "jrtDeviceRlList"
})
public class SendPatrolData {

    @XmlElement(name = "substation_id")
    protected String substationId;
    @XmlElement(name = "jrt_device_rl_list")
    protected String jrtDeviceRlList;

    /**
     * 获取substationId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubstationId() {
        return substationId;
    }

    /**
     * 设置substationId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubstationId(String value) {
        this.substationId = value;
    }

    /**
     * 获取jrtDeviceRlList属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJrtDeviceRlList() {
        return jrtDeviceRlList;
    }

    /**
     * 设置jrtDeviceRlList属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJrtDeviceRlList(String value) {
        this.jrtDeviceRlList = value;
    }

}
