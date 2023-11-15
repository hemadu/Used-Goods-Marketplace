package cn.entity;

public class Ordertail {
private Integer id;
private Integer oid;
private Integer pid;
private Integer quantity;
private Goods goods;
private Orders orders;
public Ordertail(){
	
}
public Ordertail(Integer id, Integer oid, Integer pid, Integer quantity, Goods goods, Orders orders) {
	this.id = id;
	this.oid = oid;
	this.pid = pid;
	this.quantity = quantity;
	this.goods = goods;
	this.orders = orders;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Integer getOid() {
	return oid;
}
public void setOid(Integer oid) {
	this.oid = oid;
}
public Integer getPid() {
	return pid;
}
public void setPid(Integer pid) {
	this.pid = pid;
}
public Integer getQuantity() {
	return quantity;
}
public void setQuantity(Integer quantity) {
	this.quantity = quantity;
}
public Goods getGoods() {
	return goods;
}
public void setGoods(Goods goods) {
	this.goods = goods;
}
public Orders getOrders() {
	return orders;
}
public void setOrders(Orders orders) {
	this.orders = orders;
}


}
