package org.target.casestudy.model;

import java.util.List;
/**
 * Last Modified: June 10, 2016
 * @author Asad Islam
 *
 */
public class Items {
	
	List<Identifier> identifier;
	String relation;
	String relationDescription;
	String dataPageLink;
	String imnIdentifier;
	String isOderable;
	String isSellable;
	String generalDescriptions;
	String isCircularPublish;
	BusinessProcessStatus businessProcessStatus;
	String dpci;
	String departmentId;
	String classId;
	String itemId;
	OnlineDescription onlineDescription;
	StoreDescription storeDescription;
	List<AlternateDescription> alternateDescription;
	
	
	
	/**
	 * @return the itemId
	 */
	public String getItemId() {
		return itemId;
	}
	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	/**
	 * @return the identifier
	 */
	public List<Identifier> getIdentifier() {
		return identifier;
	}
	/**
	 * @param identifier the identifier to set
	 */
	public void setIdentifier(List<Identifier> identifier) {
		this.identifier = identifier;
	}
	/**
	 * @return the relation
	 */
	public String getRelation() {
		return relation;
	}
	/**
	 * @param relation the relation to set
	 */
	public void setRelation(String relation) {
		this.relation = relation;
	}
	/**
	 * @return the relationDescription
	 */
	public String getRelationDescription() {
		return relationDescription;
	}
	/**
	 * @param relationDescription the relationDescription to set
	 */
	public void setRelationDescription(String relationDescription) {
		this.relationDescription = relationDescription;
	}
	/**
	 * @return the dataPageLink
	 */
	public String getDataPageLink() {
		return dataPageLink;
	}
	/**
	 * @param dataPageLink the dataPageLink to set
	 */
	public void setDataPageLink(String dataPageLink) {
		this.dataPageLink = dataPageLink;
	}
	/**
	 * @return the imnIdentifier
	 */
	public String getImnIdentifier() {
		return imnIdentifier;
	}
	/**
	 * @param imnIdentifier the imnIdentifier to set
	 */
	public void setImnIdentifier(String imnIdentifier) {
		this.imnIdentifier = imnIdentifier;
	}
	/**
	 * @return the isOderable
	 */
	public String getIsOderable() {
		return isOderable;
	}
	/**
	 * @param isOderable the isOderable to set
	 */
	public void setIsOderable(String isOderable) {
		this.isOderable = isOderable;
	}
	/**
	 * @return the isSellable
	 */
	public String getIsSellable() {
		return isSellable;
	}
	/**
	 * @param isSellable the isSellable to set
	 */
	public void setIsSellable(String isSellable) {
		this.isSellable = isSellable;
	}
	/**
	 * @return the generalDescriptions
	 */
	public String getGeneralDescriptions() {
		return generalDescriptions;
	}
	/**
	 * @param generalDescriptions the generalDescriptions to set
	 */
	public void setGeneralDescriptions(String generalDescriptions) {
		this.generalDescriptions = generalDescriptions;
	}
	/**
	 * @return the isCircularPublish
	 */
	public String getIsCircularPublish() {
		return isCircularPublish;
	}
	/**
	 * @param isCircularPublish the isCircularPublish to set
	 */
	public void setIsCircularPublish(String isCircularPublish) {
		this.isCircularPublish = isCircularPublish;
	}
	
	/**
	 * @return the businessProcessStatus
	 */
	public BusinessProcessStatus getBusinessProcessStatus() {
		return businessProcessStatus;
	}
	/**
	 * @param businessProcessStatus the businessProcessStatus to set
	 */
	public void setBusinessProcessStatus(BusinessProcessStatus businessProcessStatus) {
		this.businessProcessStatus = businessProcessStatus;
	}
	/**
	 * @return the dpci
	 */
	public String getDpci() {
		return dpci;
	}
	/**
	 * @param dpci the dpci to set
	 */
	public void setDpci(String dpci) {
		this.dpci = dpci;
	}
	/**
	 * @return the departmentId
	 */
	public String getDepartmentId() {
		return departmentId;
	}
	/**
	 * @param departmentId the departmentId to set
	 */
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	/**
	 * @return the classId
	 */
	public String getClassId() {
		return classId;
	}
	/**
	 * @param classId the classId to set
	 */
	public void setClassId(String classId) {
		this.classId = classId;
	}
	/**
	 * @return the onlineDescription
	 */
	public OnlineDescription getOnlineDescription() {
		return onlineDescription;
	}
	/**
	 * @param onlineDescription the onlineDescription to set
	 */
	public void setOnlineDescription(OnlineDescription onlineDescription) {
		this.onlineDescription = onlineDescription;
	}
	
	/**
	 * @return the storeDescription
	 */
	public StoreDescription getStoreDescription() {
		return storeDescription;
	}
	/**
	 * @param storeDescription the storeDescription to set
	 */
	public void setStoreDescription(StoreDescription storeDescription) {
		this.storeDescription = storeDescription;
	}
	/**
	 * @return the alternateDescription
	 */
	public List<AlternateDescription> getAlternateDescription() {
		return alternateDescription;
	}
	/**
	 * @param alternateDescription the alternateDescription to set
	 */
	public void setAlternateDescription(
			List<AlternateDescription> alternateDescription) {
		this.alternateDescription = alternateDescription;
	}
}
