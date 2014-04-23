package com.icms.webset.web.websetmanager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.icms.webset.entity.websetmanager.WebSet;
import com.icms.webset.vo.websetmanager.WebSetVO;
import com.opensymphony.xwork2.Action;
import com.platform.entity.uniauth.user.User;
import com.platform.util.Page;
import com.platform.web.base.BaseAction;

@Component
@Scope("prototype")
@SuppressWarnings("unchecked")
public class WebsetManagerAction extends BaseAction
{

    private static final long serialVersionUID = 2857523592501720809L;

    private WebSet webSet;

    private List<WebSetVO> rows = new ArrayList<WebSetVO>();

    private String msg;
    
    public String index()
    {
        return "list";
    }

    public String queryList()
    {

        try
        {
            Page p = this.initPage();
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(WebSet.class);

            List<WebSet> websets = this.getService().queryListByPage(detachedCriteria, p);
            WebSetVO w = null;
            for (WebSet ws : websets)
            {
                String updateTime = ws.getUpdateTime() == null ? "" : ws.getUpdateTime() + "";
                String userName = ws.getInsertUser() == null ? "" : ws.getInsertUser().getName();
                w = new WebSetVO();
                w.setEffective(ws.getEffective().getValue());
                w.setId(ws.getId());
                w.setInsertTime(ws.getInsertTime() + "");
                w.setInsertUser(ws.getInsertUser().getName());
                w.setIp(ws.getIp());
                w.setName(ws.getName());
                w.setUpdateTime(updateTime);
                w.setUpdateUser(userName);
                w.setNote(ws.getNote());
                w.setContent(ws.getContent());
                rows.add(w);
            }
            this.responsePage(p);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return Action.SUCCESS;
    }

    public void save()
    {
        Date date = this.getSysDate();
        User u = this.getLogonUser();
        if (webSet.getId() == 0)
        {
            webSet.setInsertTime(date);
            webSet.setInsertUser(u);

        } else
        {
            WebSet w = null;
            w = (WebSet) this.getService().get(webSet.getId(), WebSet.class);
            w.setName(webSet.getName());
            w.setEffective(webSet.getEffective());
            w.setIp(webSet.getIp());
            w.setContent(webSet.getContent());
            w.setUrl(webSet.getUrl());
            w.setNote(webSet.getNote());
            webSet = w;
        }
        webSet.setUpdateTime(date);
        webSet.setUpdateUser(u);
        this.getService().saveOrUpdate(webSet);
    }

    public void delete()
    {
        this.getService().delete(webSet);
    }

    public WebSet getWebSet()
    {
        return webSet;
    }

    public void setWebSet(WebSet webSet)
    {
        this.webSet = webSet;
    }

    public List<WebSetVO> getRows()
    {
        return rows;
    }

    public void setRows(List<WebSetVO> rows)
    {
        this.rows = rows;
    }

    public Integer getPage()
    {
        return page;
    }

    public Integer getRp()
    {
        return rp;
    }

    public Integer getTotal()
    {
        return total;
    }

    @JSON
    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

}
