package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.model.Account;
import com.example.demo.repository.UserRepository;

@Controller
public class UserController {

	private final HttpSession session;
	private final Account account;
	private final UserRepository userRepository;

	public UserController(HttpSession session, Account account, UserRepository userRepository) {
		this.userRepository = userRepository;
		this.session = session;
		this.account = account;
	}

	// ログイン画面を表示
	@GetMapping({ "/", "/login", "/logout" })
	public String index() {
		// セッション情報を全てクリアする
		session.invalidate();
		return "login";
	}

	// ログインを実行
	@PostMapping("/login")
	public String login(
			@RequestParam String name,
			@RequestParam String password,
			Model model) {

		// どちらかが空の場合にエラーとする
		if (name.length() == 0) {
			model.addAttribute("message", "名前を入力してください");
			return "login";
		} else if (password.length() == 0) {
			model.addAttribute("message", "パスワードを入力してください");
			model.addAttribute("name", name);
			return "login";
		}

		List<User> userList = userRepository.findByNameAndPassword(name, password);
		if (userList == null || userList.size() == 0) {
			// 存在しなかった場合
			model.addAttribute("message", "名前とパスワードが一致しませんでした");
			model.addAttribute("name", name);
			return "login";
		}

		User user = userList.get(0);

		// アカウント情報にIDと名前をセット
		account.setUserId(user.getUserId());
		account.setName(user.getName());

		return "redirect:/tasks";
	}

	//	新規登録画面表示
	@GetMapping("/users/add")
	public String create() {
		return "addUser";
	}

	// 新規登録処理
	@PostMapping("/users/add")
	public String resister(
			@RequestParam(defaultValue = "") String name,
			@RequestParam(defaultValue = "") String password,
			@RequestParam(defaultValue = "") String passwordConfirm,
			Model model) {

		List<String> errorList = new ArrayList<>();
		// どちらかが空の場合にエラーとする
		if (name.length() == 0) {
			errorList.add("名前を入力してください");
			return "addUser";
		} else if (password.length() == 0) {
			errorList.add("パスワードを入力してください");
			//			model.addAttribute("name", name);
			return "addUser";
		}

		//		パスワード確認
		if (password.equals(passwordConfirm) == false) {
			errorList.add("パスワードが一致しませんでした");
			return "addUser";
		}

		// エラー発生時はフォームに戻す
		if (errorList.size() > 0) {
			model.addAttribute("errorList", errorList);
			model.addAttribute("name", name);
			return "accountForm";
		}

		User user = new User(name, password);

		userRepository.save(user);

		return "redirect:/login";
	}

}
