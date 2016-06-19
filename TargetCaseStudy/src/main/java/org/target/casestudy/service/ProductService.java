package org.target.casestudy.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.target.casestudy.connectionfactory.ConnectionFactory;
import org.target.casestudy.constant.ApplicationConstant;
import org.target.casestudy.exception.DataAccessException;
import org.target.casestudy.model.Identifier;
import org.target.casestudy.model.Items;
import org.target.casestudy.model.Product;
import org.target.casestudy.model.ProductCompositeResponse;
import org.target.casestudy.model.RequestAttribute;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * 
 * @author Asad Islam
 * Last Updated: Feb 7, 2016
 * TODO: Logger
 */
@SuppressWarnings("unchecked")
public class ProductService implements IProductDAO {
	
	SqlMapClient sqlMapClient = ConnectionFactory.getSqlMapFactory(); // Create db connection
	/**
	 * This method will get all the product from database.
	 * and return a list of products from the database.
	 */
	public List<Product> getAllProduct() {
		
		List<Product> products = new ArrayList<Product>();
		
		try {
			products = sqlMapClient.queryForList(ApplicationConstant.GET_ALL_PRODUCT);
		} catch (SQLException exception) {
			System.out.println(this.getClass().getPackage() + " " + this.getClass().getName() + ApplicationConstant.SPACE + exception);
			throw new DataAccessException(exception.toString());
		}
		return products;
	}
	/**
	 * This method will return a product match by provided product id.
	 */
	@Override
	public Product getProductByID(int productId) {
		
		Product product = null;
		
		try {
			product = (Product) sqlMapClient.queryForObject(ApplicationConstant.GET_PRODUCT_BY_ID, productId);
		} catch (SQLException exception) {
			System.out.println(this.getClass().getPackage() + ApplicationConstant.SPACE + this.getClass().getName() + ApplicationConstant.SPACE + exception);
			throw new DataAccessException(exception.toString());
		}
		return product;
	}

	/**
	 * This method return a list of product matched by category.	
	 */
	@Override
	public List<Product> getProductByCategory(String Category) {
		
		List<Product> products = new ArrayList<Product>();
		
		try {
			products = sqlMapClient.queryForList(ApplicationConstant.GET_PRODUCT_BY_CATEGORY, Category);
		} catch (SQLException exception) {
			System.out.println(this.getClass().getPackage() + ApplicationConstant.SPACE + this.getClass().getName() + ApplicationConstant.SPACE + exception);
			throw new DataAccessException(exception.toString());
		}
		return products;
	}
	/**
	 * This method will insert a new product into database (Product and product price table)
	 */
	@Override
	public boolean insertIntoProduct(Product product) {
		
		boolean inserted = true;
		
		try {
			sqlMapClient.startTransaction();
			sqlMapClient.insert(ApplicationConstant.INSERT_INTO_PRODUCT, product);
			sqlMapClient.insert(ApplicationConstant.INSERT_INTO_PRODUCT_PRICE, product);
			sqlMapClient.commitTransaction();
		} catch (SQLException sqlException) {
			inserted = false;
			throw new DataAccessException(sqlException);
		} finally {
			try {
				sqlMapClient.endTransaction();
			} catch (SQLException exception) {
				inserted = false;
			}
		}
		return inserted;
	}
	/**
	 * This will update a product matched by product id.
	 */
	@Override
	public boolean updateProductDetails(Product product) {
		boolean updated = true;
		
		try {
			sqlMapClient.startTransaction();
			sqlMapClient.update(ApplicationConstant.UPDATE_PRODUCT_TABLE, product);
			sqlMapClient.update(ApplicationConstant.UPDATE_PRODUCT_PRICE_TABLE, product);
			sqlMapClient.commitTransaction();
		} catch (SQLException sqlException) {
			updated = false;
			throw new DataAccessException(sqlException);
		} finally {
			try {
				sqlMapClient.endTransaction();
			} catch (SQLException exception) {
				updated = false;
			}
		}
		return updated;
	}
	
	public Product getProductDescr(Product product, ProductCompositeResponse pcr) {
		List<RequestAttribute> ras = pcr.getRequestAttribute();
		List<Items> items = pcr.getItems();
		String id_type = "";
		String productId = "";
		String source = "";
		for (RequestAttribute ra : ras) {
			if(ra.getName().equalsIgnoreCase(ApplicationConstant.ID_TYPE)) {
				id_type = ra.getValue();
			}
			if(ra.getName().equalsIgnoreCase(ApplicationConstant.ID_PRODUCT)) {
				productId = ra.getValue();
			}
		}
		for (Items item : items) {
			List<Identifier> id = item.getIdentifier();
			for (Identifier identifier : id) {
				if(identifier.getId_type().equalsIgnoreCase(id_type) && productId.equalsIgnoreCase(String.valueOf(product.getProductId()))) {
					source = identifier.getSource();
					product.setProductSource(source);
				} else {
					product.setProductSource(ApplicationConstant.NA);
				}
				
			}
			
			if((source.contains(ApplicationConstant.ONLINE) || source.contains(ApplicationConstant.STORE)) && (productId.equalsIgnoreCase(String.valueOf(product.getProductId())))) {
				product.setProductName(item.getGeneralDescriptions());
			} 
		}
		return product;
	}
}