package com.cn.uk.dto;

public class MainConfig {
	private long id;
	private int valid;
	private String objectid;
	private String name;
	private String memo;
	private String strValue;
	private int intValue;
	private long parent;
	private int cls;
	private int subclass;
	private int sibling;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getValid() {
		return valid;
	}

	public void setValid(int valid) {
		this.valid = valid;
	}

	public String getObjectid() {
		return objectid;
	}

	public void setObjectid(String objectid) {
		this.objectid = objectid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getStrValue() {
		return strValue;
	}

	public void setStrValue(String strValue) {
		this.strValue = strValue;
	}

	public int getIntValue() {
		return intValue;
	}

	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}

	public long getParent() {
		return parent;
	}

	public void setParent(long parent) {
		this.parent = parent;
	}

	public int getCls() {
		return cls;
	}

	public void setCls(int cls) {
		this.cls = cls;
	}

	public int getSubclass() {
		return subclass;
	}

	public void setSubclass(int subclass) {
		this.subclass = subclass;
	}

	public int getSibling() {
		return sibling;
	}

	public void setSibling(int sibling) {
		this.sibling = sibling;
	}
}
