package com.platform.util;

import java.util.ArrayList;
import java.util.List;

import com.platform.entity.uniauth.auth.Auth;

public class IrBarIconDataUtil
{

	public static List<Auth> IrBarIconDataList;
	static
	{
		IrBarIconDataList = new ArrayList<Auth>();
		Auth a1 = new Auth();
		a1.setId(0);
		a1.setAuthName("待办事宜");
		a1.setIcoUrl("pages/icon/todo.png");
		a1.setUrl("#");
		Auth a2 = new Auth();
		a2.setId(1);
		a2.setAuthName("修改密码");
		a2.setIcoUrl("pages/icon/lock.PNG");
		a2.setUrl("platform/passwordAction_index");
		IrBarIconDataList.add(a1);
		IrBarIconDataList.add(a2);
	}
}
