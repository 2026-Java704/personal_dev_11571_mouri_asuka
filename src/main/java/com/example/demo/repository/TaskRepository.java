package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

	// カテゴリあり ＆ 期限日順
	List<Task> findByCategoryIdOrderByClosingDateAsc(Integer categoryId);

	// カテゴリなし ＆ 期限日順
	List<Task> findAllByOrderByClosingDateAsc();

	// 日付のみで検索
	List<Task> findByDate(LocalDate date);

	// 日付 ＋ カテゴリーで検索
	List<Task> findByDateAndCategoryId(LocalDate date, Integer categoryId);

	// 日付 ＋ 期限順
	List<Task> findByDateOrderByClosingDateAsc(LocalDate date);

	// 日付 ＋ カテゴリー ＋ 期限順
	List<Task> findByDateAndCategoryIdOrderByClosingDateAsc(LocalDate date, Integer categoryId);

	List<Task> findByCategoryId(Integer categoryId);

	List<Task> findByUserId(Integer userId);

	List<Task> findByUserIdAndCategoryIdAndDateOrderByClosingDateAsc(Integer userId, Integer categoryId,
			LocalDate date);

	List<Task> findByUserIdAndCategoryIdOrderByClosingDateAsc(Integer userId, Integer categoryId);

	List<Task> findByUserIdOrderByClosingDateAsc(Integer userId);

	List<Task> findByUserIdAndDateOrderByClosingDateAsc(Integer userId, LocalDate date);

}
