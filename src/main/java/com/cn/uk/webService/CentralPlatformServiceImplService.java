package com.cn.uk.webService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService(name="CentralPlatformServiceImplService", targetNamespace="http://ws.lnzn.com/")
@BindingType(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_MTOM_BINDING)
public  interface CentralPlatformServiceImplService
{
 @WebMethod(operationName = "send_patrol_data")
 @WebResult(name="res_conf")
 @RequestWrapper(localName = "send_patrol_data",targetNamespace = "http://ws.lnzn.com/",className = "com.lnzn.ws.SendPatrolData")
 @ResponseWrapper(localName = "send_patrol_data_Response",targetNamespace = "http://ws.lnzn.com/",className = "com.lnzn.ws.SendPatrolDataResponse")
 public  String SendPatrolData(@WebParam(name = "substation_id") String substation_id, @WebParam(name = "jrt_device_rl_list") String paramString2);

 @WebMethod(operationName = "send_fault_value")
 @WebResult(name="res_conf")
 @RequestWrapper(localName = "send_fault_value",targetNamespace = "http://ws.lnzn.com/",className = "com.lnzn.ws.SendFaultValue")
 @ResponseWrapper(localName = "send_fault_value_Response",targetNamespace = "http://ws.lnzn.com/",className = "com.lnzn.ws.SendFaultValueResponse")
 public  String SendFaultValue(@WebParam(name = "substation_id") String substation_id, @WebParam(name = "jalarm_data_rl_list") String paramString2);

 @WebMethod(operationName = "send_micro_climate")
 @WebResult(name="res_conf")
 @RequestWrapper(localName = "send_micro_climate",targetNamespace = "http://ws.lnzn.com/",className = "com.lnzn.ws.SendMicroClimate")
 @ResponseWrapper(localName = "send_micro_climate_Response",targetNamespace = "http://ws.lnzn.com/",className = "com.lnzn.ws.SendMicroClimateResponse")
 public  String SendMicroClimate(@WebParam(name = "substation_id") String substation_id, @WebParam(name = "jrt_station_rl_list") String paramString2);

 @WebMethod(operationName = "send_patrol_path")
 @WebResult(name="res_conf")
 @RequestWrapper(localName = "send_patrol_path",targetNamespace = "http://ws.lnzn.com/",className = "com.lnzn.ws.SendPatrolPath")
 @ResponseWrapper(localName = "send_patrol_path_Response",targetNamespace = "http://ws.lnzn.com/",className = "com.lnzn.ws.SendPatrolPathResponse")
 public  String SendPatrolPath(@WebParam(name = "substation_id") String substation_id, @WebParam(name = "robot_name") String robot_name, @WebParam(name = "robot_mac") String robot_mac, @WebParam(name = "task_name") String task_name, @WebParam(name = "task_id") String task_id, @WebParam(name = "jmap_list") String jmap_list);

 @WebMethod(operationName = "send_robot_state")
 @WebResult(name="res_conf")
 @RequestWrapper(localName = "send_robot_state",targetNamespace = "http://ws.lnzn.com/",className = "com.lnzn.ws.SendRobotState")
 @ResponseWrapper(localName = "send_robot_state_Response",targetNamespace = "http://ws.lnzn.com/",className = "com.lnzn.ws.SendRobotStateResponse")
 public  String SendRobotState(@WebParam(name = "substation_id") String substation_id, @WebParam(name = "jrobot_state_list") String jrobot_state_list);

 @WebMethod(operationName = "send_body_alarm")
 @WebResult(name="res_conf")
 @RequestWrapper(localName = "send_body_alarm",targetNamespace = "http://ws.lnzn.com/",className = "com.lnzn.ws.SendBodyAlarm")
 @ResponseWrapper(localName = "send_body_alarm_Response",targetNamespace = "http://ws.lnzn.com/",className = "com.lnzn.ws.SendBodyAlarmResponse")
 public  String SendBodyAlarm(@WebParam(name = "substation_id") String substation_id, @WebParam(name = "jevent_rl_list") String jevent_rl_list);

 @WebMethod(operationName = "send_control_info")
 @WebResult(name="res_conf")
 @RequestWrapper(localName = "send_control_info",targetNamespace = "http://ws.lnzn.com/",className = "com.lnzn.ws.SendControlInfo")
 @ResponseWrapper(localName = "send_control_info_Response",targetNamespace = "http://ws.lnzn.com/",className = "com.lnzn.ws.SendControlInfoResponse")
 public  String SendControlInfo(@WebParam(name = "substation_id") String substation_id, @WebParam(name = "jrt_data_rl_list") String jrt_data_rl_list);

 @WebMethod(operationName = "send_task_execute_info")
 @WebResult(name="res_conf")
 @RequestWrapper(localName = "send_task_execute_info",targetNamespace = "http://ws.lnzn.com/",className = "com.lnzn.ws.SendTaskExecuteInfo")
 @ResponseWrapper(localName = "send_task_execute_info_Response",targetNamespace = "http://ws.lnzn.com/",className = "com.lnzn.ws.SendTaskExecuteInfoResponse")
 public  String SendTaskExecuteInfo(@WebParam(name = "substation_id") String substation_id, @WebParam(name = "jtask_rl") String jtask_rl);

