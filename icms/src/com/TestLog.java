package com;

import org.apache.log4j.Logger;


public class TestLog
{
    private static Logger logger = Logger.getLogger(TestLog.class);  

    public static void main(String[] args)
    {
        logger.fatal("xml");
    }

}
