package com.platform.service.iface.cache;

import java.util.List;

import com.platform.util.Page;
import com.platform.vo.uniauth.auth.AuthVo;

public interface ICacheService
{

    void remove(String key);

    void removeAll();

    List<AuthVo> queryAuthList(Page p);

}
