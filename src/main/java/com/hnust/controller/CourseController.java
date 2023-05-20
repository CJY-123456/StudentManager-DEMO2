package com.hnust.controller;

import com.hnust.pojo.Course;
import com.hnust.pojo.User;
import com.hnust.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.hnust.dto.paginationDTO;

import javax.servlet.http.HttpServletRequest;


@Controller
@ComponentScan("com.hnust.service")
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @RequestMapping("/allCourse")
    public String list(Model model,
                       @RequestParam(name="curPage",defaultValue = "1") Integer curPage,
                       @RequestParam(name = "pageSize",defaultValue = "5")Integer pageSize){
        paginationDTO paginationDTO = courseService.queryAllCourse(curPage,pageSize);
        model.addAttribute("list",paginationDTO);
        return "allCourse";

    }

    @RequestMapping("/toAddCourse")
    public String toAddPaper() {

        return "addCourse";
    }

    @RequestMapping("/addCourse")
    public String addPaper(Course course) {
        System.out.println(course);
        courseService.addCourse(course);
        return "redirect:/course/allCourse";
    }

    @RequestMapping("/toUpdateCourse")
    public String toUpdateCourse(Model model, int id) {
        Course course = courseService.queryCourseById(id);
        System.out.println(course);
        model.addAttribute("course",course );
        return "updateCourse";
    }

    @RequestMapping("/updateCourse")
    public String updateCourse(Model model, Course course) {
        System.out.println(course);
        courseService.updateCourse(course);
        Course course1 = courseService.queryCourseById(course.getId());
        model.addAttribute("course", course);
        return "redirect:/course/allCourse";
    }

    @RequestMapping("/del/{id}")
    public String deleteCourse(@PathVariable("id") int id) {
        courseService.deleteCourseById(id);
        return "redirect:/course/allCourse";
    }

    @RequestMapping("/queryCourse")
    public String queryCourseByName(@RequestParam(name = "name",required = false)String name,
                                  @RequestParam(name="curPage",defaultValue = "1") Integer curPage,
                                  @RequestParam(name = "pageSize",defaultValue = "5")Integer pageSize,
                                  Model model){
        int count = courseService.queryCourseCountByName(name);
        if (count == 0){
            model.addAttribute("error","未查到相关书籍");
            model.addAttribute("list",null);
            return "allCourse";
        }
        paginationDTO paginationDTO = courseService.queryCourseByName(name, curPage, pageSize);
        model.addAttribute("name",name);
        model.addAttribute("list",paginationDTO);
        return "allCourse";
    }

}
