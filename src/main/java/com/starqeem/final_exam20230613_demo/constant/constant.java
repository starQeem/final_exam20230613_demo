package com.starqeem.final_exam20230613_demo.constant;

/**
 * @Date: 2023/6/13 21:26
 * @author: Qeem
 */
public class constant {
    public final static int PAGE_SIZE = 8;  //一页的数据条数
    public final static int PAGE_NUM = 1; //首页的默认页码
    public final static int SEARCH_PAGE_SIZE = 1000000; //模糊搜索的数据条数
    public final static int ZERO = 0;
    public final static String EMAIL_ADDRESS = "2572277647@qq.com";  //邮箱地址
    public final static String DEVICE_LIST_KEY = "device:admin:List:";  //设备列表的key
    public final static String DEVICE_LIST_DEL_KEY = "device:admin:List:*";
    public final static int DEVICE_LIST_CACHE_TTL = 5 * 60;  //设备列表缓存过期时间
}
