package org.yynn.resm.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Title: PageListData
 * </p>
 * <p>
 * Description: 分页数据对象
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created 2007-5-24
 */
public class PageListData {
	private List dataArray = null; // 保存分页数据对象

	private int count = -1; // 记录总数

	private int pageSize = 20; // 每页显示记录数

	private int pageCount = 0; // 总页数

	private int pages = 1; // 当前页数

	public PageListData() {
		newDataArray();
	}

	public List getDataArray() {
		return dataArray;
	}

	public void setDataArray(List dataArray) {
		this.dataArray = dataArray;
	}

	public Object getData(int i) {
		return getDataArray().get(i);
	}

	public void newDataArray() {
		if (dataArray == null) {
			dataArray = new ArrayList();
		}
	}

	public void clearDataArray() {
		if (dataArray != null) {
			dataArray.clear();
		}
	}

	public void addData(Object data) {
		newDataArray();
		getDataArray().add(data);
	}

	public void addData(int i, Object data) {
		newDataArray();
		getDataArray().add(i, data);
	}

	/**
	 * 计算记录总数
	 * 
	 * @return int 总行数
	 */
	public int getCount() {
		return count;
	}

	/**
	 * 
	 * 设置记录总数，同时利用count和pageSize计算出pageCount。
	 * 
	 * @param count 记录总数
	 */
	public void setCount(int count) {
		if (pageSize != 0) {
			pageCount = count / pageSize;
			if (count % pageSize != 0) {
				pageCount++;
			}
		}
		this.count = count;
	}

	/**
	 * 计算总页数
	 * 
	 * @return int 总页面数
	 */
	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	/**
	 * 获取每页显示记录数
	 * 
	 * @return int 每页显示的记录数
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 
	 * 设置每页显示记录数
	 * 
	 * @param pageSize 每页显示记录数
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 获取当前页数
	 * 
	 * @return int 当前的页数
	 */
	public int getPages() {
		return pages;
	}

	/**
	 * 设置当前页数
	 * 
	 * @return int 当前的页数
	 */
	public void setPages(int pages) {
		this.pages = pages;
	}

}
