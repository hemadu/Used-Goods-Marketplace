package cn.entity;

public class Carts {
private Integer id;
private Integer uid;
private Integer pid;
private Integer sid;
private Integer quantity;
private Double price;
private Goods goods;
private Users users;
private String type;
public Carts(){
	
}
public Carts(Integer id, Integer uid, Integer pid, Integer sid, Integer quantity, Double price, Goods goods,
		Users users, String type) {
	this.id = id;
	this.uid = uid;
	this.pid = pid;
	this.sid = sid;
	this.quantity = quantity;
	this.price = price;
	this.goods = goods;
	this.users = users;
	this.type = type;
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
public Integer getSid() {
	return sid;
}
public void setSid(Integer sid) {
	this.sid = sid;
}
public Integer getQuantity() {
	return quantity;
}
public void setQuantity(Integer quantity) {
	this.quantity = quantity;
}
public Double getPrice() {
	return price;
}
public void setPrice(Double price) {
	this.price = price;
}
public Goods getGoods() {
	return goods;
}
public void setGoods(Goods goods) {
	this.goods = goods;
}
public Users getUsers() {
	return users;
}
public void setUsers(Users users) {
	this.users = users;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}


}
