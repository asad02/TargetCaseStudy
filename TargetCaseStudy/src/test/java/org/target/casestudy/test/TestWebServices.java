package org.target.casestudy.test;


import static org.junit.Assert.assertEquals;

import java.net.URI;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.junit.Test;

public class TestWebServices {
	
	@Test
	   public void readAllproduct() {
		ClientConfig config = new ClientConfig();
	    Client client = ClientBuilder.newClient(config);
	    WebTarget target = client.target(getBaseURI());
	    String response = target.path("products").path("allproduct").request().accept(MediaType.APPLICATION_JSON).get(Response.class).toString();
	    System.out.println(response);
	}
	
	 @Test
	    public void findAllProducts() {
		 ClientConfig config = new ClientConfig();
		    Client client = ClientBuilder.newClient(config);
		    WebTarget target = client.target(getBaseURI());
		    Response response = target.path("products").path("allproduct").request().accept(MediaType.APPLICATION_JSON).get(Response.class);
		    System.out.println("JSON Results: " + response);
		    String jsonResponseAsString = response.readEntity(String.class);

		     assertEquals(200, response.getStatus());
		     assertEquals("{\"id\":\"5555\",\"productname\":\"Stroller\"}", jsonResponseAsString);
	    }
	 
	 @Test
	    public void findProductById() throws NotFoundException {
		 ClientConfig config = new ClientConfig();
		    Client client = ClientBuilder.newClient(config);
		    WebTarget target = client.target(getBaseURI());
		    Response response = target.path("products").path("5555").request().accept(MediaType.APPLICATION_JSON).get(Response.class);
		    System.out.println("JSON Results: " + response);
		    String jsonResponseAsString = response.readEntity(String.class);

		     assertEquals(200, response.getStatus());
		     assertEquals("{\"id\":\"5555\",\"productname\":\"Stroller\"}", jsonResponseAsString);
	    }
	 
	 @Test
	    public void findProductByCategory() throws NotFoundException {
		 ClientConfig config = new ClientConfig();
		    Client client = ClientBuilder.newClient(config);
		    WebTarget target = client.target(getBaseURI());
		    Response response = target.path("products").path("category").path("baby").request().accept(MediaType.APPLICATION_JSON).get(Response.class);
		    System.out.println("JSON Results: " + response);
		    String jsonResponseAsString = response.readEntity(String.class);

		     assertEquals(200, response.getStatus());
		     assertEquals("{\"id\":\"5555\",\"productname\":\"Stroller\"}", jsonResponseAsString);
	    }

  private static URI getBaseURI() {
    return UriBuilder.fromUri("http://localhost:8081/TargetCaseStudy/").build();
  }
}
