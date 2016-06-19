package org.target.casestudy.resources;

/**
 * @author Asad Islam
 * Last Updated: June 19, 2016
 * GET, PUT, POST DELETE
 * Four web services are defined in this class, and mapped to four different url.
 * URL: http://localhost:8080/TargetCaseStudy/service/products/allproduct mapped to allproduct resuorces
 * URL: http://localhost:8080/TargetCaseStudy/service/products/{productId}, mapped to only one product resource
 * URL: http://localhost:8080/TargetCaseStudy/service/products/category/{category}, mapped to a product list with selected category
 * URL: http://localhost:8080/TargetCaseStudy/service/products/updateproduct
 * 	This service is mapped to create a new resource or update an existing resource, if a resource if already present
 */

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.Status.Family;

import org.target.casestudy.constant.ApplicationConstant;
import org.target.casestudy.model.AlternateDescription;
import org.target.casestudy.model.BusinessProcessStatus;
import org.target.casestudy.model.Identifier;
import org.target.casestudy.model.Items;
import org.target.casestudy.model.OnlineDescription;
import org.target.casestudy.model.ProcessStatus;
import org.target.casestudy.model.Product;
import org.target.casestudy.model.ProductCompositeResponse;
import org.target.casestudy.model.ProductInformation;
import org.target.casestudy.model.RequestAttribute;
import org.target.casestudy.model.StoreDescription;
import org.target.casestudy.service.ProductService;
import org.target.casestudy.util.BeanValidation;
import org.target.casestudy.util.SuccessMessage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

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
	 * Mapped to path allproducts
	 * @return all the product from the data base
	 */
	@GET
	@Path("/productinfo")
	@Produces(MediaType.APPLICATION_JSON)
	public ProductInformation getAllProductsInfo()
	{
		ProductInformation productInfo = new ProductInformation();
		List<Product> productList = productService.getAllProduct();
		ProductCompositeResponse pcr = getDetailFromInternalAPI();
		// Update product name, get the product name from the external API and then if product exist
		//update product name else do nothing.
		for(Product product : productList) {
			productService.getProductDescr(product, pcr);
		}
		productInfo.setPcr(pcr);
		productInfo.setProduct(productList);
		return productInfo;
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
		ProductCompositeResponse pcr = getDetailFromInternalAPI();
		// Update product name, get the product name from the external API and then if product exist
		//update product name else do nothing.
		Product product = productService.getProductByID(productId);
		return productService.getProductDescr(product, pcr);
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
	@DELETE
	@Consumes({MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
	@Path("{productId}")
	public Response deleteProductById(@PathParam(ApplicationConstant.PRODUCT_ID) int productId) throws ConstraintViolationException, ValidationException, IOException
	{
		Response response = null;
		Product product = productService.getProductByID(productId);
	    Calendar cal = Calendar.getInstance();
		product.setLastUpdatedDate(cal.getTime());
		product.setActive(ApplicationConstant.EMPTY_STR);
		product.setPriceActive(ApplicationConstant.EMPTY_STR);
		boolean update = productService.updateProductDetails(product);
		if(update) {
			SuccessMessage successMessage = new SuccessMessage();
			successMessage.setSuccessCode(Status.OK.getStatusCode());
			successMessage.setSuccessMessage(Status.OK.toString());
			response = Response.status(200).entity(successMessage).build();
		}
		return response;
	}
	
	/**
	 * This will return a list of product of selected category.
	 * @param category, which the list should be returned.
	 * @return a list of product with matching provided category.
	 */
	@GET
	@Path("/category/{category}")
	@Produces(MediaType.APPLICATION_JSON)
	public ProductInformation getProductByCategory(@PathParam(ApplicationConstant.CAT) String category)
	{
		ProductInformation productInfo = new ProductInformation();
		List<Product> productList = productService.getProductByCategory(category);
		ProductCompositeResponse pcr = getDetailFromInternalAPI();
		// Update product name, get the product name from the external API and then if product exist
		//update product name else do nothing.
		for(Product product : productList) {
			productService.getProductDescr(product, pcr);
		}
		productInfo.setPcr(pcr);
		productInfo.setProduct(productList);
		return productInfo;
	}
	/**
	 * This method will update/create a product based on the product id. A JSON will be posted 
	 * from the client and after the validation it will be updated into the data base
	 * of provided product id.
	 * @param product, a product as a JSON format
	 * @return, SuccessMesssage with 200 status code, and OK
	 * @throws ConstraintViolationException, if there is any constraint
	 * @throws ValidationException, if there is any violation
	 * @throws IOException, optional just in case if there is any error related to IO
	 */
	@POST
	@Path("createproduct")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateProduct(Product product) throws ConstraintViolationException, ValidationException, IOException
	{
		Response response = null;
		BeanValidation beanValidation = new BeanValidation();
		boolean update = false;
		
		if(beanValidation.validateBean(product)) {
			Product p = productService.getProductByID(product.getProductId());
			Calendar cal = Calendar.getInstance();
			product.setLastUpdatedDate(cal.getTime());
			if(p != null) {
				update = productService.updateProductDetails(product);
			} else {
				update = productService.insertIntoProduct(product);
			}
			
			if(update) {
				SuccessMessage successMessage = new SuccessMessage();
				successMessage.setSuccessCode(Status.OK.getStatusCode());
				successMessage.setSuccessMessage(Status.OK.toString());
				response = Response.status(200).entity(successMessage).build();
			}
		}
		return response;
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
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{productId}")
	public Response updateProductById(Product product, @PathParam(ApplicationConstant.PRODUCT_ID) int productId) throws ConstraintViolationException, ValidationException, IOException
	{
		Response response = null;
		BeanValidation beanValidation = new BeanValidation();
		
		if(beanValidation.validateBean(product)) {
			//get current date time with Calendar()
		   Calendar cal = Calendar.getInstance();
			product.setLastUpdatedDate(cal.getTime());
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
	
	/**
	 * Mapped to path detailsfromapi
	 * @return all the product from the data base
	 */
	@GET
	@Path("/detailsfromapi")
	@Produces(MediaType.APPLICATION_JSON)
	public ProductCompositeResponse getDetailFromInternalAPI()
	{
		
		ProductCompositeResponse pcr = new ProductCompositeResponse();
		List<Items> items = new ArrayList<Items>();
		List<RequestAttribute> requestAttr = new ArrayList<RequestAttribute>();
		JsonParser jsonParser = new JsonParser();
		GsonBuilder  gsonBuilder = new GsonBuilder();
		Gson gson = new Gson();
		//Define a new client
		Client client = ClientBuilder.newClient();
		//Define a resource
		WebTarget resource = client.target(ApplicationConstant.TARGET_EXT_API);
		
		Builder request = resource.request();
		request.accept(MediaType.APPLICATION_JSON);
		// Response
		Response response = request.get();
		//if response is successful then process the data
		if (response.getStatusInfo().getFamily() == Family.SUCCESSFUL) {
			String jsonobj = response.readEntity(String.class);
			// Parse items data and set append to the final response.
			JsonArray itemsData = jsonParser.parse(jsonobj).getAsJsonObject().getAsJsonObject(ApplicationConstant.PRODUCT_COMPOSITE_RESPONSE).getAsJsonArray(ApplicationConstant.ITEMS);
			Type itemListType = new TypeToken<List<Items>>() {}.getType();
			gson = gsonBuilder.registerTypeAdapter(Items.class, new ItemsJsonAdapter()).create();
			items = gson.fromJson(itemsData, itemListType);
			//Parse request attributes data and append to the final response
			JsonArray requestAttrData = jsonParser.parse(jsonobj).getAsJsonObject().getAsJsonObject(ApplicationConstant.PRODUCT_COMPOSITE_RESPONSE).getAsJsonArray(ApplicationConstant.REQ_ATTRIBUTES);
			Type requestAttrListType = new TypeToken<List<RequestAttribute>>() {}.getType();
			gson = gsonBuilder.registerTypeAdapter(RequestAttribute.class, new RequestAttrJsonAdapter()).create();
			requestAttr = gson.fromJson(requestAttrData, requestAttrListType);
			
			pcr.setItems(items);
			pcr.setRequestAttribute(requestAttr);
			
		} else {
		    System.out.println("ERROR! " + response.getStatus());    
		    System.out.println(response.getEntity());
		}
		return pcr;
	}
	
	private class ItemsJsonAdapter extends TypeAdapter<Items> {
		
		JsonParser parser;
		ItemsJsonAdapter() {
			parser = new JsonParser();
		}
		@Override
		public Items read(JsonReader input) throws IOException {
			JsonObject jsonObject = parser.parse(input).getAsJsonObject();
			Items items = new Items();
			// Read Identifier
			JsonArray identifierJsonArray = jsonObject.getAsJsonArray(ApplicationConstant.IDENTIFIER);
			List<Identifier> identifier = new ArrayList<Identifier>();
			for (JsonElement identifierJsonElement : identifierJsonArray) {
				identifier.add(createIdentifier(identifierJsonElement.getAsJsonObject()));
			}
			items.setIdentifier(identifier);
			//Read other stuff
			items.setRelation(getNullSafeString(jsonObject, ApplicationConstant.RELATION));
			items.setRelationDescription(getNullSafeString(jsonObject, ApplicationConstant.RELATION_DESCR));
			items.setDataPageLink(getNullSafeString(jsonObject, ApplicationConstant.DATA_PAGE_LINK));
			items.setImnIdentifier(getNullSafeString(jsonObject, ApplicationConstant.IMN_IDENTIFIER));
			items.setIsOderable(getNullSafeString(jsonObject, ApplicationConstant.IS_ORERABLE));
			items.setIsSellable(getNullSafeString(jsonObject, ApplicationConstant.IS_SELLABLE));
			items.setGeneralDescriptions(getNullSafeString(jsonObject, ApplicationConstant.GENERAL_DESCR));
			items.setIsCircularPublish(getNullSafeString(jsonObject, ApplicationConstant.IS_CIRCULAR_PUBLISH));
			
			//Read business process array
			JsonArray businessProcessJsonArray = jsonObject.getAsJsonArray(ApplicationConstant.BUSINESS_PROCESS_STATUS);
			BusinessProcessStatus businessProcessStatus = new BusinessProcessStatus();
			List<ProcessStatus> processStatus = new ArrayList<ProcessStatus>();
			for (JsonElement processStatusJsonElement : businessProcessJsonArray) {
				processStatus.add(createProcessStatus(processStatusJsonElement.getAsJsonObject().getAsJsonObject(ApplicationConstant.PROCESS_STATUS)));
			}
			businessProcessStatus.setProcessStatus(processStatus);
			items.setBusinessProcessStatus(businessProcessStatus);
			
			//other stuff
			items.setDpci(getNullSafeString(jsonObject, ApplicationConstant.DPCI));
			items.setDepartmentId(getNullSafeString(jsonObject, ApplicationConstant.DEPERTMENT_ID));
			items.setClassId(getNullSafeString(jsonObject, ApplicationConstant.CLASS_ID));
			items.setItemId(getNullSafeString(jsonObject, ApplicationConstant.ITEM_ID));
			
			//Online Description
			OnlineDescription onlineDesc = new OnlineDescription();
			JsonObject onlineDescriptionJsonArray = jsonObject.getAsJsonObject(ApplicationConstant.ONLINE_DESCR);
			onlineDesc.setValue(getNullSafeString(onlineDescriptionJsonArray, ApplicationConstant.VALUE));
			onlineDesc.setType(getNullSafeString(onlineDescriptionJsonArray, ApplicationConstant.TYPE));
			items.setOnlineDescription(onlineDesc);
			
			//Store Descriptions
			StoreDescription storeDesc = new StoreDescription();
			JsonObject storeDescriptionJsonArray = jsonObject.getAsJsonObject(ApplicationConstant.STORE_DESCR);
			storeDesc.setValue(getNullSafeString(storeDescriptionJsonArray, ApplicationConstant.VALUE));
			storeDesc.setType(getNullSafeString(storeDescriptionJsonArray, ApplicationConstant.TYPE));
			items.setStoreDescription(storeDesc);
			
			//alternate description
			JsonArray alternateDescJsonArray = jsonObject.getAsJsonArray(ApplicationConstant.ALTERNATE_DESCR);
			List<AlternateDescription> alternateDesc = new ArrayList<AlternateDescription>();
			for (JsonElement alternateDescJsonElement : alternateDescJsonArray) {
				alternateDesc.add(createIAlternateDescJson(alternateDescJsonElement.getAsJsonObject()));
			}
			items.setAlternateDescription(alternateDesc);
			
			
			return items;
		}

		private AlternateDescription createIAlternateDescJson(JsonObject asJsonObject) {
				AlternateDescription alternateDesc = new AlternateDescription();
				alternateDesc.setType(getNullSafeString(asJsonObject, ApplicationConstant.TYPE));
				alternateDesc.setValue(getNullSafeString(asJsonObject, ApplicationConstant.VALUE));
				alternateDesc.setTypeDescription(getNullSafeString(asJsonObject, ApplicationConstant.TYPE_DESCR));
				return alternateDesc;
		}
		private ProcessStatus createProcessStatus(JsonObject asJsonObject) {
			ProcessStatus processStatus = new ProcessStatus();
			processStatus.setIs_ready(getNullSafeString(asJsonObject, ApplicationConstant.IS_READY));
			processStatus.setOperation_Description(getNullSafeString(asJsonObject, ApplicationConstant.OPERATION_DESCR));
			processStatus.setOpenrationCode(getNullSafeString(asJsonObject, ApplicationConstant.OPERATION_CODE));
			return processStatus;
		}
		private Identifier createIdentifier(JsonObject asJsonObject) {
			Identifier iden = new Identifier();
			iden.setId(getNullSafeString(asJsonObject, ApplicationConstant.ID));
			iden.setId_type(getNullSafeString(asJsonObject, ApplicationConstant.ID_TYPE));
			iden.setIs_primary(getNullSafeString(asJsonObject, ApplicationConstant.IS_PRIMARY));
			iden.setSource(getNullSafeString(asJsonObject, ApplicationConstant.SOURCE));
			return iden;
		}
		@Override
		public void write(JsonWriter out, Items val) throws IOException {
			out.setHtmlSafe(true);
			out.setIndent(" ");
			out.name(ApplicationConstant.IDENTIFIER);
			out.beginObject();
			// implement all necessary steps to write from java object to json.
			out.endObject();	
		}
	}
	//Request attribute adapter
	private class RequestAttrJsonAdapter extends TypeAdapter<RequestAttribute> {
		
		JsonParser parser;
		RequestAttrJsonAdapter() {
			parser = new JsonParser();
		}
		@Override
		public RequestAttribute read(JsonReader input) throws IOException {
			JsonObject jsonObject = parser.parse(input).getAsJsonObject();
			RequestAttribute requestAttr = new RequestAttribute();
			requestAttr.setName(getNullSafeString(jsonObject, ApplicationConstant.NAME));
			requestAttr.setValue(getNullSafeString(jsonObject, ApplicationConstant.VALUE));
			return requestAttr;
		}
		
		@Override
		public void write(JsonWriter out, RequestAttribute val) throws IOException {
			out.setHtmlSafe(true);
			out.setIndent(ApplicationConstant.SPACE);
			out.name(ApplicationConstant.REQ_ATTRIBUTES);
			out.beginObject();
			out.name(ApplicationConstant.NAME).value(val.getName());
			out.name(ApplicationConstant.VALUE).value(val.getName());
			out.endObject();	
		}
	}
	
	String getNullSafeString(JsonObject json, String propertyName) {
		
		if(json.has(propertyName) && !json.get(propertyName).isJsonNull()) {
			return (json.has(propertyName)) ? json.get(propertyName).getAsString() : null;
		}
		else {
			return ApplicationConstant.NULL;
		}
	}
}
