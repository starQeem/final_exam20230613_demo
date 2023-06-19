package com.starqeem.final_exam20230613_demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * @Date: 2023/6/13 20:56
 * @author: Qeem
 */
@Data  //get、set方法
@NoArgsConstructor  //无参构造
@AllArgsConstructor  //有参构造
public class device{
    private Integer id;  // 主键id
    private String name;  // 设备名称
    private String label;  // 设备类型
    private Integer price;  // 设备价格
}
