package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

	List<Task> findByCategoryId(Integer categoryId);

	List<Task> findByUserId(Integer userId);

	List<Task> findByUserIdAndCategoryIdAndDateOrderByClosingDateAsc(Integer userId, Integer categoryId,
			LocalDate date);

	List<Task> findByUserIdAndCategoryIdOrderByClosingDateAsc(Integer userId, Integer categoryId);

	List<Task> findByUserIdOrderByClosingDateAsc(Integer userId);

	List<Task> findByUserIdAndClosingDateLessThanEqualOrderByClosingDateAsc(Integer userId, LocalDate date);

}
