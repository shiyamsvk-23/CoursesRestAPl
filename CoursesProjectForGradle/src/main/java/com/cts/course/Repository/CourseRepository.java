package com.cts.course.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cts.course.model.Courses;

@Repository
public interface CourseRepository extends JpaRepository<Courses, Integer>{

	@Query("DELETE m FROM Courses m WHERE m.courseId=:courseId")
	String deleteByCourseId(int courseId);

}
