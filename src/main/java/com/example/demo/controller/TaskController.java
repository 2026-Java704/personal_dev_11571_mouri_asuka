package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Category;
import com.example.demo.entity.Task;
import com.example.demo.model.Account;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.TaskRepository;

@Controller
public class TaskController {

	private final TaskRepository taskRepository;
	private final CategoryRepository categoryRepository;
	private final Account account;

	public TaskController(TaskRepository taskRepository, CategoryRepository categoryRepository, Account account) {
		this.taskRepository = taskRepository;
		this.categoryRepository = categoryRepository;
		this.account = account;
	}

	//タスク一覧画面を表示する
	@GetMapping("/tasks")
	public String index(
			@RequestParam(defaultValue = "") Integer categoryId,
			@RequestParam(defaultValue = "") String sort,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
			Model model) {

		// 全カテゴリー一覧を取得
		List<Category> categoryList = categoryRepository.findAll();
		model.addAttribute("categories", categoryList);

		// ログイン中のユーザーIDを取得
		Integer userId = account.getUserId();

		// タスク一覧情報の取得
		List<Task> taskList = null;

		//		

		if (categoryId == null) {
			// カテゴリーも日付も何も選ばれていない
			if (date == null) {
				// ログインユーザーのタスクをすべて持ってくる（期限順）
				taskList = taskRepository.findByUserIdOrderByClosingDateAsc(userId);
			} else {
				// 日付の指定だけあるとき
				taskList = taskRepository.findByUserIdAndDateOrderByClosingDateAsc(userId, date);
			}
		} else {
			// カテゴリーが選ばれているとき
			if (date != null) {
				// ユーザーID ＋ カテゴリー ＋ 日付 ＋ 期限順(できてないかも)
				taskList = taskRepository.findByUserIdAndCategoryIdAndDateOrderByClosingDateAsc(userId, categoryId,
						date);
			} else {
				// ユーザーID ＋ カテゴリー ＋ 期限順
				taskList = taskRepository.findByUserIdAndCategoryIdOrderByClosingDateAsc(userId, categoryId);
			}
		}

		model.addAttribute("tasks", taskList);
		model.addAttribute("categoryId", categoryId);
		model.addAttribute("sort", sort);
		model.addAttribute("selectedDate", date);
		model.addAttribute("today", LocalDate.now());

		return "tasks";
	}

	//	タスクを追加する
	//	新規作成フォーム表示
	@GetMapping("/tasks/create")
	public String create() {
		return "addTask";
	}

	//	新規作成処理
	@PostMapping("/tasks/create")
	public String register(
			@RequestParam(defaultValue = "") Integer categoryId,
			@RequestParam(defaultValue = "") String title,
			@RequestParam(defaultValue = "") LocalDate closingDate,
			@RequestParam(defaultValue = "") Integer progress,
			@RequestParam(defaultValue = "") String memo,
			@RequestParam(defaultValue = "") Integer time,
			@RequestParam(defaultValue = "") LocalDate date,
			Model model) {

		Task task = new Task(categoryId, title, closingDate, progress, memo, time, date);

		task.setUserId(account.getUserId());
		taskRepository.save(task);

		return "redirect:/tasks";
	}

	//	登録内容を変更する
	//	タスク内容変更フォーム表示
	@GetMapping("/tasks/{id}/edit")
	public String edit(
			@PathVariable("id") Integer taskId,
			@RequestParam(defaultValue = "") Integer categoryId,
			@RequestParam(defaultValue = "") String title,
			@RequestParam(defaultValue = "") LocalDate closingDate,
			@RequestParam(defaultValue = "") Integer progress,
			@RequestParam(defaultValue = "") String memo,
			@RequestParam(defaultValue = "") Integer time,
			@RequestParam(defaultValue = "") LocalDate date,
			Model model) {

		//ID（主キー）で検索
		Task task = taskRepository.findById(taskId).get();
		model.addAttribute("task", task);
		return "editTask";
	}

	//	変更処理
	@PostMapping("/tasks/{id}/edit")
	public String update(
			@PathVariable("id") Integer taskId,
			@RequestParam(defaultValue = "") Integer categoryId,
			@RequestParam(defaultValue = "") String title,
			@RequestParam(defaultValue = "") LocalDate closingDate,
			@RequestParam(defaultValue = "") Integer progress,
			@RequestParam(defaultValue = "") String memo,
			@RequestParam(defaultValue = "") Integer time,
			@RequestParam(defaultValue = "") LocalDate date) {

		//	ID（主キー）で検索
		Task task = taskRepository.findById(taskId).get();
		task.setCategoryId(categoryId);
		task.setTitle(title);
		task.setClosingDate(closingDate);
		task.setProgress(progress);
		task.setMemo(memo);
		task.setTime(time);
		task.setDate(date);
		task.setUserId(account.getUserId());

		//	反映（UPDATE）
		taskRepository.save(task);

		return "redirect:/tasks";
	}

	//	タスク削除処理
	@PostMapping("/tasks/{id}/delete")
	public String deleteTask(@PathVariable("id") Integer taskId) {

		taskRepository.deleteById(taskId);

		return "redirect:/tasks";
	}

}
