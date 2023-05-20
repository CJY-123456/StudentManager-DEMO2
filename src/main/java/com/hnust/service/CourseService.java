package com.hnust.service;
import com.hnust.dto.paginationDTO;
import com.hnust.pojo.Course;
import com.hnust.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface CourseService {

    public paginationDTO queryAllCourse(Integer curPage, Integer pageSize);
    public int addCourse(Course course);
    public int updateCourse(Course course);
    public int deleteCourseById(int id);
    public Course queryCourseById(int id);
    public int queryCourseCountByName(@Param(value = "name")String name);
    public paginationDTO queryCourseByName(String name,Integer curPage, Integer pageSize);

}
