package com.cfang.WeChat.dto;

public class BaseDto {

	/** 排序字段 */
	private String sort;

	/** 排序方向 */
	private String order;

	/** 页码 */
	private int page;

	/** 每页条数 */
	private int rows;

	/** 充值起始日期 */
	private String startDate;
	private String endDate;
	
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
