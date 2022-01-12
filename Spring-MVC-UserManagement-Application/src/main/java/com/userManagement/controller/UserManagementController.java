package com.userManagement.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.userManagement.dao.UserDataBaseConnectionDao;
import com.userManagement.model.UserModel;

@Controller
public class UserManagementController {

	private UserModel userModel;
	private UserDataBaseConnectionDao userDataBaseConnectionDao;

	@RequestMapping("/")
	public void welcomePage(HttpServletResponse response) throws IOException {
		response.sendRedirect("list");
	}

	@RequestMapping("/new")
	public String newClick() {
		return "/WEB-INF/view/new-user.jsp";
	}

	@RequestMapping("/insert")
	public void inserNewUser(@RequestParam("name") String name, @RequestParam("email") String email,
			@RequestParam("country") String country, HttpServletResponse response) throws IOException {
		userDataBaseConnectionDao = new UserDataBaseConnectionDao();
		userModel = new UserModel(name, email, country);
		try {
			userDataBaseConnectionDao.inserNewUser(userModel);
			response.sendRedirect("list");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping("/list")
	public String getAllUser(Model model) {
		userDataBaseConnectionDao = new UserDataBaseConnectionDao();
		try {
			List<UserModel> allUserList = userDataBaseConnectionDao.getAllUserList();
			model.addAttribute("listUser", allUserList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "/WEB-INF/view/user-list.jsp";
	}

	@RequestMapping("/delete")
	public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String stringId = request.getParameter("id");
		int id = Integer.parseInt(stringId);

		userDataBaseConnectionDao = new UserDataBaseConnectionDao();
		try {
			userDataBaseConnectionDao.deleteUser(id);

			response.sendRedirect("list");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping("/edit")
	public String getUserById(HttpServletRequest request, HttpServletResponse response, Model model) {
		String stringId = request.getParameter("id");
		int id = Integer.parseInt(stringId);

		userDataBaseConnectionDao = new UserDataBaseConnectionDao();
		try {
			UserModel existingUser = userDataBaseConnectionDao.getUserById(id);

			model.addAttribute("user", existingUser);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "/WEB-INF/view/new-user.jsp";
	}

	@RequestMapping("/update")
	public void updateUser(@RequestParam("name") String name, @RequestParam("email") String email,
			@RequestParam("country") String country, @RequestParam("id") String strId, HttpServletResponse response)
			throws IOException {
		userDataBaseConnectionDao = new UserDataBaseConnectionDao();
		int id = Integer.parseInt(strId);
		userModel = new UserModel(id, name, email, country);
		try {
			userDataBaseConnectionDao.updateUser(userModel);
			response.sendRedirect("list");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
