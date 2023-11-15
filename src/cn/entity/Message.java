package cn.entity;

public class Message {
private Integer id;
private Integer uid;
private Integer pid;
private String content;
private String optime;
private Integer isdel;
private Users users;
private Goods goods;
public Message(){
	
}
public Message(Integer id, Integer uid, Integer pid, String content, String optime, Integer isdel, Users users,
		Goods goods) {
	this.id = id;
	this.uid = uid;
	this.pid = pid;
	this.content = content;
	this.optime = optime;
	this.isdel = isdel;
	this.users = users;
	this.goods = goods;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Integer getUid() {
	return uid;
}
public void setUid(Integer uid) {
	this.uid = uid;
}
public Integer getPid() {
	return pid;
}
public void setPid(Integer pid) {
	this.pid = pid;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getOptime() {
	return optime;
}
public void setOptime(String optime) {
	this.optime = optime;
}
public Integer getIsdel() {
	return isdel;
}
public void setIsdel(Integer isdel) {
	this.isdel = isdel;
}
public Users getUsers() {
	return users;
}
public void setUsers(Users users) {
	this.users = users;
}
public Goods getGoods() {
	return goods;
}
public void setGoods(Goods goods) {
	this.goods = goods;
}


}
