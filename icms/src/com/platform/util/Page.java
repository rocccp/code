package com.platform.util;

public class Page
{
	private int page;

	private int rp;

	private int maxResult;

	private int startIndex;

	private int totolResultNum;

	private String sortname;

	private String sortorder;

	public String getSortname()
	{
		return sortname;
	}

	public void setSortname(String sortname)
	{
		this.sortname = sortname;
	}

	public String getSortorder()
	{
		return sortorder;
	}

	public void setSortorder(String sortorder)
	{
		this.sortorder = sortorder;
	}

	public int getTotolResultNum()
	{
		return totolResultNum;
	}

	public void setTotolResultNum(int totolResultNum)
	{
		this.totolResultNum = totolResultNum;
	}

	public int getStartIndex()
	{
		return startIndex;
	}

	public void setStartIndex(int startIndex)
	{
		this.startIndex = startIndex;
	}

	public int getPage()
	{
		return page;
	}

	public void setPage(int page)
	{
		this.page = page;
	}

	public int getRp()
	{
		return rp;
	}

	public void setRp(int rp)
	{
		this.rp = rp;
	}

	public int getMaxResult()
	{
		return maxResult;
	}

	public void setMaxResult(int maxResult)
	{
		this.maxResult = maxResult;
	}

}
