package org.kuali.ole.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ole_ds_license_t database table.
 * 
 */
@Entity
@Table(name="ole_ds_license_t")
public class OleDsLicenseT implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="LICENSE_ID")
	private Integer licenseId;

	@Column(name="AGREEMENT_NOTE")
	private String agreementNote;

	@Column(name="AGREEMENT_TYPE")
	private String agreementType;

	@Lob
	private byte[] content;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_CREATED")
	private Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_UPDATED")
	private Date dateUpdated;

	@Column(name="DOCUMENT_MIME_TYPE")
	private String documentMimeType;

	@Column(name="DOCUMENT_NOTE")
	private String documentNote;

	@Column(name="DOCUMENT_TITLE")
	private String documentTitle;

	@Column(name="FILE_NAME")
	private String fileName;

	@Column(name="UNIQUE_ID_PREFIX")
	private String uniqueIdPrefix;

	@Column(name="UPDATED_BY")
	private String updatedBy;

	public OleDsLicenseT() {
	}

	public Integer getLicenseId() {
		return this.licenseId;
	}

	public void setLicenseId(Integer licenseId) {
		this.licenseId = licenseId;
	}

	public String getAgreementNote() {
		return this.agreementNote;
	}

	public void setAgreementNote(String agreementNote) {
		this.agreementNote = agreementNote;
	}

	public String getAgreementType() {
		return this.agreementType;
	}

	public void setAgreementType(String agreementType) {
		this.agreementType = agreementType;
	}

	public byte[] getContent() {
		return this.content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateUpdated() {
		return this.dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public String getDocumentMimeType() {
		return this.documentMimeType;
	}

	public void setDocumentMimeType(String documentMimeType) {
		this.documentMimeType = documentMimeType;
	}

	public String getDocumentNote() {
		return this.documentNote;
	}

	public void setDocumentNote(String documentNote) {
		this.documentNote = documentNote;
	}

	public String getDocumentTitle() {
		return this.documentTitle;
	}

	public void setDocumentTitle(String documentTitle) {
		this.documentTitle = documentTitle;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUniqueIdPrefix() {
		return this.uniqueIdPrefix;
	}

	public void setUniqueIdPrefix(String uniqueIdPrefix) {
		this.uniqueIdPrefix = uniqueIdPrefix;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

}