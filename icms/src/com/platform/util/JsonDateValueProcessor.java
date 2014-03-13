package com.platform.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * Created on Feb 25, 2010
 * <p>Title:       东北神学院管理系统_[模块名称]_[功能名]/p>
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author         赵继平[rocccp@sohu.com]
 * @version        1.0
*/
public class JsonDateValueProcessor implements JsonValueProcessor
{
    private String format = "yyyy-MM-dd";    
    /**
     *  Created on Feb 25, 2010
     * <p>Discription:[方法功能中文描述]</p>
     * @author: 赵继平[rocccp@sohu.com]
     * @update:[日期YYYY-MM-DD] [更改人姓名]
     */
    public Object processArrayValue(Object value, JsonConfig jcf) {    
        String[] obj = {};    
        if(value instanceof Date[]){    
            SimpleDateFormat sdf = new SimpleDateFormat(format);    
            Date[] dates = (Date[])value;    
            obj = new String[dates.length];    
            for(int i=0;i<dates.length;i++){    
                obj[i] = sdf.format(dates[i]).trim();    
            }    
        }    
        return obj;    
    }   

    /**
     *  Created on Feb 25, 2010
     * <p>Discription:[方法功能中文描述]</p>
     * @author: 赵继平[rocccp@sohu.com]
     * @update:[日期YYYY-MM-DD] [更改人姓名]
     */
    public Object processObjectValue(String key, Object value, JsonConfig jcf) {    
        if(value instanceof Date){    
            String str = new SimpleDateFormat(format).format((Date)value);    
            return str.trim();    
        }    
        return value==null?null:value.toString();    
    } 

}
