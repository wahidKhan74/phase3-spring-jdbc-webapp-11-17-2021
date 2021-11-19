package com.simplilearn.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.simplilearn.model.Product;

public class ProductDAO {
	
	// jdbc template 
	JdbcTemplate template;	
	
	public JdbcTemplate getTemplate() {
		return template;
	}
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	// list of products
	public List<Product> getProducts() {
		return template.query("select * from eproducts", new RowMapper<Product>() {
			public Product mapRow(ResultSet res, int row) throws SQLException {
				// map fields 
				Product product = new Product();
				product.setId(res.getInt(1));
				product.setName(res.getString(2));
				product.setPrice(res.getBigDecimal(3));
				product.setDateAdded(res.getDate(4));
				return product;
			}
		});
	}	
	
	// add products
	public int addProduct(Product product) {
		String insertQuery = "insert into eproducts(name,price) values('"+product.getName()+
				"','"+product.getPrice()+"')";
		return template.update(insertQuery);
	}
	
	// updates products
	public int updateProduct(Product product) {
		String updateQuery = "update eproducts  set name='"+product.getName()
		+"', price="+product.getPrice()+" where pid="+product.getId();	
		return template.update(updateQuery);
	}
	
	// delete products
	public int deleteProduct(Product product) {
		String updateQuery = "delete from eproducts where pid="+product.getId();		
		return template.update(updateQuery);
	}
}
