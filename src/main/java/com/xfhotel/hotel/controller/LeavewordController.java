package com.xfhotel.hotel.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.xfhotel.hotel.entity.Leaveword;
import com.xfhotel.hotel.entity.Page;
import com.xfhotel.hotel.service.LeavewordService;
@Controller
@RequestMapping("/")
public class LeavewordController {

@Autowired
public LeavewordService LeavewordService;
public LeavewordController(){}
protected final transient Log log = LogFactory.getLog(LeavewordController.class);

@RequestMapping
//找到所有的记录并实现了分页
public String findAll(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) {   
        String pageNo = request.getParameter("pageNo");
        if (pageNo == null) {
            pageNo = "1";
        }
        Page page = LeavewordService.queryForPage(Integer.valueOf(pageNo), 5);
        request.setAttribute("page", page);
        List<Leaveword> list = page.getList();
        modelMap.put("list", list);
    return "";
}


//添加留言信息
@RequestMapping(params = "method=add")
public String add(HttpServletRequest request, ModelMap modelMap) throws Exception{
    return "*****";
}
//保存留言信息
@RequestMapping(params = "method=save")
public String save(HttpServletRequest request, ModelMap modelMap){
	Leaveword l = new Leaveword();
    String name = request.getParameter("name");
    String title=request.getParameter("title"); 
    String content=request.getParameter("content");
    String reply=null;
    l.setName(name);
    l.setTitle(title);
    l.setContent(content);
    l.setReply(reply);
    try{
    	LeavewordService.Servicesave(l);
        modelMap.put("addstate", "添加成功");
    }
    catch(Exception e){
        e.printStackTrace();
        log.error(e.getMessage());
        modelMap.put("addstate", "添加失败");
    }       
    return "****";
}
//删除留言信息
@RequestMapping(params = "method=del")
public String del(@RequestParam("id") String id) throws Exception{
	Leaveword l=new Leaveword();
    l.setId(Integer.valueOf(id));
    LeavewordService.Servicedelete(l);
    return "****";
}

//回复留言信息获得原来的留言信息并跳转页面
@RequestMapping(params = "method=edit")
public ModelAndView edit(@RequestParam("id") String id,HttpServletRequest request) throws Exception{
         ModelAndView modelAndView =  new ModelAndView();
         int idd=Integer.valueOf(id);
         Leaveword l=LeavewordService.QueryById(idd);   
         String reply =l.getReply();
         request.setAttribute("reply", reply);
         modelAndView.setViewName("/****");
         return modelAndView;
}
//回复留言信息确认提交
@RequestMapping(params = "method=editsubmit" )
public String editsubmit(HttpServletRequest request) throws Exception{
	 String reply=request.getParameter("reply");
     Leaveword l=new Leaveword();
     l.setReply(reply);
     LeavewordService.Serviceupdate(l);
     return "*****";
	}
}
