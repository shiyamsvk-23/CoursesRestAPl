package com.cts.course.Controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cts.course.Exception.CoursesNotFoundException;
import com.cts.course.Service.CourseService;
import com.cts.course.model.Courses;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class CourseController {

	@Autowired
	CourseService courseService;

	/**
	 * addDetails method used to add details of Course
	 * 
	 * @param Course
	 */
	@PostMapping("/addDetails")
	public String addDetails(@Valid @RequestBody Courses course) {
		courseService.addDetails(course);
		return "success";
	}

	/**
	 * coursesByCourseId used to return Courses detail based on courseId
	 * 
	 * @param courseId
	 * @return
	 * @throws CoursesNotFoundException
	 */
	@GetMapping("coursesByCourseId/{courseId}")
	public Courses coursesByCourseId(@RequestBody int courseId) throws CoursesNotFoundException {
		try {
			return courseService.coursesByCourseId(courseId);
		} catch (CoursesNotFoundException e) {
			throw new CoursesNotFoundException("No Courses found with courseId = " + courseId);
		}
	}

	/**
	 * updateDetails method used to update details of Course
	 * 
	 * @param Course
	 * @throws CoursesNotFoundException
	 */
	@PutMapping("/update/{course}")
	public void updateDetails(@Valid @RequestBody Courses course) throws CoursesNotFoundException {
		try {
			courseService.updateDetail(course);
		} catch (CoursesNotFoundException e) {

			throw new CoursesNotFoundException("Course not found.Please select exsisting course to update details.");
		}
	}

	/**
	 * deleteCourseById used to delete the record of Course
	 * 
	 * @param courseId
	 * @throws CoursesNotFoundException
	 */
	@DeleteMapping("/deleteById")
	public void deleteCourseByCourseId(@RequestParam int courseId) throws CoursesNotFoundException {
		try {
			courseService.deletecourseByCourseId(courseId);
		} catch (EmptyResultDataAccessException e) {
			throw new CoursesNotFoundException("Course record does not found");
		}
	}
	
	
}
