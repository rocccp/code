package com.platform.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EhCacheManager
{
	private static CacheManager cacheManager = null;

	private EhCacheManager()
	{

	}

	/**
	 * Created on 2011/2/3
	 * <p>
	 * Discription:[初始化緩存]
	 * </p>
	 * 
	 * @author:赵继平
	 * @update:[日期YYYY-MM-DD] [更改人姓名]
	 * @return void .
	 */
	private static void initCacheManager()
	{
		if (cacheManager == null)
		{
			// 使用指定的配置文件路径创建缓存管理器
			cacheManager = CacheManager.create();
			// 获取配置的缓存Cache名字
			// String [] cacheNames = cacheManager.getCacheNames();
			// 会在当前classpath中去寻找ehcache.xml配置文件
			// cacheManager = CacheManager.getInstance();
			// new CacheManager();
			// 或者 CacheManager.getInstance();
			// 或者CacheManager.create();
		}
	}

	/**
	 * Created on 2011/2/3
	 * <p>
	 * Discription:[向緩存添加或更新鍵值對]
	 * </p>
	 * 
	 * @author:赵继平
	 * @update:[日期YYYY-MM-DD] [更改人姓名]
	 * @return void .
	 */
	public synchronized static void addCache(Object key, Object value)
	{
		initCacheManager();
		// 由缓存管理器中获得缓存,可以是配置在文件中的缓存也可以是代码中new出来的.
		Cache cache = cacheManager.getCache("systemConfigCache");
		// 数据放入缓存
		cache.put(new Element(key, value));

	}

	/**
	 * Created on 2011/2/3
	 * <p>
	 * Discription:[向緩存添加或更新鍵值對 指定緩存名稱]
	 * </p>
	 * 
	 * @author:赵继平
	 * @update:[日期YYYY-MM-DD] [更改人姓名]
	 * @return void .
	 */
	public synchronized void addCache(String cacheName, Object key, Object value)
	{
		initCacheManager();
		// 由缓存管理器中获得缓存,可以是配置在文件中的缓存也可以是代码中new出来的.
		Cache cache = cacheManager.getCache(cacheName);
		// 数据放入缓存
		cache.put(new Element(key, value));
		// 更新缓存数据 cache.put(new Element("key","value2"));

		// 取得缓存中的属性maxElementsInMemory,其他的值类似 int maxelementsInMemory =
		// cache.getMaxElementsInMemory();
		// String name = "codeCreateCache"; //缓存名字
		// int maxElementsInMemory = 1000; //缓存可以存储的总记录量
		// boolean overflowToDisk = false; //当缓存中的数据达到最大值时,是否把缓存数据写入磁盘
		// boolean eternal = true; //缓存是否永远不销毁
		// long timeToLiveSeconds = 60; //当缓存闲置指定时间,则自动销毁
		// long timeToIdleSeconds = 120; //当缓存创建之后到达时间自动销毁
		// 代码中创建缓存 Cache cache2 = new Cache(name, maxElementsInMemory,
		// overflowToDisk, eternal, timeToLiveSeconds,timeToIdleSeconds);
		// 添加缓存 cacheManager.addCache(cache2);
		// 移除缓存 cacheManager.removeCache("systemConfigCache");
		// 卸载缓存管理器 cacheManager.shutdown();

	}

	/**
	 * 
	 * Created on 2011/2/3
	 * <p>
	 * Discription:[獲取緩存的值]
	 * </p>
	 * 
	 * @author:赵继平
	 * @update:[日期YYYY-MM-DD] [更改人姓名]
	 * @return Object .
	 */
	public synchronized static Object getCacheValue(String key)
	{
		initCacheManager();
		// 由缓存管理器中获得缓存,可以是配置在文件中的缓存也可以是代码中new出来的.
		Cache cache = cacheManager.getCache("systemConfigCache");
		Element element = cache.get(key);
		// 获取缓存的值(序列画值) Serializable value = element.getValue();
		// 获取缓存的值(对象值)
		return element == null ? null : element.getObjectValue();

	}

	/**
	 * 
	 * Created on 2011/2/3
	 * <p>
	 * Discription:[獲取緩存的值 指定緩存的名稱]
	 * </p>
	 * 
	 * @author:赵继平
	 * @update:[日期YYYY-MM-DD] [更改人姓名]
	 * @return Object .
	 */
	public synchronized static Object getCacheValue(String cacheName, String key)
	{
		initCacheManager();
		// 由缓存管理器中获得缓存,可以是配置在文件中的缓存也可以是代码中new出来的.
		Cache cache = cacheManager.getCache(cacheName);
		Element element = cache.get(key);
		// 获取缓存的值(序列画值) Serializable value = element.getValue();
		// 获取缓存的值(对象值)
		return element == null ? null : element.getObjectValue();
	}

	/**
	 * 
	 * Created on 2011/2/3
	 * <p>
	 * Discription:[移除緩存 指定緩存的名稱]
	 * </p>
	 * 
	 * @author:赵继平
	 * @update:[日期YYYY-MM-DD] [更改人姓名]
	 * @return void .
	 */
	public static void removeCache(String cacheName, String key)
	{
		initCacheManager();
		// 由缓存管理器中获得缓存,可以是配置在文件中的缓存也可以是代码中new出来的.
		Cache cache = cacheManager.getCache(cacheName);
		// 移除缓存数据
		cache.remove(key);
	}

	/**
	 * 
	 * Created on 2011/2/3
	 * <p>
	 * Discription:[移除緩存]
	 * </p>
	 * 
	 * @author:赵继平
	 * @update:[日期YYYY-MM-DD] [更改人姓名]
	 * @return void .
	 */
	public static void removeCache(String key)
	{
		initCacheManager();
		// 由缓存管理器中获得缓存,可以是配置在文件中的缓存也可以是代码中new出来的.
		Cache cache = cacheManager.getCache("systemConfigCache");
		// 移除缓存数据
		cache.remove(key);
	}

	/**
	 * Created on 2011/2/3
	 * <p>
	 * Discription:[清空緩存]
	 * </p>
	 * 
	 * @author:赵继平
	 * @update:[日期YYYY-MM-DD] [更改人姓名]
	 * @return void .
	 */
	public static void removeAllCache()
	{
		initCacheManager();
		// 由缓存管理器中获得缓存,可以是配置在文件中的缓存也可以是代码中new出来的.
		Cache cache = cacheManager.getCache("systemConfigCache");
		cache.removeAll();
	}

	/**
	 * Created on 2011/2/3
	 * <p>
	 * Discription:[清空緩存]
	 * </p>
	 * 
	 * @author:赵继平
	 * @update:[日期YYYY-MM-DD] [更改人姓名]
	 * @return void .
	 */
	public static void removeAllCache(String cacheName)
	{
		initCacheManager();
		// 由缓存管理器中获得缓存,可以是配置在文件中的缓存也可以是代码中new出来的.
		Cache cache = cacheManager.getCache(cacheName);
		cache.removeAll();
	}
}
