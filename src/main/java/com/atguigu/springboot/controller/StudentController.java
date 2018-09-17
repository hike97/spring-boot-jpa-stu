package com.atguigu.springboot.controller;

import com.atguigu.springboot.BaseController;
import com.atguigu.springboot.entity.Student;
import com.atguigu.springboot.service.StudentService;
import org.springframework.data.jpa.domain.AbstractPersistable_;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller
@RequestMapping("/stu/info")
public class StudentController extends BaseController {
    @Resource
    private StudentService studentService;
    //查看
    @GetMapping("/students")
    public String getlist(){
        List<Student> students = studentService.getList();
        request.setAttribute("students",students);
        return "admin/student/list";
    }
    //添加页面
    @GetMapping("/student")
    public String toAddPage(){
        return "admin/student/add";
    }

    //添加
    @PostMapping("/student")
    public String addEmp(Student student){
        /*初始密码为123456*/
        student.setStatus(0);
        student.setPassWord("123456");
        //System.out.println("保存的学生信息："+student);
        studentService.save(student);
        return "redirect:/stu/info/students";
    }

    //修改页面
    @GetMapping("/student/{id}")
    public String toEditPage(@PathVariable("id") Integer id){
        Student student = studentService.getById( id );
        request.setAttribute("student",student);
        return "admin/student/add";
    }
    //修改操作
    @PutMapping("/student")
    public String updateEmployee(){
        //获取student
        String id = getParameter( "id" );
        Student student = studentService.getById( Integer.valueOf( id ) );
            //获取参数
        String userName = getParameter( "userName" );
        String realName = getParameter( "realName" );
        String gender = getParameter( "gender" );
        String age = getParameter( "age" );
        String role = getParameter( "role" );
        String passWord = getParameter( "passWord" );
        //修改参数
        student.setRealName( realName );
        student.setPassWord( passWord );
        student.setStatus( 0 );
        student.setAge( Integer.valueOf( age ) );
        student.setRole( role );
        student.setGender( Integer.valueOf( gender ) );
        System.out.println("修改的学生数据："+student);
        studentService.save(student);
        return "redirect:/stu/info/students";
    }

    //删除
    @DeleteMapping("/student/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        studentService.delete(id);
        return "redirect:/stu/info/students";
    }
}
