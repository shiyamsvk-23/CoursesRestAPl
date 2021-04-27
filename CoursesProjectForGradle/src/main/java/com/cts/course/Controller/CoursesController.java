package com.cts.course.Controller;

import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
@CrossOrigin(origins = "http://localhost:3000")
public class CoursesController {

	@Autowired
	CourseService courseService;

	/**
	 * all courses method used to return all courses details
	 * 
	 * @return
	 * @throws coursesNotFoundException
	 */
	@GetMapping("/showAllCourses")
	public List<Courses> allcourses() throws CoursesNotFoundException {
		try {
			return courseService.allCourse();
		} catch (CoursesNotFoundException e) {
			// TODO Auto-generated catch block
			throw new CoursesNotFoundException("No courses");
		}
	}

	/**
	 * addMultipleCourse method used to add bulk Course data
	 * 
	 * @param courseList
	 * @throws ConstraintViolationException
	 */
	@PostMapping("/addAllDetails")
	public void addMultiplecourses(@Valid @RequestBody List<Courses> courseList) throws ConstraintViolationException {
		try {
			courseService.addMultipleCourse(courseList);
		} catch (TransactionSystemException e) {
			if (e.getRootCause() instanceof ConstraintViolationException) {
				ConstraintViolationException constraintViolationException = (ConstraintViolationException) e
						.getRootCause();
				throw new ConstraintViolationException(constraintViolationException.getConstraintViolations());
			}
		}

	}

	/**
	 * updateMultipleCourses method used to update  bulk Course data
	 * 
	 * @param courseList
	 * @throws ConstraintViolationException
	 */
	@PutMapping("/updateAllDetails")
	public void updateMultiplecourses(@Valid @RequestBody List<Courses> courseList)
			throws ConstraintViolationException {
		try {
			courseService.updateMultipleDetails(courseList);
		} catch (TransactionSystemException e) {
			if (e.getRootCause() instanceof ConstraintViolationException) {
				ConstraintViolationException constraintViolationException = (ConstraintViolationException) e
						.getRootCause();
				throw new ConstraintViolationException(constraintViolationException.getConstraintViolations());
			}
		}
	}
	
	/**
	 * deleteMultipleDetails method used to delete courses data
	 * @param coursesList
	 */
	@DeleteMapping("/deleteAllDetails")
	public void deleteMultipleDetails(@RequestBody List<Courses> courseList)
	{
		courseService.deleteMultipleDetails(courseList);
	}
	
	/**
	 *  deleteAll method delete data of all courses
	 */
	@DeleteMapping("/deleteAll")
	public void deleteAll()
	{
		courseService.deleteAll();
	}

}