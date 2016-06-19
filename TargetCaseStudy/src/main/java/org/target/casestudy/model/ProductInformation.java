package org.target.casestudy.model;

import java.util.List;
/**
 *
 * @author Asad Islam
 * Last Updated: June 19, 2016
 *
 */

public class ProductInformation {
	
	List<Product> product;
	ProductCompositeResponse pcr;
	/**
	 * @return the product
	 */
	public List<Product> getProduct() {
		return product;
	}
	/**
	 * @param product the product to set
	 */
	public void setProduct(List<Product> product) {
		this.product = product;
	}
	/**
	 * @return the pcr
	 */
	public ProductCompositeResponse getPcr() {
		return pcr;
	}
	/**
	 * @param pcr the pcr to set
	 */
	public void setPcr(ProductCompositeResponse pcr) {
		this.pcr = pcr;
	}
}
