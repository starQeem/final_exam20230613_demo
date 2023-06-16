package com.starqeem.final_exam20230613_demo.mapper;

import com.starqeem.final_exam20230613_demo.pojo.device;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Date: 2023/6/13 20:58
 * @author: Qeem
 */
@Mapper
public interface deviceMapper {
//    //根据id查询
    @Select("select * from device.device where id = #{id}")
    public device getDeviceById(@Param("id") int id);
//    //查询所有
//    @Select("select * from device.device")
//    public List<device> getDeviceList();
    //模糊查询
    @Select("select * from device.device where name LIKE CONCAT('%', #{name}, '%')")
    public List<device> getDeviceLikeList(@Param("name")String name);
    //新增设备
    @Insert("insert into device.device (name, label, price) VALUES (#{name}, #{label}, #{price})")
    int insertDevice(@Param("name") String name,@Param("label") String label,@Param("price") Integer price);
    //删除设备
    @Delete("delete from device.device where id = #{id}")
    int deleteDeviceById(@Param("id") Integer id);
    //根据id修改设备
    @Update("update device.device SET name = #{name}, label = #{label},price = #{price} WHERE id = #{id}")
    int updateDeviceById(@Param("id") Integer id,@Param("name") String name,@Param("label") String label,@Param("price") Integer price);
}
