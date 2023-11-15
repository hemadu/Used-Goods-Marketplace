package cn.entity;

public class Goods {
private Integer id;
private Integer sid;//学生
private Users users;
private Integer cid;
private String name;
private Double price;
private Integer quantity;
private String pic;
private String content;
private Integer type;
private Goodstype goodstype;
private Integer isdel;
public Goods(){
	
}
public Goods(Integer id, Integer sid, Users users, Integer cid, String name, Double price, Integer quantity, String pic,
		String content, Integer type, Goodstype goodstype, Integer isdel) {
	super();
	this.id = id;
	this.sid = sid;
	this.users = users;
	this.cid = cid;
	this.name = name;
	this.price = price;
	this.quantity = quantity;
	this.pic = pic;
	this.content = content;
	this.type = type;
	this.goodstype = goodstype;
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
public Users getUsers() {
	return users;
}
public void setUsers(Users users) {
	this.users = users;
}
public Integer getCid() {
	return cid;
}
public void setCid(Integer cid) {
	this.cid = cid;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Double getPrice() {
	return price;
}
public void setPrice(Double price) {
	this.price = price;
}
public Integer getQuantity() {
	return quantity;
}
public void setQuantity(Integer quantity) {
	this.quantity = quantity;
}
public String getPic() {
	return pic;
}
public void setPic(String pic) {
	this.pic = pic;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public Integer getType() {
	return type;
}
public void setType(Integer type) {
	this.type = type;
}
public Goodstype getGoodstype() {
	return goodstype;
}
public void setGoodstype(Goodstype goodstype) {
	this.goodstype = goodstype;
}
public Integer getIsdel() {
	return isdel;
}
public void setIsdel(Integer isdel) {
	this.isdel = isdel;
}


}
