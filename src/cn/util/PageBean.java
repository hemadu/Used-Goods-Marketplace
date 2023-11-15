package cn.util;

import java.io.Serializable;

public class PageBean implements Serializable{
	private int pageNo=1;
	private int pageSize=3;
	private int bpageSize=9;
	private int totalCount;
	private int nextPage;
	private int prevPage;
	private int totalPage;
	private int btotalPage;
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public int getBpageSize() {
		return bpageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public void setBpageSize(int bpageSize) {
		this.bpageSize = bpageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getNextPage() {
		if(getPageNo()<getTotalPage()){
			return getPageNo()+1;
	}else{
		return getTotalPage();
	}
	}
	public int getNextPage1() {
		if(getPageNo()<getBtotalPage()){
			return getPageNo()+1;
	}else{
		return getBtotalPage();
	}
	}
	
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getPrevPage() {
		if(getPageNo()>1){
			return getPageNo()-1;
		}else{
			return 1;
		}
	}
	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}
	public int getTotalPage() {
		if(getTotalCount()%getPageSize()==0){
			return getTotalCount()/getPageSize();
		}else{
			return getTotalCount()/getPageSize()+1;
		}
	}
	public int getBtotalPage() {
		if(getTotalCount()%getBpageSize()==0){
			return getTotalCount()/getBpageSize();
		}else{
			return getTotalCount()/getBpageSize()+1;
		}
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public void setBtotalPage(int btotalPage) {
		this.btotalPage = btotalPage;
	}

	
}
