package com.simplilearn.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.simplilearn.model.Login;
import com.simplilearn.model.Product;
import com.simplilearn.model.User;

public class UserDAO {

	// jdbc template
	JdbcTemplate template;

	public JdbcTemplate getTemplate() {
		return template;
	}

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	// add products
	public int registerUser(User user) {
		String insertQuery = "insert into users values(?,?,?,?,?,?,?)";

		return template.update(insertQuery, new Object[] { user.getUsername(), user.getPassword(), user.getFirstname(),
				user.getLastname(), user.getEmail(), user.getAddress(), user.getPhone() });
	}

	public User loginUser(Login login) {
		String sql = "select * from users where username='" + login.getUsername() + "' and password='"
				+ login.getPassword() + "'";
		List<User> users = template.query(sql, new RowMapper<User>() {
			public User mapRow(ResultSet res, int row) throws SQLException {
				// map fields
				User user = new User();
				user.setUsername(res.getString("username"));
				user.setPassword(res.getString("password"));
				user.setFirstname(res.getString("firstname"));
				user.setLastname(res.getString("lastname"));
				user.setEmail(res.getString("email"));
				user.setAddress(res.getString("address"));
				user.setPhone(res.getInt("phone"));
				return user;
			}
		});
		
		return users.size()>0 ? users.get(0) : null;
	}
}
