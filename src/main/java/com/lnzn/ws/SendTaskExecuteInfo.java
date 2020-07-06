
package com.lnzn.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>send_task_execute_info complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="send_task_execute_info">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="substation_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="jtask_rl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "send_task_execute_info", propOrder = {
    "substationId",
    "jtaskRl"
})
public class SendTaskExecuteInfo {

    @XmlElement(name = "substation_id")
    protected String substationId;
    @XmlElement(name = "jtask_rl")
    protected String jtaskRl;

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
     * 获取jtaskRl属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJtaskRl() {
        return jtaskRl;
    }

    /**
     * 设置jtaskRl属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJtaskRl(String value) {
        this.jtaskRl = value;
    }

}
