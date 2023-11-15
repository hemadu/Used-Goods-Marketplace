package cn.entity;

public class Orders {
private Integer id;
private Integer sid;
private Integer uid;
private String no;
private Double totalprice;
private String optime;
private String status;
private Integer isdel;
public Orders(){
	
}
public Orders(Integer id, Integer sid, String no, Double totalprice, String optime, String status, Integer isdel) {
	this.id = id;
	this.sid = sid;
	this.no = no;
	this.totalprice = totalprice;
	this.optime = optime;
	this.status = status;
	this.isdel = isdel;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Integer getSid() {
	return sid;
}
public void setSid(Integer sid) {
	this.sid = sid;
}
public String getNo() {
	return no;
}
public void setNo(String no) {
	this.no = no;
}
public Double getTotalprice() {
	return totalprice;
}
public void setTotalprice(Double totalprice) {
	this.totalprice = totalprice;
}

public Integer getUid() {
	return uid;
}
public void setUid(Integer uid) {
	this.uid = uid;
}
public String getOptime() {
	return optime;
}
public void setOptime(String optime) {
	this.optime = optime;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public Integer getIsdel() {
	return isdel;
}
public void setIsdel(Integer isdel) {
	this.isdel = isdel;
}


}
