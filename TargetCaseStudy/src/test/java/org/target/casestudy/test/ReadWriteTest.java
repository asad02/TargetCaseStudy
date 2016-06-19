package org.target.casestudy.test;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;
import org.target.casestudy.model.Product;
import org.target.casestudy.service.ProductService;

public class ReadWriteTest extends TestCase{
	ProductService productService = new ProductService();
	Product product = null;
	@Test
	public void readAllProducts() {
		List<Product> products = productService.getAllProduct();

		assertNotNull("Failure - Product is null", products);
		assertTrue("Failure - More than 3 products are expected",
				products.size() == 4);
		assertEquals(Double.MIN_VALUE, products.get(0).getPrice());
	}

	@Test
	public void readProductById() {
		product = productService.getProductByID(5555);

		assertNotNull("Failure - Product is null", product);
		assertTrue("Failure - Product id 5555 expected",
				product.getProductId() == 5555);
		assertTrue("Failure - Product name is not correct",
				product.getProductName() == "Strolle");
	}

	@Test
	public void writeIntoProductsTable() throws SQLException {
		product = new Product(5561, "Baby Shampoo", "Baby", new Date(),
				12.20, "ASD123", "", "");
		boolean inserted = productService.insertIntoProduct(product);

		assertTrue("Failure - Product was not inserted", inserted == true);

		product = productService.getProductByID(5561);

		assertNotNull("Failure - Product is null", product);
		assertTrue("Failure - Product name is not correct",
				product.getProductName() == "Baby Shampoo");
	}
	
	@Test
    public void validProduct() {
        product = productService.getProductByID(5555);
        assertTrue("Failure - Product id does not match: ", product.productId == 12323);

    }
	
	@Test
    public void getNullResponseWithCategory() {
        String category = null;
        List<Product> products = productService.getProductByCategory(category);
        assertNull("Failure - Invalid product:", products.get(0));
    }

    @Test
    public void invalidProduct() {
    	//Test invalid product
        assertTrue("Failure - Invalid product: ",productService.getProductByID(5555) == null);
    }
}
