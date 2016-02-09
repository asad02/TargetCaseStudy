/**
 * 
 */
package org.target.casestudy.service;

import java.util.List;

import org.target.casestudy.model.Product;

/**
 * @author Asad Islam
 * Last Updated: Feb 7, 2016
 */
public interface IProductDAO {
	
	public List<Product> getAllProduct(); // Get a list of products
	
	public Product getProductByID(int productId); // get a product by id
	
	public List<Product> getProductByCategory(String Category); //get a list of product by category
	
	public boolean insertIntoProduct(Product product); // insert a product into product and productprice table
	public boolean updateProductDetails(Product product); // update a product based on the provided product id.
}
