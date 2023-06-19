package com.starqeem.final_exam20230613_demo.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.starqeem.final_exam20230613_demo.mapper.deviceMapper;
import com.starqeem.final_exam20230613_demo.pojo.device;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.starqeem.final_exam20230613_demo.constant.constant.PAGE_SIZE;


/**
 * @Date: 2023/6/13 21:00
 * @author: Qeem
 */
@Service
public class deviceService {
    @Resource
    private deviceMapper deviceMapper;

    /**
     * 获取设备列表
     *
     * @param pageNum  页面num
     * @return {@link PageInfo}<{@link device}>
     */
    @Cacheable(value = "device:admin:List",key = "#pageNum") //添加缓存
    public PageInfo<device> getDeviceList(Integer pageNum) {
        //调用分页助手，传入当前页码和一页的数据条数
        PageHelper.startPage(pageNum,PAGE_SIZE);
        //查询数据库
        List<device> deviceLikeList = deviceMapper.getDeviceList();
        PageInfo<device> pageInfo = new PageInfo<>(deviceLikeList);
        //返回分页信息
        return pageInfo;
    }

    /**
     * 搜索列表
     *
     * @param name 名字
     * @return {@link List}<{@link device}>
     */
    public List<device> getSearchList(String name) {
        return deviceMapper.getSearchList(name);
    }

    /**
     * 添加
     *
     * @param name  名字
     * @param label 标签
     * @param price 价格
     * @return int
     */
    @CacheEvict(value = "device:admin:List", allEntries = true)  //删除缓存（allEntries设置为true是指删除device:admin:List中的所有条目）
    public int add(String name,String label,Integer price) {
        return deviceMapper.insertDevice(name, label, price);
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
    @CacheEvict(value = "device:admin:List", allEntries = true)//删除缓存（allEntries设置为true是指删除device:admin:List中的所有条目）
    public int updateDeviceById(Integer id, String name, String label, Integer price) {
        return deviceMapper.updateDeviceById(id, name, label, price);
    }

    /**
     * 删除设备通过id
     *
     * @param id id
     * @return int
     */
    @CacheEvict(value = "device:admin:List", allEntries = true)//删除缓存（allEntries设置为true是指删除device:admin:List中的所有条目）
    public int deleteDeviceById(Integer id) {
        return deviceMapper.deleteDeviceById(id);
    }


}
