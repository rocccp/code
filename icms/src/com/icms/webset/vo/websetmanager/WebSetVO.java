package com.icms.webset.vo.websetmanager;

public class WebSetVO
{
    private int id;

    private String name;

    private String ip;

    private String effective;

    private String insertTime;

    private String insertUser;

    private String updateTime;

    private String updateUser;

    private String note;
    
    private String content;
    
    

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public String getEffective()
    {
        return effective;
    }

    public void setEffective(String effective)
    {
        this.effective = effective;
    }

    public String getInsertTime()
    {
        return insertTime;
    }

    public void setInsertTime(String insertTime)
    {
        this.insertTime = insertTime;
    }

    public String getInsertUser()
    {
        return insertUser;
    }

    public void setInsertUser(String insertUser)
    {
        this.insertUser = insertUser;
    }

    public String getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(String updateTime)
    {
        this.updateTime = updateTime;
    }

    public String getUpdateUser()
    {
        return updateUser;
    }

    public void setUpdateUser(String updateUser)
    {
        this.updateUser = updateUser;
    }

}
