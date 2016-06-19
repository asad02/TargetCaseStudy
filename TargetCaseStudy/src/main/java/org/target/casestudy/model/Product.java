/**
 * 
 */
package org.target.casestudy.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Asad Islam
 * Last Updated: Feb 7, 2016
 */
@XmlRootElement(name="Products")
@XmlAccessorType(XmlAccessType.FIELD)
public class Product {
	
	@NotNull
	public int productId;
	public String sku;
	public String productName;
	public String productCategory;
	public Date lastUpdatedDate;
	public double price;
	public String currency;	
	public String productSource;
	public String active;
	public String priceActive;

	public Product() {
	}
	
	public Product(int prodId, String prodName, String prodCat, Date ProdLastUpd, double price, String sku, String currency, String productSource) {
		this.productId = prodId;
		this.productName = prodName;
		this.productCategory = prodCat;
		this.lastUpdatedDate = ProdLastUpd;
		this.price = price;
		this.sku = sku;
		this.currency = currency;
		this.productSource = productSource;
	}
	
	/**
	 * @return the priceActive
	 */
	public String getPriceActive() {
		return priceActive;
	}

	/**
	 * @param priceActive the priceActive to set
	 */
	public void setPriceActive(String priceActive) {
		this.priceActive = priceActive;
	}

	/**
	 * @return the active
	 */
	public String getActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(String active) {
		this.active = active;
	}
	
	/**
	 * @return the productSource
	 */
	public String getProductSource() {
		return productSource;
	}

	/**
	 * @param productSource the productSource to set
	 */
	public void setProductSource(String productSource) {
		this.productSource = productSource;
	}

	/**
	 * @return the productId
	 */
	public int getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}
	/**
	 * @return the sku
	 */
	public String getSku() {
		return sku;
	}
	/**
	 * @param sku the sku to set
	 */
	public void setSku(String sku) {
		this.sku = sku;
	}
	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * @return the productCategory
	 */
	public String getProductCategory() {
		return productCategory;
	}
	/**
	 * @param productCategory the productCategory to set
	 */
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	/**
	 * @return the lastUpdatedDate
	 */
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	/**
	 * @param lastUpdatedDate the lastUpdatedDate to set
	 */
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
