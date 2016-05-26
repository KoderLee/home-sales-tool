package org.yynn.resm.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Title: PageListData
 * </p>
 * <p>
 * Description: ��ҳ���ݶ���
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created 2007-5-24
 */
public class PageListData {
	private List dataArray = null; // �����ҳ���ݶ���

	private int count = -1; // ��¼����

	private int pageSize = 20; // ÿҳ��ʾ��¼��

	private int pageCount = 0; // ��ҳ��

	private int pages = 1; // ��ǰҳ��

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
	 * �����¼����
	 * 
	 * @return int ������
	 */
	public int getCount() {
		return count;
	}

	/**
	 * 
	 * ���ü�¼������ͬʱ����count��pageSize�����pageCount��
	 * 
	 * @param count ��¼����
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
	 * ������ҳ��
	 * 
	 * @return int ��ҳ����
	 */
	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	/**
	 * ��ȡÿҳ��ʾ��¼��
	 * 
	 * @return int ÿҳ��ʾ�ļ�¼��
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 
	 * ����ÿҳ��ʾ��¼��
	 * 
	 * @param pageSize ÿҳ��ʾ��¼��
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * ��ȡ��ǰҳ��
	 * 
	 * @return int ��ǰ��ҳ��
	 */
	public int getPages() {
		return pages;
	}

	/**
	 * ���õ�ǰҳ��
	 * 
	 * @return int ��ǰ��ҳ��
	 */
	public void setPages(int pages) {
		this.pages = pages;
	}

}
