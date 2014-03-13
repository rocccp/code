package com.platform.util;

import org.apache.commons.lang3.StringUtils;

public class PlatformStringUtils
{

	public static String changeNull(String str)
	{
		if (StringUtils.isNotEmpty(str))
		{
			return str.trim();

		} else
		{
			return "";
		}

	}

}
