package com.cts.course.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

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
	 * addMultiplecourses method used to add bulk course data
	 * 
	 * @param courseList
	 * @throws ConstraintViolationException
	 */

	public void addMultipleCourse(@Valid List<Courses> courseList) {
		// TODO Auto-generated method stub
		repository.saveAll(courseList);
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
	 * updateMultiplecourse method used to update bulk course data
	 * 
	 * @param courseList
	 * @throws ConstraintViolationException
	 */
	public void updateMultipleDetails(List<Courses> courseList) throws TransactionSystemException {
		repository.saveAll(courseList);
	}
	
	
	/**
	 * deletecourseBycourseId used to delete the record of course
	 * @param courseId
	 * @throws EmptyResultDataAccessException
	 * @throws CoursesNotFoundException 
	 */
	
	
	public String deletecourseByCourseId(int courseId) throws EmptyResultDataAccessException, CoursesNotFoundException
	{
		repository.deleteById(courseId);
		return "Success";
	}
	

	
	
	
	/**
	 * deleteMultipleDetails method used to delete course data
	 * 
	 * @param courseList
	 */
	public void deleteMultipleDetails(List<Courses> courseList) {
		// TODO Auto-generated method stub
		repository.deleteAll(courseList);
	}

	/**
	 * deleteAll method delete data of all courses
	 */
	public void deleteAll() {
		// TODO Auto-generated method stub
		repository.deleteAll();
	}

	public List<Courses> allCourse() throws CoursesNotFoundException {
		List<Courses> courseList = repository.findAll();
		if (courseList.isEmpty())
			throw new CoursesNotFoundException();

		return courseList;
	}
	

}
