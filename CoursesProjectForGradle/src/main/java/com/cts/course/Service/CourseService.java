package com.cts.course.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.cts.course.Exception.CoursesNotFoundException;
import com.cts.course.Repository.CourseRepository;
import com.cts.course.model.Courses;

@Service
public class CourseService {

	@Autowired
	CourseRepository repository;

	/**
	 * addDetails method used to add details of Course
	 * 
	 * @param course
	 */
	public String addDetails(Courses course) {
	
		repository.save(course);
		return "success";
	
	}

	/**
	 * courseByRollNo used to return course detail based on roll number
	 * 
	 * @param rollNumber
	 * @return
	 * @throws courseNotFoundException
	 */

	public Courses coursesByCourseId(int courseId) throws CoursesNotFoundException {
	
		Optional<Courses> optional = repository.findById(courseId);
		if (!optional.isPresent())
			throw new CoursesNotFoundException();

		return optional.get();

	}

	/**
	 * allCourses method used to return all Courses details
	 * 
	 * @return Courses
	 * @throws CoursesNotFoundException
	 */
	public List<Courses> viewAllCourses() throws CoursesNotFoundException {
	
		List<Courses> courseList = repository.findAll();
		if (courseList.isEmpty())
			throw new CoursesNotFoundException();

		return courseList;
	}

	/**
	 * updateDetails method used to update details of Courses 
	 * @param course
	 * @throws CoursesNotFoundException
	 */
	
	public void updateDetail(Courses course) throws CoursesNotFoundException {
		
		Courses tempCourses = this.coursesByCourseId(course.getCourseId());
		tempCourses.setCourse_Name(course.getCourse_Name());
		tempCourses.setTeacher_Name(course.getTeacher_Name());
		tempCourses.setClass1(course.getClass1());
		tempCourses.setCourseId(course.getCourseId());
		tempCourses.setCreatedby(course.getCreatedby());
		tempCourses.setModifiedby(course.getModifiedby());
		tempCourses.setCreated_date(course.getCreated_date());
		tempCourses.setModified_date(course.getModified_date());
		repository.save(tempCourses);
			
	}

	/**
	 * deletecourseBycourseId used to delete the record of course
	 * @param courseId
	 * @throws EmptyResultDataAccessException
	 */
	
	public String deletecourseByCourseId(int courseId) throws EmptyResultDataAccessException
	{
		repository.deleteByCourseId(courseId);
		return "Success";
	}
}
