
package com.lnzn.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>send_patrol_point complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="send_patrol_point">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="substation_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="robot_name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="robotMac" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="task_name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="task_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="jmap" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "send_patrol_point", propOrder = {
    "substationId",
    "robotName",
    "robotMac",
    "taskName",
    "taskId",
    "jmap"
})
public class SendPatrolPoint {

    @XmlElement(name = "substation_id")
    protected String substationId;
    @XmlElement(name = "robot_name")
    protected String robotName;
    @XmlElement(name = "robot_mac")
    protected String robotMac;
    @XmlElement(name = "task_name")
    protected String taskName;
    @XmlElement(name = "task_id")
    protected String taskId;
    protected String jmap;

    /**
     * ��ȡsubstationId���Ե�ֵ��
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
     * ����substationId���Ե�ֵ��
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
     * ��ȡrobotName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRobotName() {
        return robotName;
    }

    /**
     * ����robotName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRobotName(String value) {
        this.robotName = value;
    }

    /**
     * ��ȡrobotMac���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRobotMac() {
        return robotMac;
    }

    /**
     * ����robotMac���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRobotMac(String value) {
        this.robotMac = value;
    }

    /**
     * ��ȡtaskName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * ����taskName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaskName(String value) {
        this.taskName = value;
    }

    /**
     * ��ȡtaskId���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * ����taskId���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaskId(String value) {
        this.taskId = value;
    }

    /**
     * ��ȡjmap���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJmap() {
        return jmap;
    }

    /**
     * ����jmap���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJmap(String value) {
        this.jmap = value;
    }

}
