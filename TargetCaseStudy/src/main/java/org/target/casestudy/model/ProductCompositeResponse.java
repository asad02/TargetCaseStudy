package org.target.casestudy.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;
/**
 * Last Modified: June 10, 2016
 * @author Asad Islam
 *
 */
public class ProductCompositeResponse {
	
	@SerializedName("request_attributes")
	List<RequestAttribute> requestAttribute  = new ArrayList<RequestAttribute>();
	
	@SerializedName("items")
	List<Items> items  = new ArrayList<Items>();

	/**
	 * @return the requestAttribute
	 */
	public List<RequestAttribute> getRequestAttribute() {
		return requestAttribute;
	}

	/**
	 * @param requestAttribute the requestAttribute to set
	 */
	public void setRequestAttribute(List<RequestAttribute> requestAttribute) {
		this.requestAttribute = requestAttribute;
	}

	/**
	 * @return the items
	 */
	public List<Items> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(List<Items> items) {
		this.items = items;
	}
}
