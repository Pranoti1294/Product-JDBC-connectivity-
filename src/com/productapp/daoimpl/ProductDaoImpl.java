package com.productapp.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.productapp.dao.ProductDao;
import com.productapp.model.Product;
import com.productapp.utility.SqlUtil;

public class ProductDaoImpl implements ProductDao{

	@Override
	public int save(Product product) {
		int result = -1;
		
		try {
			SqlUtil.connectDB();
			String qryString = "INSERT INTO products VALUES ('"+product.getId()+"','"+product.getName()+"','"+product.getPrice()+"')";
			result = SqlUtil.insert(qryString);
			SqlUtil.close();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
		
		return result;
	}

	@Override
	public List<Product> getAll() {
		List<Product> productList = new ArrayList<Product>();
		try {
			SqlUtil.connectDB();
			
			String qryString = "SELECT * FROM products";
			ResultSet resultSet = SqlUtil.fetch(qryString);
			
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int price = resultSet.getInt("price");
				
				Product product = new Product(id, name, price);
				productList.add(product);
			}
			
			SqlUtil.close();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
		
		
		return productList;
	}

	@Override
	public Product getById(int id) {
		 
		Product product = null;
		try {
			SqlUtil.connectDB();
			
			String qryString = "SELECT * FROM products WHERE id="+id;
			ResultSet resultSet = SqlUtil.fetch(qryString);
			if(resultSet!=null) {
				if(resultSet.next()) {
					product = new Product();
					product.setId(resultSet.getInt("id"));
					product.setName(resultSet.getString("name"));
					product.setPrice(resultSet.getInt("price"));
				}					
			}
			
			SqlUtil.close();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
		
		return product;
	}

	@Override
	public int remove(int id) {
		int result = -1;
		try {
			SqlUtil.connectDB();
			String qryString = "DELETE FROM products WHERE id = "+id;
			result = SqlUtil.delete(qryString);
			
			SqlUtil.close();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
		
		return result;
	}

	@Override
	public int update(int id, Product product) {
		int result = -1;
		try {
			SqlUtil.connectDB();
			String qryString = "UPDATE products SET id='"+product.getId()+"', name='"+product.getName()+"',price='"+product.getPrice()+"' WHERE id ="+id;
			result = SqlUtil.update(qryString);
			SqlUtil.close();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
		return result;
	}

}
