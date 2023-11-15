package cn.entity;

public class Goodstype {
private Integer id;
private String name;
private Integer isdel;

public Goodstype(){
	
}

public Goodstype(Integer id, String name, Integer isdel) {
	super();
	this.id = id;
	this.name = name;
	this.isdel = isdel;
}

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public Integer getIsdel() {
	return isdel;
}

public void setIsdel(Integer isdel) {
	this.isdel = isdel;
}

}
