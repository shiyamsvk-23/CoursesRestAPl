package com.cts.course.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.course.Exception.CoursesNotFoundException;
import com.cts.course.Service.CourseService;
import com.cts.course.model.Courses;

@RestController
@RequestMapping("/api")
public class CourseController {

	@Autowired
	CourseService courseService;

	//https://github.com/harshitsankhala/springStudentRepo.git
	@GetMapping("/demo")
	public String sayHello() {
		return "Hi, Welcome";
	}

	/**
	 * addDetails method used to add details of Course
	 * 
	 * @param Course
	 */
	@PostMapping("/addDetails")
	public void addDetails(@Valid @RequestBody Courses course) {
		courseService.addDetails(course);
	}

	/**
	 * coursesByCourseId used to return Courses detail based on courseId
	 * 
	 * @param courseId
	 * @return
	 * @throws CoursesNotFoundException
	 */
	@GetMapping("coursesByCourseId/{courseId}")
	public Courses coursesByCourseId(@PathVariable int courseId) throws CoursesNotFoundException {
		try {
			return courseService.coursesByCourseId(courseId);
		} catch (CoursesNotFoundException e) {
			// TODO Auto-generated catch block
			throw new CoursesNotFoundException("No Courses found with courseId = " + courseId);
		}
	}

	/**
	 * allCourses method used to return all Course details
	 * 
	 * @return
	 * @throws CoursesNotFoundException
	 */
	@GetMapping("showAllCourse")
	public List<Courses> viewAllCourses() throws CoursesNotFoundException {
		try {
			return courseService.viewAllCourses();
		} catch (CoursesNotFoundException e) {
			// TODO Auto-generated catch block
			throw new CoursesNotFoundException("No students");
		}
	}
	
	/**
	 * updateDetails method used to update details of Course 
	 * @param Course
	 * @throws CoursesNotFoundException
	 */
	@PutMapping("/update/{course}")
	public void updateDetails(@Valid @RequestBody Courses course) throws CoursesNotFoundException
	{
		try {
			courseService.updateDetail(course);
		} catch (CoursesNotFoundException e) {
			// TODO Auto-generated catch block
			throw new CoursesNotFoundException("Student not found.Please select exsisting student to update details.");
		}
	}
	
	/**
	 * deleteCourseById used to delete the record of Course
	 * @param courseId
	 * @throws CoursesNotFoundException
	 */
	@DeleteMapping("/delete/{courseId}")
	public void deleteCourseByCourseId(int courseId) throws CoursesNotFoundException
	{
		try {
			courseService.deletecourseByCourseId(courseId);
		}
		catch(EmptyResultDataAccessException e)
		{
			throw new CoursesNotFoundException("Student record does not found");
		}
	}
}