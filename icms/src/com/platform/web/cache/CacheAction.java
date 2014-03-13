package com.platform.web.cache;

import java.util.List;

import com.opensymphony.xwork2.Action;
import com.platform.service.iface.cache.ICacheService;
import com.platform.util.Page;
import com.platform.vo.uniauth.auth.AuthVo;
import com.platform.web.base.BaseAction;

public class CacheAction extends BaseAction
{

    private static final long serialVersionUID = -3844693760642992193L;

    private List<AuthVo> rows;

    public String queryList()
    {
        Page p = this.initPage();
        this.rows = ((ICacheService) this.getService("cacheServiceImpl")).queryAuthList(p);
        this.responsePage(p);
        return Action.SUCCESS;
    }

    public List<AuthVo> getRows()
    {
        return rows;
    }

    public void setRows(List<AuthVo> rows)
    {
        this.rows = rows;
    }

}
