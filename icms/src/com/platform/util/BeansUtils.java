package com.platform.util;

import javax.transaction.SystemException;

import org.apache.commons.beanutils.BeanUtils;

public class BeansUtils
{
	public static final Object copyBeanProperties(Object expectedBean, Object sourceBean) throws SystemException
	{
		if (sourceBean != null)
		{
			try
			{
				BeanUtils.copyProperties(expectedBean, sourceBean);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		} else
		{
			expectedBean = null;
		}
		return expectedBean;
	}
}
