package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Task;
import com.example.demo.repository.TaskRepository;

@Controller
public class TaskController {

	//	private final HttpSession session;
	private final TaskRepository taskRepository;

	public TaskController(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
		//		this.session = session;
	}

	//タスク一覧画面を表示する(ソート機能まだ)	
	@GetMapping("/tasks")
	public String index(
			@RequestParam(defaultValue = "") Integer categoryId,
			Model model) {

		List<Task> taskList = taskRepository.findAll();

		model.addAttribute("tasks", taskList);

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
			//			@RequestParam(defaultValue = "") Integer taskId,
			@RequestParam(defaultValue = "") Integer categoryId,
			@RequestParam(defaultValue = "") String title,
			@RequestParam(defaultValue = "") LocalDate closingDate,
			@RequestParam(defaultValue = "") Integer progress,
			@RequestParam(defaultValue = "") String memo,
			@RequestParam(defaultValue = "") Integer time,
			@RequestParam(defaultValue = "") LocalDate date,
			Model model) {

		Task task = new Task(title, closingDate, progress, memo, time, date);

		taskRepository.save(task);

		return "redirect:/tasks";
	}

}
