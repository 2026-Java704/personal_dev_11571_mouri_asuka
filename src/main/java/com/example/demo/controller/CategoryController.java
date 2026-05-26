package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Category;
import com.example.demo.model.Account;
import com.example.demo.repository.CategoryRepository;

@Controller
public class CategoryController {

	private final CategoryRepository categoryRepository;
	private final Account account;

	public CategoryController(CategoryRepository categoryRepository, Account account) {
		this.categoryRepository = categoryRepository;
		this.account = account;

	}

	// 一覧画面の表示
	@GetMapping("/categories")
	public String index(Model model) {

		List<Category> categoryList = categoryRepository.findAll();
		model.addAttribute("categories", categoryList);

		model.addAttribute("account", this.account);

		return "categories";
	}

	//新規登録画面表示
	@GetMapping("/categories/add")
	public String create() {
		return "addCategory";
	}

	// 新規登録処理
	@PostMapping("/categories/add")
	public String store(
			@RequestParam(defaultValue = "") String categoryName) {

		Category category = new Category(categoryName);
		categoryRepository.save(category);

		return "redirect:/categories";
	}

	// 更新画面表示
	@GetMapping("/categories/{id}/edit")
	public String edit(@PathVariable("id") Integer categoryId, Model model) {

		Category category = categoryRepository.findById(categoryId).get();
		model.addAttribute("category", category);

		return "editCategory";
	}

	// 更新処理
	@PostMapping("/categories/{id}/edit")
	public String update(
			@PathVariable("id") Integer categoryId,
			@RequestParam(defaultValue = "") String categoryName) {

		Category category = categoryRepository.findById(categoryId).get();
		category.setCategoryName(categoryName);

		categoryRepository.save(category);

		return "redirect:/categories";
	}

	// 削除処理
	@PostMapping("/categories/{id}/delete")
	public String delete(@PathVariable("id") Integer categoryId) {

		categoryRepository.deleteById(categoryId);

		return "redirect:/categories";
	}

}
