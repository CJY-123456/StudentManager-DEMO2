package com.hnust.service;

import com.hnust.dto.paginationDTO;
import com.hnust.mapper.CourseMapper;
import com.hnust.pojo.Course;
import com.hnust.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    private CourseMapper courseMapper;

    public void setCourseMapper(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    @Override
    public int addCourse(Course course) {
        return courseMapper.addCourse(course);
    }

    @Override
    public int updateCourse(Course course) {
        return courseMapper.updateCourse(course);
    }

    @Override
    public int deleteCourseById(int id) {
        return courseMapper.deleteCourseById(id);
    }

    @Override
    public paginationDTO queryAllCourse(Integer curPage, Integer pageSize) {
        paginationDTO<Object> paginationDTO = new paginationDTO<>();
        Integer totalPage;
        Integer totalCount = courseMapper.queryCourseCount();
        if(totalCount % pageSize == 0){
            totalPage = totalCount / pageSize;
        }else {
            totalPage = totalCount / pageSize + 1;
        }
        if (curPage < 1){
            curPage = 1;
        }
        if (curPage > totalPage){
            curPage = totalPage;
        }

        paginationDTO.setPagination(totalPage,curPage);
        Integer offset = pageSize * (curPage - 1);

        List<Course> course = courseMapper.queryAllCourse(offset, pageSize);
        paginationDTO.setData(course);

        return paginationDTO;
    }

    @Override
    public Course queryCourseById(int id) {
        return courseMapper.queryCourseById(id);
    }

    @Override
    public int queryCourseCountByName(String name) {
        return courseMapper.queryCourseCountByName(name);
    }

    @Override
    public paginationDTO queryCourseByName(String name,Integer curPage, Integer pageSize) {
        paginationDTO<Object> paginationDTO = new paginationDTO<>();
        Integer totalPage;
        Integer totalCount = courseMapper.queryCourseCountByName(name);
        if (totalCount.equals(0)) {
            return paginationDTO;
        }
        if(totalCount % pageSize == 0){
            totalPage = totalCount / pageSize;
        }else {
            totalPage = totalCount / pageSize + 1;
        }
        if (curPage < 1){
            curPage = 1;
        }
        if (curPage > totalPage){
            curPage = totalPage;
        }

        paginationDTO.setPagination(totalPage,curPage);
        Integer offset = pageSize * (curPage - 1);

        List<Course> course = courseMapper.queryCourseByName(name,offset,pageSize);
        paginationDTO.setData(course);

        return paginationDTO;

    }

}
