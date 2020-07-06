
package com.lnzn.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>send_fault_value complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="send_fault_value">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="substation_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="jalarm_data_rl_list" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "send_fault_value", propOrder = {
    "substationId",
    "jalarmDataRlList"
})
public class SendFaultValue {

    @XmlElement(name = "substation_id")
    protected String substationId;
    @XmlElement(name = "jalarm_data_rl_list")
    protected String jalarmDataRlList;

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
     * 获取jalarmDataRlList属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJalarmDataRlList() {
        return jalarmDataRlList;
    }

    /**
     * 设置jalarmDataRlList属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJalarmDataRlList(String value) {
        this.jalarmDataRlList = value;
    }

}
