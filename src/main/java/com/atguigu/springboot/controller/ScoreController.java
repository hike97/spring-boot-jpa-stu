package com.atguigu.springboot.controller;

import com.atguigu.springboot.BaseController;

import com.atguigu.springboot.entity.Score;
import com.atguigu.springboot.entity.Student;
import com.atguigu.springboot.service.ScoreService;
import com.atguigu.springboot.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/score/info")
public class ScoreController extends BaseController {
    @Resource
    private ScoreService scoreService;
    @Resource
    private StudentService studentService;
    //查看
    @GetMapping("/scores/{stuId}")
    public String getlist(@PathVariable Integer stuId){
        //System.out.println(">>>>"+stuId);
        List<Score> scores = null;
        if (stuId==0){
            scores = scoreService.findAll();
            //System.out.println( "score详情："+scores );
            request.setAttribute("scores",scores);
            return "admin/score/list";
        }else {
            if (stuId==1){
                scores = scoreService.findAll();
            }else {
                scores = scoreService.getList(stuId);
            }
            request.setAttribute("scores",scores);
            return "admin/score/mylist";
        }

    }
    //添加页面
    @GetMapping("/score")
    public String toAddPage(){
        List<Student> list = studentService.getList();
        if (list.size()>0&&list !=null){
            //System.out.println(list);
            request.setAttribute("stuList",list);
        }
        return "admin/score/add";
    }

    //添加操作
    @PostMapping("/score")
    public String add(Score score){
        String stuId = getParameter( "stuId" );
        Student student = studentService.getById( Integer.valueOf( stuId ) );
        score.setStudent(student);
        score.setStatus(0);
        //System.out.println(score);
        /*添加操作前 先删除 相同年份数据*/
        Integer year = score.getYear();
        scoreService.deleteByYear(year,Integer.valueOf(stuId));
        scoreService.save(score);
        return "redirect:/score/info/scores/0";
    }

    //修改页面
    @GetMapping("/score/{id}")
    public String toEditPage(@PathVariable("id") Integer id){
        Score score = scoreService.selectById(id);
        request.setAttribute("score",score);
        List<Student> list = studentService.getList();
        if (list.size()>0&&list !=null){
            //System.out.println(list);
            request.setAttribute("stuList",list);
        }
        return "admin/score/add";
    }
    //修改操作
    @PutMapping("/score")
    public String updateEmployee(){
        String id = getParameter( "id" );
        Score score = scoreService.getById(Integer.valueOf( id ));
        //获取参数
        String score_ = getParameter( "score" );
        String year = getParameter( "year" );
        //执行
        System.out.println( "修改分数："+score_ );
        score.setScore( Double.valueOf( score_ ) );
        score.setYear( Integer.valueOf( year ) );
        score.setStatus(0);
        System.out.println("修改的学生数据："+score);
        scoreService.save(score);
        return "redirect:/score/info/scores/0";
    }

    //删除
    @DeleteMapping("/score/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        scoreService.delete(id);
        System.out.println( "hehehe" );
        return "redirect:/score/info/scores/0";
    }
    //判断学生是否有成绩记录录入
    @PostMapping("isAdd")
    @ResponseBody
    public boolean isAdd(Integer stuId){
        List<Score> scores = scoreService.getList(stuId);
        return scores.size() > 0;
    }
}
