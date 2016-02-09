package org.target.casestudy.resources;

/**
 * @author Asad Islam
 * Last Updated: Feb 7, 2016
 * 
 * Four web services are defined in this class, and mapped to four different url.
 * URL: http://localhost:8080/TargetCaseStudy/service/products/allproduct mapped to allproduct resuorces
 * URL: http://localhost:8080/TargetCaseStudy/service/products/{productId}, mapped to only one product resource
 * URL: http://localhost:8080/TargetCaseStudy/service/products/category/{category}, mapped to a product list with selected category
 * URL: http://localhost:8080/TargetCaseStudy/service/products/updateproduct
 * 	This service is mapped to create a new resource or update an existing resource, if a resource if already present
 */

import java.io.IOException;
import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.target.casestudy.model.Product;
import org.target.casestudy.service.ProductService;
import org.target.casestudy.util.BeanValidation;
//import org.target.casestudy.util.BeanValidation;
import org.target.casestudy.util.SuccessMessage;

@Path("/products")
public class MyRetailStoreResource {
	
	// Product service
	ProductService productService = new ProductService();
	/**
	 * Mapped to path allproducts
	 * @return all the product from the data base
	 */
	@GET
	@Path("/allproducts")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getAllProducts()
	{
		return productService.getAllProduct();
	}
	/**
	 * This will return a product matched with the provided product id.
	 * @param productId, path parameter as a product id
	 * @return a Product
	 */
	@GET
	@Path("{productId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Product getProductById(@PathParam("productId") int productId)
	{
		return productService.getProductByID(productId);
	}
	/**
	 * This will return a list of product of selected category.
	 * @param category, which the list should be returned.
	 * @return a list of product with matching provided category.
	 */
	@GET
	@Path("/category/{category}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getProductByCategory(@PathParam("category") String category)
	{
		return productService.getProductByCategory(category);
	}
	/**
	 * This method will update a product based on the product id. A JSON will be posted 
	 * from the client and after the validation it will be updated into the data base
	 * of provided product id.
	 * @param product, a product as a JSON format
	 * @return, SuccessMesssage with 200 status code, and OK
	 * @throws ConstraintViolationException, if there is any constraint
	 * @throws ValidationException, if there is any violation
	 * @throws IOException, optional just in case if there is any error related to IO
	 */
	@POST
	@Path("updateproduct")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateProduct(Product product) throws ConstraintViolationException, ValidationException, IOException
	{
		Response response = null;
		BeanValidation beanValidation = new BeanValidation();
		
		if(beanValidation.validateBean(product)) {
			boolean update = productService.updateProductDetails(product);
			if(update) {
				SuccessMessage successMessage = new SuccessMessage();
				successMessage.setSuccessCode(Status.OK.getStatusCode());
				successMessage.setSuccessMessage(Status.OK.toString());
				response = Response.status(200).entity(successMessage).build();
			}
		}
		return response;
	}
}
