
package com.lnzn.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>sync_task_templet complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="sync_task_templet">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="substation_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="jtask_link" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sync_task_templet", propOrder = {
    "substationId",
    "jtaskLink"
})
public class SyncTaskTemplet {

    @XmlElement(name = "substation_id")
    protected String substationId;
    @XmlElement(name = "jtask_link")
    protected String jtaskLink;

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
     * 获取jtaskLink属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJtaskLink() {
        return jtaskLink;
    }

    /**
     * 设置jtaskLink属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJtaskLink(String value) {
        this.jtaskLink = value;
    }

}
