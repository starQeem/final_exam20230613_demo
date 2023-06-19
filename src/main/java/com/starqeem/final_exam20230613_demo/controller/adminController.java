package com.starqeem.final_exam20230613_demo.controller;

import com.github.pagehelper.PageInfo;
import com.starqeem.final_exam20230613_demo.mapper.deviceMapper;
import com.starqeem.final_exam20230613_demo.pojo.device;
import com.starqeem.final_exam20230613_demo.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import static com.starqeem.final_exam20230613_demo.constant.constant.*;

/**
 * @Date: 2023/6/13 21:02
 * @author: Qeem
 */
@Controller
@RequestMapping("/admin")
public class adminController{
    @Resource
    private deviceService deviceService;
    @Resource
    private deviceMapper deviceMapper;
    @Resource
    private sendEmailMessage sendEmailMessage;
    private String MESSAGE = null;
    /*
    * 分页查询
    * */
    @GetMapping(value = {"/{pageNum}",""})
    public String index(Model model, @PathVariable(value = "pageNum",required = false)Integer pageNum,String name){
        if (pageNum == null){//判断页码数是否为空
            //为空，赋值为1（第一页）
            pageNum = PAGE_NUM;
        }
        PageInfo<device> deviceList = deviceService.getDeviceList(name,pageNum,PAGE_SIZE);
        model.addAttribute("message",MESSAGE);
        model.addAttribute("page",deviceList);
        return "admin/index";
    }
    /*
    * 跳转到新增设备页面
    * */
    @GetMapping("/add")
    public String add(){
        return "admin/add";
    }
    /*
    * 新增设备
    * */
    @PostMapping("/add")
    public String addInput(RedirectAttributes attributes, String name, String label, Integer price){
        int i = deviceService.add(name, label, price);
        if (i > ZERO){
            attributes.addFlashAttribute("message","新增成功!");
        }else {
            attributes.addFlashAttribute("message","新增失败!");
        }
        return "redirect:/admin";
    }
    /*
    * 跳转到修改页面
    * */
    @GetMapping("/update/{id}")
    public String update(@PathVariable("id")Integer id,Model model){
        device device = deviceMapper.getDeviceById(id);
        model.addAttribute("device",device);
        return "admin/update";
    }
    /*
    * 修改
    * */
    @PostMapping("/update")
    public String updateInput(RedirectAttributes attributes,Integer id,String name,String label,Integer price){
        int i = deviceService.updateDeviceById(id,name,label,price);
        if (i > ZERO){
            attributes.addFlashAttribute("message","修改成功!");
        }else {
            attributes.addFlashAttribute("message","修改失败!");
        }
        return "redirect:/admin";
    }
    /*
    * 删除设备
    * */
    @RequestMapping("/delete/{id}")
    public String deleteDeviceById(RedirectAttributes attributes,@PathVariable("id")Integer id){
        int i = deviceService.deleteDeviceById(id);
        if (i > ZERO){
            attributes.addFlashAttribute("message","删除成功!");
        }else {
            attributes.addFlashAttribute("message","删除失败!");
        }
        return "redirect:/admin";
    }
    /*
    * 跳转到邮件发送页面
    * */
    @GetMapping("/email")
    public String email(){
        return "admin/email";
    }
    /*
    * 邮件发送
    * */
    @PostMapping("/emailInput")
    public String emailInput(String title,String email,String content) throws MessagingException {
        sendEmailMessage.emailInput1(title,email,content);  //发送邮箱
        sendEmailMessage.emailInput2(email);  //发送通知邮箱
        MESSAGE = "发送成功!";
        return "redirect:/admin";
    }
}
