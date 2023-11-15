package cn.entity;

public class Reply {
	private Integer id;
	private Integer mid;
	private Integer uid;
	private String content;
	private String optime;
	private Users users;
	private Message message;
	public Reply(){
		
	}
	public Reply(Integer id, Integer mid, Integer uid, String content, String optime, Users users, Message message) {
		this.id = id;
		this.mid = mid;
		this.uid = uid;
		this.content = content;
		this.optime = optime;
		this.users = users;
		this.message = message;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
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
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	
	
}
