package com.userManagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.userManagement.model.UserModel;

@Repository
public class UserDataBaseConnectionDao {
	
	private final String InsertNewUser = "insert into user (name, email, country) values(?, ?, ?)";
	private final String UpdateExistingUser = "update user set name = ?, email = ?, country = ? where id = ?";
	private final String deleteExistingUser = "delete from user where id = ?";
	private final String GetUserOnID = "select * from user where id = ?";
	private final String getAllUserList = "select * from user";
	
	
	public boolean inserNewUser(UserModel userModel) throws SQLException {
		ConnectionUtil con = new ConnectionUtil();
		boolean b = false;
		try {
			Connection connection = con.getConnection();
			
			PreparedStatement prepareStatement = connection.prepareStatement(InsertNewUser);
			prepareStatement.setString(1, userModel.getName());
			prepareStatement.setString(2, userModel.getEmail());
			prepareStatement.setString(3, userModel.getCountry());
			
			b = prepareStatement.executeUpdate() > 0;
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return b;
	}
	
	
	public List<UserModel> getAllUserList() throws SQLException{
		ConnectionUtil con = new ConnectionUtil();
		ArrayList<UserModel> userModelList = new ArrayList<UserModel>();
		try {
			Connection connection = con.getConnection();
			
			PreparedStatement prepareStatement = connection.prepareStatement(getAllUserList);
			ResultSet result = prepareStatement.executeQuery();
			UserModel userModel = null;
			while(result.next()) {
				int id = result.getInt("id");
				String name = result.getString("name");
				String email = result.getString("email");
				String country = result.getString("country");
				userModel = new UserModel(id, name, email, country);
				userModelList.add(userModel);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userModelList;
	}
	
	public boolean deleteUser(int id) throws SQLException {
		ConnectionUtil con = new ConnectionUtil();
		boolean b = false;
		try {
			Connection connection = con.getConnection();
			
			PreparedStatement prepareStatement = connection.prepareStatement(deleteExistingUser);
			prepareStatement.setInt(1, id);
			
			b = prepareStatement.executeUpdate() > 0;
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
	
	public UserModel getUserById(int id) throws SQLException {
		ConnectionUtil con = new ConnectionUtil();
		UserModel userModel = null;
		try {
			Connection connection = con.getConnection();
			
			PreparedStatement prepareStatement = connection.prepareStatement(GetUserOnID);
			prepareStatement.setInt(1, id);
			ResultSet result = prepareStatement.executeQuery();
			
			while(result.next()) {
				String name = result.getString("name");
				String email = result.getString("email");
				String country = result.getString("country");
				userModel = new UserModel(id, name, email, country);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userModel;
	}
	
	
	public boolean updateUser(UserModel userModel) throws SQLException {
		ConnectionUtil con = new ConnectionUtil();
		boolean b = false;
		try {
			Connection connection = con.getConnection();
			
			PreparedStatement prepareStatement = connection.prepareStatement(UpdateExistingUser);
			
			prepareStatement.setString(1, userModel.getName());
			prepareStatement.setString(2, userModel.getEmail());
			prepareStatement.setString(3, userModel.getCountry());
			prepareStatement.setInt(4, userModel.getId());
			
			b = prepareStatement.executeUpdate() > 0;
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return b;
	}
	
}
