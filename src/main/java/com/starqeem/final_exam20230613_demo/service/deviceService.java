package com.starqeem.final_exam20230613_demo.service;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.starqeem.final_exam20230613_demo.mapper.deviceMapper;
import com.starqeem.final_exam20230613_demo.pojo.device;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.starqeem.final_exam20230613_demo.constant.constant.*;

/**
 * @Date: 2023/6/13 21:00
 * @author: Qeem
 */
@Service
public class deviceService {
    @Resource
    private deviceMapper deviceMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 获取设备列表
     *
     * @param name     名字
     * @param pageNum  页面num
     * @param pageSize 页面大小
     * @return {@link PageInfo}<{@link device}>
     */
    public PageInfo<device> getDeviceList(String name,Integer pageNum,Integer pageSize) {
        if (name == null){  //判断搜索框输入的设备名称是否为空
            //为空，将name设置为空字符串，这样在进行模糊查询的时候就能查询出所有列表信息
            name = "";
        }else {
            //不为空，则说明在使用搜索功能，此时将分页的一页的数据条数设置为一个较大的值，这样就只能查出一页的数据了，相当于不进行分页
            pageSize = SEARCH_PAGE_SIZE;
        }
        if (pageNum == null){//判断页码数是否为空
            //为空，赋值为1（第一页）
            pageNum = PAGE_NUM;
        }
        //从redis中查询列表信息
        String cacheDeviceList = stringRedisTemplate.opsForValue().get(DEVICE_LIST_KEY + pageNum);
        if (cacheDeviceList!=null && name.equals("")){//判断redis中查询出的列表信息是否为空且没有使用搜索功能
            //有列表,直接返回redis中查到的数据
            JSONObject jsonObj = JSONUtil.parseObj(cacheDeviceList); //将String类型的数据转换为JSONObject类型
            // 将jsonObj转换为PageInfo<device>类型的对象
            PageInfo<device> pageInfo = (PageInfo<device>) jsonObj.toBean(PageInfo.class);
            //返回分页信息
            return pageInfo;
        }
        //redis中没有列表信息或使用了搜索功能,直接查询数据库
        PageHelper.startPage(pageNum,pageSize);
        List<device> deviceLikeList = deviceMapper.getDeviceLikeList(name);
        PageInfo<device> pageInfo = new PageInfo<>(deviceLikeList);
        if (name.equals("")){ //判断是否使用了搜索功能
            //否，存入redis
            JSONObject jsonObj = JSONUtil.parseObj(pageInfo);
            // 将JSONObject类型的对象转换为PageInfo<device>类型的对象
            PageInfo<device> deviceListPageInfo = (PageInfo<device>) jsonObj.toBean(PageInfo.class);
            //将PageInfo<device>类型对象转换为String类型
            String cacheDevicePageList = JSONUtil.toJsonStr(deviceListPageInfo);
            //存入redis中
            stringRedisTemplate.opsForValue().set(DEVICE_LIST_KEY + pageNum, cacheDevicePageList, DEVICE_LIST_CACHE_TTL, TimeUnit.SECONDS);
        }
        //返回分页信息
        return pageInfo;
    }

    /**
     * 添加
     *
     * @param name  名字
     * @param label 标签
     * @param price 价格
     * @return int
     */
    public int add(String name,String label,Integer price) {
        int i = deviceMapper.insertDevice(name, label, price);
        if (i > ZERO){ //判断是否添加成功
            //成功，删除设备列表缓存
            Set<String> keysToDelete = stringRedisTemplate.keys(DEVICE_LIST_DEL_KEY);
            if (keysToDelete != null && !keysToDelete.isEmpty()) {
                stringRedisTemplate.delete(keysToDelete);
            }
        }
        return i;
    }

    /**
     * 更新设备通过id
     *
     * @param id    id
     * @param name  名字
     * @param label 标签
     * @param price 价格
     * @return int
     */
    public int updateDeviceById(Integer id, String name, String label, Integer price) {
        int i = deviceMapper.updateDeviceById(id, name, label, price);
        if (i > ZERO){//判断是否更新成功
            //成功，删除设备列表缓存
            Set<String> keysToDelete = stringRedisTemplate.keys(DEVICE_LIST_DEL_KEY);
            if (keysToDelete != null && !keysToDelete.isEmpty()) {
                stringRedisTemplate.delete(keysToDelete);//删除缓存
            }
        }
        return i;
    }

    /**
     * 删除设备通过id
     *
     * @param id id
     * @return int
     */
    public int deleteDeviceById(Integer id) {
        int i = deviceMapper.deleteDeviceById(id);
        if (i > ZERO){ //判断是否删除成功
            //成功，删除设备列表缓存
            Set<String> keysToDelete = stringRedisTemplate.keys(DEVICE_LIST_DEL_KEY);
            if (keysToDelete != null && !keysToDelete.isEmpty()) {
                stringRedisTemplate.delete(keysToDelete);//删除缓存
            }
        }
        return i;
    }
}