 @WebMethod(operationName = "send_config_info")
 @WebResult(name="jconn_re_info")
 @RequestWrapper(localName = "send_config_info",targetNamespace = "http://ws.lnzn.com/",className = "com.lnzn.ws.SendConfigInfo")
 @ResponseWrapper(localName = "send_config_info_Response",targetNamespace = "http://ws.lnzn.com/",className = "com.lnzn.ws.SendConfigInfoResponse")
 public  String SendConfigInfo(@WebParam(name = "substation_id") String substation_id, @WebParam(name = "jstation_info") String jstation_info);

 @WebMethod(operationName = "change_permission_state")
 @WebResult(name="res_conf")
 @RequestWrapper(localName = "change_permission_state",targetNamespace = "http://ws.lnzn.com/",className = "com.lnzn.ws.ChangePermissionState")
 @ResponseWrapper(localName = "change_permission_state_Response",targetNamespace = "http://ws.lnzn.com/",className = "com.lnzn.ws.ChangePermissionStateResponse")
 public  String ChangePermissionState(@WebParam(name = "substation_id") String substation_id, @WebParam(name = "jpermission_list") String jpermission_list);

 @WebMethod(operationName = "check_time")
 @WebResult(name="time")
 @RequestWrapper(localName = "check_time",targetNamespace = "http://ws.lnzn.com/",className = "com.lnzn.ws.CheckTime")
 @ResponseWrapper(localName = "check_time_Response",targetNamespace = "http://ws.lnzn.com/",className = "com.lnzn.ws.CheckTimeResponse")
 public  String CheckTime(@WebParam(name = "substation_id") String substation_id);

 @WebMethod(operationName = "get_equipment_info")
 @WebResult(name="equipment_infos")
 @RequestWrapper(localName = "get_equipment_info",targetNamespace = "http://ws.lnzn.com/",className = "com.lnzn.ws.GetEquipmentInfo")
 @ResponseWrapper(localName = "get_equipment_info_Response",targetNamespace = "http://ws.lnzn.com/",className = "com.lnzn.ws.GetEquipmentInfoResponse")
 public  String GetEquipmentInfo(@WebParam(name = "substation_id") String substation_id);

 @WebMethod(operationName = "get_patrol_plan")
 @WebResult(name="patrol_plans")
 @RequestWrapper(localName = "get_patrol_plan",targetNamespace = "http://ws.lnzn.com/",className = "com.lnzn.ws.GetPatrolPlan")
 @ResponseWrapper(localName = "get_patrol_plan_Response",targetNamespace = "http://ws.lnzn.com/",className = "com.lnzn.ws.GetPatrolPlanResponse")
 public  String GetPatrolPlan(@WebParam(name = "substation") String substation);

 @WebMethod(operationName = "send_patrol_point")
 @WebResult(name="res_conf")
 @RequestWrapper(localName = "send_patrol_point",targetNamespace = "http://ws.lnzn.com/",className = "com.lnzn.ws.SendPatrolPoint")
 @ResponseWrapper(localName = "send_patrol_point_Response",targetNamespace = "http://ws.lnzn.com/",className = "com.lnzn.ws.SendPatrolPointResponse")
 public  String SendPatrolPoint(@WebParam(name = "substation_id") String substation_id, @WebParam(name = "robot_name") String robot_name, @WebParam(name = "robot_mac") String robot_mac, @WebParam(name = "task_name") String task_name, @WebParam(name = "task_id") String task_id, @WebParam(name = "jmap") String jmap);

 @WebMethod(operationName = "send_robot_motion_state")
 @WebResult(name="res_conf")
 @RequestWrapper(localName = "send_robot_motion_state",targetNamespace = "http://ws.lnzn.com/",className = "com.lnzn.ws.SendRobotMotionState")
 @ResponseWrapper(localName = "send_robot_motion_state_Response",targetNamespace = "http://ws.lnzn.com/",className = "com.lnzn.ws.SendRobotMotionStateResponse")
 public  String SendRobotMotionState(@WebParam(name = "substation") String substation, @WebParam(name = "jrt_data_rl_list") String jrt_data_rl_list);

 @WebMethod(operationName = "send_task_finish_info")
 @WebResult(name="res_conf")
 @RequestWrapper(localName = "send_task_finish_info",targetNamespace = "http://ws.lnzn.com/",className = "com.lnzn.ws.SendTaskFinishInfo")
 @ResponseWrapper(localName = "send_task_finish_info_Response",targetNamespace = "http://ws.lnzn.com/",className = "com.lnzn.ws.SendTaskFinishInfoResponse")
 public  String SendTaskFinishInfo(@WebParam(name = "substation_id") String substation_id, @WebParam(name = "jtask_data_rl_list") String jtask_data_rl_list);

 @WebMethod(operationName = "sync_task_templet")
 @WebResult(name="res_conf")
 @RequestWrapper(localName = "sync_task_templet",targetNamespace = "http://ws.lnzn.com/",className = "com.lnzn.ws.SyncTaskTemplet")
 @ResponseWrapper(localName = "sync_task_templet_Response",targetNamespace = "http://ws.lnzn.com/",className = "com.lnzn.ws.SyncTaskTempletResponse")
 public  String SyncTaskTemplet(@WebParam(name = "substation_id") String substation_id, @WebParam(name = "jtask_link") String jtask_link);



}
