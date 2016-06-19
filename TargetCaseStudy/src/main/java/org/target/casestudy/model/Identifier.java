package org.target.casestudy.model;
/**
 * Last Modified: June 10, 2016
 * @author Asad Islam
 *
 */
public class Identifier {
	String id_type;
	String id;
	String is_primary;
	String source;
	/**
	 * @return the id_tyepe
	 */
	public String getId_type() {
		return id_type;
	}
	/**
	 * @param id_tyepe the id_tyepe to set
	 */
	public void setId_type(String id_type) {
		this.id_type = id_type;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the is_primary
	 */
	public String getIs_primary() {
		return is_primary;
	}
	/**
	 * @param is_primary the is_primary to set
	 */
	public void setIs_primary(String is_primary) {
		this.is_primary = is_primary;
	}
	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}
}
