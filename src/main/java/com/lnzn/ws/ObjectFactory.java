
package com.lnzn.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.lnzn.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SendRobotMotionStateResponse_QNAME = new QName("http://ws.lnzn.com/", "send_robot_motion_stateResponse");
    private final static QName _GetPatrolPlanResponse_QNAME = new QName("http://ws.lnzn.com/", "get_patrol_planResponse");
    private final static QName _SendBodyAlarm_QNAME = new QName("http://ws.lnzn.com/", "send_body_alarm");
    private final static QName _SendTaskFinishInfo_QNAME = new QName("http://ws.lnzn.com/", "send_task_finish_info");
    private final static QName _SendRobotMotionState_QNAME = new QName("http://ws.lnzn.com/", "send_robot_motion_state");
    private final static QName _GetEquipmentInfoResponse_QNAME = new QName("http://ws.lnzn.com/", "get_equipment_infoResponse");
    private final static QName _GetPatrolPlan_QNAME = new QName("http://ws.lnzn.com/", "get_patrol_plan");
    private final static QName _SendMicroClimateResponse_QNAME = new QName("http://ws.lnzn.com/", "send_micro_climateResponse");
    private final static QName _SendTaskFinishInfoResponse_QNAME = new QName("http://ws.lnzn.com/", "send_task_finish_infoResponse");
    private final static QName _SendConfigInfo_QNAME = new QName("http://ws.lnzn.com/", "send_config_info");
    private final static QName _SendFaultValueResponse_QNAME = new QName("http://ws.lnzn.com/", "send_fault_valueResponse");
    private final static QName _SendPatrolDataResponse_QNAME = new QName("http://ws.lnzn.com/", "send_patrol_dataResponse");
    private final static QName _CheckTimeResponse_QNAME = new QName("http://ws.lnzn.com/", "check_timeResponse");
    private final static QName _SendFaultValue_QNAME = new QName("http://ws.lnzn.com/", "send_fault_value");
    private final static QName _SendPatrolPathResponse_QNAME = new QName("http://ws.lnzn.com/", "send_patrol_pathResponse");
    private final static QName _SendControlInfo_QNAME = new QName("http://ws.lnzn.com/", "send_control_info");
    private final static QName _SendRobotState_QNAME = new QName("http://ws.lnzn.com/", "send_robot_state");
    private final static QName _CheckTime_QNAME = new QName("http://ws.lnzn.com/", "check_time");
    private final static QName _GetEquipmentInfo_QNAME = new QName("http://ws.lnzn.com/", "get_equipment_info");
    private final static QName _SendTaskExecuteInfo_QNAME = new QName("http://ws.lnzn.com/", "send_task_execute_info");
    private final static QName _SendPatrolPoint_QNAME = new QName("http://ws.lnzn.com/", "send_patrol_point");
    private final static QName _SendConfigInfoResponse_QNAME = new QName("http://ws.lnzn.com/", "send_config_infoResponse");
    private final static QName _SendPatrolData_QNAME = new QName("http://ws.lnzn.com/", "send_patrol_data");
    private final static QName _SendPatrolPath_QNAME = new QName("http://ws.lnzn.com/", "send_patrol_path");
    private final static QName _SendPatrolPointResponse_QNAME = new QName("http://ws.lnzn.com/", "send_patrol_pointResponse");
    private final static QName _ChangePermissionStateResponse_QNAME = new QName("http://ws.lnzn.com/", "change_permission_stateResponse");
    private final static QName _SendBodyAlarmResponse_QNAME = new QName("http://ws.lnzn.com/", "send_body_alarmResponse");
    private final static QName _SyncTaskTempletResponse_QNAME = new QName("http://ws.lnzn.com/", "sync_task_templetResponse");
    private final static QName _SendTaskExecuteInfoResponse_QNAME = new QName("http://ws.lnzn.com/", "send_task_execute_infoResponse");
    private final static QName _SyncTaskTemplet_QNAME = new QName("http://ws.lnzn.com/", "sync_task_templet");
    private final static QName _SendControlInfoResponse_QNAME = new QName("http://ws.lnzn.com/", "send_control_infoResponse");
    private final static QName _SendRobotStateResponse_QNAME = new QName("http://ws.lnzn.com/", "send_robot_stateResponse");
    private final static QName _ChangePermissionState_QNAME = new QName("http://ws.lnzn.com/", "change_permission_state");
    private final static QName _SendMicroClimate_QNAME = new QName("http://ws.lnzn.com/", "send_micro_climate");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.lnzn.ws
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SendPatrolPathResponse }
     *
     */
    public SendPatrolPathResponse createSendPatrolPathResponse() {
        return new SendPatrolPathResponse();
    }

    /**
     * Create an instance of {@link SendControlInfo }
     *
     */
    public SendControlInfo createSendControlInfo() {
        return new SendControlInfo();
    }

    /**
     * Create an instance of {@link SendPatrolDataResponse }
     *
     */
    public SendPatrolDataResponse createSendPatrolDataResponse() {
        return new SendPatrolDataResponse();
    }

    /**
     * Create an instance of {@link CheckTimeResponse }
     *
     */
    public CheckTimeResponse createCheckTimeResponse() {
        return new CheckTimeResponse();
    }

    /**
     * Create an instance of {@link SendFaultValue }
     *
     */
    public SendFaultValue createSendFaultValue() {
        return new SendFaultValue();
    }

    /**
     * Create an instance of {@link SendTaskFinishInfoResponse }
     *
     */
    public SendTaskFinishInfoResponse createSendTaskFinishInfoResponse() {
        return new SendTaskFinishInfoResponse();
    }

    /**
     * Create an instance of {@link SendConfigInfo }
     *
     */
    public SendConfigInfo createSendConfigInfo() {
        return new SendConfigInfo();
    }

    /**
     * Create an instance of {@link SendFaultValueResponse }
     *
     */
    public SendFaultValueResponse createSendFaultValueResponse() {
        return new SendFaultValueResponse();
    }

    /**
     * Create an instance of {@link SendRobotMotionStateResponse }
     *
     */
    public SendRobotMotionStateResponse createSendRobotMotionStateResponse() {
        return new SendRobotMotionStateResponse();
    }

    /**
     * Create an instance of {@link GetPatrolPlanResponse }
     *
     */
    public GetPatrolPlanResponse createGetPatrolPlanResponse() {
        return new GetPatrolPlanResponse();
    }

    /**
     * Create an instance of {@link SendBodyAlarm }
     *
     */
    public SendBodyAlarm createSendBodyAlarm() {
        return new SendBodyAlarm();
    }

    /**
     * Create an instance of {@link SendTaskFinishInfo }
     *
     */
    public SendTaskFinishInfo createSendTaskFinishInfo() {
        return new SendTaskFinishInfo();
    }

    /**
     * Create an instance of {@link SendRobotMotionState }
     *
     */
    public SendRobotMotionState createSendRobotMotionState() {
        return new SendRobotMotionState();
    }

    /**
     * Create an instance of {@link GetEquipmentInfoResponse }
     *
     */
    public GetEquipmentInfoResponse createGetEquipmentInfoResponse() {
        return new GetEquipmentInfoResponse();
    }

    /**
     * Create an instance of {@link GetPatrolPlan }
     *
     */
    public GetPatrolPlan createGetPatrolPlan() {
        return new GetPatrolPlan();
    }

    /**
     * Create an instance of {@link SendMicroClimateResponse }
     *
     */
    public SendMicroClimateResponse createSendMicroClimateResponse() {
        return new SendMicroClimateResponse();
    }

    /**
     * Create an instance of {@link SendControlInfoResponse }
     *
     */
    public SendControlInfoResponse createSendControlInfoResponse() {
        return new SendControlInfoResponse();
    }

    /**
     * Create an instance of {@link SendRobotStateResponse }
     *
     */
    public SendRobotStateResponse createSendRobotStateResponse() {
        return new SendRobotStateResponse();
    }

    /**
     * Create an instance of {@link ChangePermissionState }
     *
     */
    public ChangePermissionState createChangePermissionState() {
        return new ChangePermissionState();
    }

    /**
     * Create an instance of {@link SendMicroClimate }
     *
     */
    public SendMicroClimate createSendMicroClimate() {
        return new SendMicroClimate();
    }

    /**
     * Create an instance of {@link SyncTaskTempletResponse }
     *
     */
    public SyncTaskTempletResponse createSyncTaskTempletResponse() {
        return new SyncTaskTempletResponse();
    }

    /**
     * Create an instance of {@link SendTaskExecuteInfoResponse }
     *
     */
    public SendTaskExecuteInfoResponse createSendTaskExecuteInfoResponse() {
        return new SendTaskExecuteInfoResponse();
    }

    /**
     * Create an instance of {@link SyncTaskTemplet }
     *
     */
    public SyncTaskTemplet createSyncTaskTemplet() {
        return new SyncTaskTemplet();
    }

    /**
     * Create an instance of {@link SendPatrolPoint }
     *
     */
    public SendPatrolPoint createSendPatrolPoint() {
        return new SendPatrolPoint();
    }

    /**
     * Create an instance of {@link SendConfigInfoResponse }
     *
     */
    public SendConfigInfoResponse createSendConfigInfoResponse() {
        return new SendConfigInfoResponse();
    }

    /**
     * Create an instance of {@link SendPatrolData }
     *
     */
    public SendPatrolData createSendPatrolData() {
        return new SendPatrolData();
    }

    /**
     * Create an instance of {@link SendPatrolPath }
     *
     */
    public SendPatrolPath createSendPatrolPath() {
        return new SendPatrolPath();
    }

    /**
     * Create an instance of {@link SendPatrolPointResponse }
     *
     */
    public SendPatrolPointResponse createSendPatrolPointResponse() {
        return new SendPatrolPointResponse();
    }

    /**
     * Create an instance of {@link ChangePermissionStateResponse }
     *
     */
    public ChangePermissionStateResponse createChangePermissionStateResponse() {
        return new ChangePermissionStateResponse();
    }

    /**
     * Create an instance of {@link SendBodyAlarmResponse }
     *
     */
    public SendBodyAlarmResponse createSendBodyAlarmResponse() {
        return new SendBodyAlarmResponse();
    }

    /**
     * Create an instance of {@link SendRobotState }
     *
     */
    public SendRobotState createSendRobotState() {
        return new SendRobotState();
    }

    /**
     * Create an instance of {@link CheckTime }
     *
     */
    public CheckTime createCheckTime() {
        return new CheckTime();
    }

    /**
     * Create an instance of {@link GetEquipmentInfo }
     *
     */
    public GetEquipmentInfo createGetEquipmentInfo() {
        return new GetEquipmentInfo();
    }

    /**
     * Create an instance of {@link SendTaskExecuteInfo }
     *
     */
    public SendTaskExecuteInfo createSendTaskExecuteInfo() {
        return new SendTaskExecuteInfo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendRobotMotionStateResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.lnzn.com/", name = "send_robot_motion_stateResponse")
    public JAXBElement<SendRobotMotionStateResponse> createSendRobotMotionStateResponse(SendRobotMotionStateResponse value) {
        return new JAXBElement<SendRobotMotionStateResponse>(_SendRobotMotionStateResponse_QNAME, SendRobotMotionStateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPatrolPlanResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.lnzn.com/", name = "get_patrol_planResponse")
    public JAXBElement<GetPatrolPlanResponse> createGetPatrolPlanResponse(GetPatrolPlanResponse value) {
        return new JAXBElement<GetPatrolPlanResponse>(_GetPatrolPlanResponse_QNAME, GetPatrolPlanResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendBodyAlarm }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.lnzn.com/", name = "send_body_alarm")
    public JAXBElement<SendBodyAlarm> createSendBodyAlarm(SendBodyAlarm value) {
        return new JAXBElement<SendBodyAlarm>(_SendBodyAlarm_QNAME, SendBodyAlarm.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendTaskFinishInfo }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.lnzn.com/", name = "send_task_finish_info")
    public JAXBElement<SendTaskFinishInfo> createSendTaskFinishInfo(SendTaskFinishInfo value) {
        return new JAXBElement<SendTaskFinishInfo>(_SendTaskFinishInfo_QNAME, SendTaskFinishInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendRobotMotionState }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.lnzn.com/", name = "send_robot_motion_state")
    public JAXBElement<SendRobotMotionState> createSendRobotMotionState(SendRobotMotionState value) {
        return new JAXBElement<SendRobotMotionState>(_SendRobotMotionState_QNAME, SendRobotMotionState.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEquipmentInfoResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.lnzn.com/", name = "get_equipment_infoResponse")
    public JAXBElement<GetEquipmentInfoResponse> createGetEquipmentInfoResponse(GetEquipmentInfoResponse value) {
        return new JAXBElement<GetEquipmentInfoResponse>(_GetEquipmentInfoResponse_QNAME, GetEquipmentInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPatrolPlan }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.lnzn.com/", name = "get_patrol_plan")
    public JAXBElement<GetPatrolPlan> createGetPatrolPlan(GetPatrolPlan value) {
        return new JAXBElement<GetPatrolPlan>(_GetPatrolPlan_QNAME, GetPatrolPlan.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendMicroClimateResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.lnzn.com/", name = "send_micro_climateResponse")
    public JAXBElement<SendMicroClimateResponse> createSendMicroClimateResponse(SendMicroClimateResponse value) {
        return new JAXBElement<SendMicroClimateResponse>(_SendMicroClimateResponse_QNAME, SendMicroClimateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendTaskFinishInfoResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.lnzn.com/", name = "send_task_finish_infoResponse")
    public JAXBElement<SendTaskFinishInfoResponse> createSendTaskFinishInfoResponse(SendTaskFinishInfoResponse value) {
        return new JAXBElement<SendTaskFinishInfoResponse>(_SendTaskFinishInfoResponse_QNAME, SendTaskFinishInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendConfigInfo }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.lnzn.com/", name = "send_config_info")
    public JAXBElement<SendConfigInfo> createSendConfigInfo(SendConfigInfo value) {
        return new JAXBElement<SendConfigInfo>(_SendConfigInfo_QNAME, SendConfigInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendFaultValueResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.lnzn.com/", name = "send_fault_valueResponse")
    public JAXBElement<SendFaultValueResponse> createSendFaultValueResponse(SendFaultValueResponse value) {
        return new JAXBElement<SendFaultValueResponse>(_SendFaultValueResponse_QNAME, SendFaultValueResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendPatrolDataResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.lnzn.com/", name = "send_patrol_dataResponse")
    public JAXBElement<SendPatrolDataResponse> createSendPatrolDataResponse(SendPatrolDataResponse value) {
        return new JAXBElement<SendPatrolDataResponse>(_SendPatrolDataResponse_QNAME, SendPatrolDataResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckTimeResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.lnzn.com/", name = "check_timeResponse")
    public JAXBElement<CheckTimeResponse> createCheckTimeResponse(CheckTimeResponse value) {
        return new JAXBElement<CheckTimeResponse>(_CheckTimeResponse_QNAME, CheckTimeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendFaultValue }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.lnzn.com/", name = "send_fault_value")
    public JAXBElement<SendFaultValue> createSendFaultValue(SendFaultValue value) {
        return new JAXBElement<SendFaultValue>(_SendFaultValue_QNAME, SendFaultValue.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendPatrolPathResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.lnzn.com/", name = "send_patrol_pathResponse")
    public JAXBElement<SendPatrolPathResponse> createSendPatrolPathResponse(SendPatrolPathResponse value) {
        return new JAXBElement<SendPatrolPathResponse>(_SendPatrolPathResponse_QNAME, SendPatrolPathResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendControlInfo }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.lnzn.com/", name = "send_control_info")
    public JAXBElement<SendControlInfo> createSendControlInfo(SendControlInfo value) {
        return new JAXBElement<SendControlInfo>(_SendControlInfo_QNAME, SendControlInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendRobotState }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.lnzn.com/", name = "send_robot_state")
    public JAXBElement<SendRobotState> createSendRobotState(SendRobotState value) {
        return new JAXBElement<SendRobotState>(_SendRobotState_QNAME, SendRobotState.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckTime }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.lnzn.com/", name = "check_time")
    public JAXBElement<CheckTime> createCheckTime(CheckTime value) {
        return new JAXBElement<CheckTime>(_CheckTime_QNAME, CheckTime.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEquipmentInfo }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.lnzn.com/", name = "get_equipment_info")
    public JAXBElement<GetEquipmentInfo> createGetEquipmentInfo(GetEquipmentInfo value) {
        return new JAXBElement<GetEquipmentInfo>(_GetEquipmentInfo_QNAME, GetEquipmentInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendTaskExecuteInfo }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.lnzn.com/", name = "send_task_execute_info")
    public JAXBElement<SendTaskExecuteInfo> createSendTaskExecuteInfo(SendTaskExecuteInfo value) {
        return new JAXBElement<SendTaskExecuteInfo>(_SendTaskExecuteInfo_QNAME, SendTaskExecuteInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendPatrolPoint }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.lnzn.com/", name = "send_patrol_point")
    public JAXBElement<SendPatrolPoint> createSendPatrolPoint(SendPatrolPoint value) {
        return new JAXBElement<SendPatrolPoint>(_SendPatrolPoint_QNAME, SendPatrolPoint.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendConfigInfoResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.lnzn.com/", name = "send_config_infoResponse")
    public JAXBElement<SendConfigInfoResponse> createSendConfigInfoResponse(SendConfigInfoResponse value) {
        return new JAXBElement<SendConfigInfoResponse>(_SendConfigInfoResponse_QNAME, SendConfigInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendPatrolData }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.lnzn.com/", name = "send_patrol_data")
    public JAXBElement<SendPatrolData> createSendPatrolData(SendPatrolData value) {
        return new JAXBElement<SendPatrolData>(_SendPatrolData_QNAME, SendPatrolData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendPatrolPath }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.lnzn.com/", name = "send_patrol_path")
    public JAXBElement<SendPatrolPath> createSendPatrolPath(SendPatrolPath value) {
        return new JAXBElement<SendPatrolPath>(_SendPatrolPath_QNAME, SendPatrolPath.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendPatrolPointResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.lnzn.com/", name = "send_patrol_pointResponse")
    public JAXBElement<SendPatrolPointResponse> createSendPatrolPointResponse(SendPatrolPointResponse value) {
        return new JAXBElement<SendPatrolPointResponse>(_SendPatrolPointResponse_QNAME, SendPatrolPointResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangePermissionStateResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.lnzn.com/", name = "change_permission_stateResponse")
    public JAXBElement<ChangePermissionStateResponse> createChangePermissionStateResponse(ChangePermissionStateResponse value) {
        return new JAXBElement<ChangePermissionStateResponse>(_ChangePermissionStateResponse_QNAME, ChangePermissionStateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendBodyAlarmResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.lnzn.com/", name = "send_body_alarmResponse")
    public JAXBElement<SendBodyAlarmResponse> createSendBodyAlarmResponse(SendBodyAlarmResponse value) {
        return new JAXBElement<SendBodyAlarmResponse>(_SendBodyAlarmResponse_QNAME, SendBodyAlarmResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SyncTaskTempletResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.lnzn.com/", name = "sync_task_templetResponse")
    public JAXBElement<SyncTaskTempletResponse> createSyncTaskTempletResponse(SyncTaskTempletResponse value) {
        return new JAXBElement<SyncTaskTempletResponse>(_SyncTaskTempletResponse_QNAME, SyncTaskTempletResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendTaskExecuteInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.lnzn.com/", name = "send_task_execute_infoResponse")
    public JAXBElement<SendTaskExecuteInfoResponse> createSendTaskExecuteInfoResponse(SendTaskExecuteInfoResponse value) {
        return new JAXBElement<SendTaskExecuteInfoResponse>(_SendTaskExecuteInfoResponse_QNAME, SendTaskExecuteInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SyncTaskTemplet }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.lnzn.com/", name = "sync_task_templet")
    public JAXBElement<SyncTaskTemplet> createSyncTaskTemplet(SyncTaskTemplet value) {
        return new JAXBElement<SyncTaskTemplet>(_SyncTaskTemplet_QNAME, SyncTaskTemplet.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendControlInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.lnzn.com/", name = "send_control_infoResponse")
    public JAXBElement<SendControlInfoResponse> createSendControlInfoResponse(SendControlInfoResponse value) {
        return new JAXBElement<SendControlInfoResponse>(_SendControlInfoResponse_QNAME, SendControlInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendRobotStateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.lnzn.com/", name = "send_robot_stateResponse")
    public JAXBElement<SendRobotStateResponse> createSendRobotStateResponse(SendRobotStateResponse value) {
        return new JAXBElement<SendRobotStateResponse>(_SendRobotStateResponse_QNAME, SendRobotStateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangePermissionState }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.lnzn.com/", name = "change_permission_state")
    public JAXBElement<ChangePermissionState> createChangePermissionState(ChangePermissionState value) {
        return new JAXBElement<ChangePermissionState>(_ChangePermissionState_QNAME, ChangePermissionState.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendMicroClimate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.lnzn.com/", name = "send_micro_climate")
    public JAXBElement<SendMicroClimate> createSendMicroClimate(SendMicroClimate value) {
        return new JAXBElement<SendMicroClimate>(_SendMicroClimate_QNAME, SendMicroClimate.class, null, value);
    }

}
