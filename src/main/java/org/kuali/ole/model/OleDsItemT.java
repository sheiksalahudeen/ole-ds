package org.kuali.ole.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ole_ds_item_t database table.
 * 
 */
@Entity
@Table(name="ole_ds_item_t")
public class OleDsItemT implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ITEM_ID")
	private Integer itemId;

	private String barcode;

	@Column(name="BARCODE_ARSL")
	private String barcodeArsl;

	@Column(name="CALL_NUMBER")
	private String callNumber;

	@Column(name="CALL_NUMBER_PREFIX")
	private String callNumberPrefix;

	@Column(name="CALL_NUMBER_TYPE_ID")
	private Integer callNumberTypeId;

	@Column(name="CHECK_IN_NOTE")
	private String checkInNote;

	@Column(name="CHECK_OUT_DATE_TIME")
	private Timestamp checkOutDateTime;

	private String chronology;

	@Column(name="CLAIMS_RETURNED")
	private String claimsReturned;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CLAIMS_RETURNED_DATE_CREATED")
	private Date claimsReturnedDateCreated;

	@Column(name="CLAIMS_RETURNED_NOTE")
	private String claimsReturnedNote;

	@Column(name="COPY_NUMBER")
	private String copyNumber;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="CURRENT_BORROWER")
	private String currentBorrower;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_CREATED")
	private Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_UPDATED")
	private Date dateUpdated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DUE_DATE_TIME")
	private Date dueDateTime;

	private String enumeration;

	@Column(name="FAST_ADD")
	private String fastAdd;

	private String fund;

	@Column(name="ITEM_DAMAGED_NOTE")
	private String itemDamagedNote;

	@Column(name="ITEM_DAMAGED_STATUS")
	private String itemDamagedStatus;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ITEM_STATUS_DATE_UPDATED")
	private Date itemStatusDateUpdated;

	@Column(name="ITEM_STATUS_ID")
	private Integer itemStatusId;

	@Column(name="ITEM_TYPE_ID")
	private Integer itemTypeId;

	private String location;

	@Column(name="LOCATION_ID")
	private Integer locationId;

	@Column(name="LOCATION_LEVEL")
	private String locationLevel;

	@Column(name="MISSING_PIECES")
	private String missingPieces;

	@Column(name="MISSING_PIECES_COUNT")
	private Integer missingPiecesCount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="MISSING_PIECES_EFFECTIVE_DATE")
	private Date missingPiecesEffectiveDate;

	@Column(name="MISSING_PIECES_NOTE")
	private String missingPiecesNote;

	@Column(name="NUM_OF_RENEW")
	private Integer numOfRenew;

	@Column(name="NUM_PIECES")
	private String numPieces;

	@Column(name="ORG_DUE_DATE_TIME")
	private Timestamp orgDueDateTime;

	private BigDecimal price;

	@Column(name="PROXY_BORROWER")
	private String proxyBorrower;

	@Column(name="PURCHASE_ORDER_LINE_ITEM_ID")
	private String purchaseOrderLineItemId;

	@Column(name="SHELVING_ORDER")
	private String shelvingOrder;

	@Column(name="STAFF_ONLY")
	private String staffOnly;

	@Column(name="TEMP_ITEM_TYPE_ID")
	private Integer tempItemTypeId;

	@Column(name="UNIQUE_ID_PREFIX")
	private String uniqueIdPrefix;

	@Column(name="UPDATED_BY")
	private String updatedBy;

	private String uri;

	@Column(name="VENDOR_LINE_ITEM_ID")
	private String vendorLineItemId;

	//bi-directional many-to-one association to OleDsDmgdItmHstryT
	@OneToMany(mappedBy="oleDsItemT")
	private List<OleDsDmgdItmHstryT> oleDsDmgdItmHstryTs;

	//bi-directional many-to-one association to OleDsItemDonorT
	@OneToMany(mappedBy="oleDsItemT")
	private List<OleDsItemDonorT> oleDsItemDonorTs;

	//bi-directional many-to-one association to OleDsItemHoldingsT
	@OneToMany(mappedBy="oleDsItemT")
	private List<OleDsItemHoldingsT> oleDsItemHoldingsTs;

	//bi-directional many-to-one association to OleDsItemNoteT
	@OneToMany(mappedBy="oleDsItemT")
	private List<OleDsItemNoteT> oleDsItemNoteTs;

	//bi-directional many-to-one association to OleDsItemStatSearchT
	@OneToMany(mappedBy="oleDsItemT")
	private List<OleDsItemStatSearchT> oleDsItemStatSearchTs;

	//bi-directional many-to-one association to OleDsHighDensityStorageT
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="HIGH_DENSITY_STORAGE_ID")
	private OleDsHighDensityStorageT oleDsHighDensityStorageT;

	//bi-directional many-to-one association to OleDsHoldingsT
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="HOLDINGS_ID")
	private OleDsHoldingsT oleDsHoldingsT;

	//bi-directional many-to-one association to OleDsItmFormerIdentifierT
	@OneToMany(mappedBy="oleDsItemT")
	private List<OleDsItmFormerIdentifierT> oleDsItmFormerIdentifierTs;

	//bi-directional many-to-one association to OleDsLocCheckinCountT
	@OneToMany(mappedBy="oleDsItemT")
	private List<OleDsLocCheckinCountT> oleDsLocCheckinCountTs;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CALL_NUMBER_TYPE_ID", insertable=false ,updatable=false)
	private OleCatShvlgSchmT oleCatShvlgSchmT;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ITEM_STATUS_ID", insertable=false ,updatable=false)
	private OleDlvrItemAvailStatT oleDlvrItemAvailStatT;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ITEM_TYPE_ID", insertable=false ,updatable=false)
	private OleCatItmTypT itemTypeRecord;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TEMP_ITEM_TYPE_ID", insertable=false ,updatable=false)
	private OleCatItmTypT tempItemTypeRecord;

	public OleDsItemT() {
	}

	public Integer getItemId() {
		return this.itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getBarcode() {
		return this.barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getBarcodeArsl() {
		return this.barcodeArsl;
	}

	public void setBarcodeArsl(String barcodeArsl) {
		this.barcodeArsl = barcodeArsl;
	}

	public String getCallNumber() {
		return this.callNumber;
	}

	public void setCallNumber(String callNumber) {
		this.callNumber = callNumber;
	}

	public String getCallNumberPrefix() {
		return this.callNumberPrefix;
	}

	public void setCallNumberPrefix(String callNumberPrefix) {
		this.callNumberPrefix = callNumberPrefix;
	}

	public Integer getCallNumberTypeId() {
		return this.callNumberTypeId;
	}

	public void setCallNumberTypeId(Integer callNumberTypeId) {
		this.callNumberTypeId = callNumberTypeId;
	}

	public String getCheckInNote() {
		return this.checkInNote;
	}

	public void setCheckInNote(String checkInNote) {
		this.checkInNote = checkInNote;
	}

	public Timestamp getCheckOutDateTime() {
		return this.checkOutDateTime;
	}

	public void setCheckOutDateTime(Timestamp checkOutDateTime) {
		this.checkOutDateTime = checkOutDateTime;
	}

	public String getChronology() {
		return this.chronology;
	}

	public void setChronology(String chronology) {
		this.chronology = chronology;
	}

	public String getClaimsReturned() {
		return this.claimsReturned;
	}

	public void setClaimsReturned(String claimsReturned) {
		this.claimsReturned = claimsReturned;
	}

	public Date getClaimsReturnedDateCreated() {
		return this.claimsReturnedDateCreated;
	}

	public void setClaimsReturnedDateCreated(Date claimsReturnedDateCreated) {
		this.claimsReturnedDateCreated = claimsReturnedDateCreated;
	}

	public String getClaimsReturnedNote() {
		return this.claimsReturnedNote;
	}

	public void setClaimsReturnedNote(String claimsReturnedNote) {
		this.claimsReturnedNote = claimsReturnedNote;
	}

	public String getCopyNumber() {
		return this.copyNumber;
	}

	public void setCopyNumber(String copyNumber) {
		this.copyNumber = copyNumber;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCurrentBorrower() {
		return this.currentBorrower;
	}

	public void setCurrentBorrower(String currentBorrower) {
		this.currentBorrower = currentBorrower;
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

	public Date getDueDateTime() {
		return this.dueDateTime;
	}

	public void setDueDateTime(Date dueDateTime) {
		this.dueDateTime = dueDateTime;
	}

	public String getEnumeration() {
		return this.enumeration;
	}

	public void setEnumeration(String enumeration) {
		this.enumeration = enumeration;
	}

	public String getFastAdd() {
		return this.fastAdd;
	}

	public void setFastAdd(String fastAdd) {
		this.fastAdd = fastAdd;
	}

	public String getFund() {
		return this.fund;
	}

	public void setFund(String fund) {
		this.fund = fund;
	}

	public String getItemDamagedNote() {
		return this.itemDamagedNote;
	}

	public void setItemDamagedNote(String itemDamagedNote) {
		this.itemDamagedNote = itemDamagedNote;
	}

	public String getItemDamagedStatus() {
		return this.itemDamagedStatus;
	}

	public void setItemDamagedStatus(String itemDamagedStatus) {
		this.itemDamagedStatus = itemDamagedStatus;
	}

	public Date getItemStatusDateUpdated() {
		return this.itemStatusDateUpdated;
	}

	public void setItemStatusDateUpdated(Date itemStatusDateUpdated) {
		this.itemStatusDateUpdated = itemStatusDateUpdated;
	}

	public Integer getItemStatusId() {
		return this.itemStatusId;
	}

	public void setItemStatusId(Integer itemStatusId) {
		this.itemStatusId = itemStatusId;
	}

	public Integer getItemTypeId() {
		return this.itemTypeId;
	}

	public void setItemTypeId(Integer itemTypeId) {
		this.itemTypeId = itemTypeId;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getLocationId() {
		return this.locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public String getLocationLevel() {
		return this.locationLevel;
	}

	public void setLocationLevel(String locationLevel) {
		this.locationLevel = locationLevel;
	}

	public String getMissingPieces() {
		return this.missingPieces;
	}

	public void setMissingPieces(String missingPieces) {
		this.missingPieces = missingPieces;
	}

	public Integer getMissingPiecesCount() {
		return this.missingPiecesCount;
	}

	public void setMissingPiecesCount(Integer missingPiecesCount) {
		this.missingPiecesCount = missingPiecesCount;
	}

	public Date getMissingPiecesEffectiveDate() {
		return this.missingPiecesEffectiveDate;
	}

	public void setMissingPiecesEffectiveDate(Date missingPiecesEffectiveDate) {
		this.missingPiecesEffectiveDate = missingPiecesEffectiveDate;
	}

	public String getMissingPiecesNote() {
		return this.missingPiecesNote;
	}

	public void setMissingPiecesNote(String missingPiecesNote) {
		this.missingPiecesNote = missingPiecesNote;
	}

	public Integer getNumOfRenew() {
		return this.numOfRenew;
	}

	public void setNumOfRenew(Integer numOfRenew) {
		this.numOfRenew = numOfRenew;
	}

	public String getNumPieces() {
		return this.numPieces;
	}

	public void setNumPieces(String numPieces) {
		this.numPieces = numPieces;
	}

	public Timestamp getOrgDueDateTime() {
		return this.orgDueDateTime;
	}

	public void setOrgDueDateTime(Timestamp orgDueDateTime) {
		this.orgDueDateTime = orgDueDateTime;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getProxyBorrower() {
		return this.proxyBorrower;
	}

	public void setProxyBorrower(String proxyBorrower) {
		this.proxyBorrower = proxyBorrower;
	}

	public String getPurchaseOrderLineItemId() {
		return this.purchaseOrderLineItemId;
	}

	public void setPurchaseOrderLineItemId(String purchaseOrderLineItemId) {
		this.purchaseOrderLineItemId = purchaseOrderLineItemId;
	}

	public String getShelvingOrder() {
		return this.shelvingOrder;
	}

	public void setShelvingOrder(String shelvingOrder) {
		this.shelvingOrder = shelvingOrder;
	}

	public String getStaffOnly() {
		return this.staffOnly;
	}

	public void setStaffOnly(String staffOnly) {
		this.staffOnly = staffOnly;
	}

	public Integer getTempItemTypeId() {
		return this.tempItemTypeId;
	}

	public void setTempItemTypeId(Integer tempItemTypeId) {
		this.tempItemTypeId = tempItemTypeId;
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

	public String getUri() {
		return this.uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getVendorLineItemId() {
		return this.vendorLineItemId;
	}

	public void setVendorLineItemId(String vendorLineItemId) {
		this.vendorLineItemId = vendorLineItemId;
	}

	public List<OleDsDmgdItmHstryT> getOleDsDmgdItmHstryTs() {
		return this.oleDsDmgdItmHstryTs;
	}

	public void setOleDsDmgdItmHstryTs(List<OleDsDmgdItmHstryT> oleDsDmgdItmHstryTs) {
		this.oleDsDmgdItmHstryTs = oleDsDmgdItmHstryTs;
	}

	public OleDsDmgdItmHstryT addOleDsDmgdItmHstryT(OleDsDmgdItmHstryT oleDsDmgdItmHstryT) {
		getOleDsDmgdItmHstryTs().add(oleDsDmgdItmHstryT);
		oleDsDmgdItmHstryT.setOleDsItemT(this);

		return oleDsDmgdItmHstryT;
	}

	public OleDsDmgdItmHstryT removeOleDsDmgdItmHstryT(OleDsDmgdItmHstryT oleDsDmgdItmHstryT) {
		getOleDsDmgdItmHstryTs().remove(oleDsDmgdItmHstryT);
		oleDsDmgdItmHstryT.setOleDsItemT(null);

		return oleDsDmgdItmHstryT;
	}

	public List<OleDsItemDonorT> getOleDsItemDonorTs() {
		return this.oleDsItemDonorTs;
	}

	public void setOleDsItemDonorTs(List<OleDsItemDonorT> oleDsItemDonorTs) {
		this.oleDsItemDonorTs = oleDsItemDonorTs;
	}

	public OleDsItemDonorT addOleDsItemDonorT(OleDsItemDonorT oleDsItemDonorT) {
		getOleDsItemDonorTs().add(oleDsItemDonorT);
		oleDsItemDonorT.setOleDsItemT(this);

		return oleDsItemDonorT;
	}

	public OleDsItemDonorT removeOleDsItemDonorT(OleDsItemDonorT oleDsItemDonorT) {
		getOleDsItemDonorTs().remove(oleDsItemDonorT);
		oleDsItemDonorT.setOleDsItemT(null);

		return oleDsItemDonorT;
	}

	public List<OleDsItemHoldingsT> getOleDsItemHoldingsTs() {
		return this.oleDsItemHoldingsTs;
	}

	public void setOleDsItemHoldingsTs(List<OleDsItemHoldingsT> oleDsItemHoldingsTs) {
		this.oleDsItemHoldingsTs = oleDsItemHoldingsTs;
	}

	public OleDsItemHoldingsT addOleDsItemHoldingsT(OleDsItemHoldingsT oleDsItemHoldingsT) {
		getOleDsItemHoldingsTs().add(oleDsItemHoldingsT);
		oleDsItemHoldingsT.setOleDsItemT(this);

		return oleDsItemHoldingsT;
	}

	public OleDsItemHoldingsT removeOleDsItemHoldingsT(OleDsItemHoldingsT oleDsItemHoldingsT) {
		getOleDsItemHoldingsTs().remove(oleDsItemHoldingsT);
		oleDsItemHoldingsT.setOleDsItemT(null);

		return oleDsItemHoldingsT;
	}

	public List<OleDsItemNoteT> getOleDsItemNoteTs() {
		return this.oleDsItemNoteTs;
	}

	public void setOleDsItemNoteTs(List<OleDsItemNoteT> oleDsItemNoteTs) {
		this.oleDsItemNoteTs = oleDsItemNoteTs;
	}

	public OleDsItemNoteT addOleDsItemNoteT(OleDsItemNoteT oleDsItemNoteT) {
		getOleDsItemNoteTs().add(oleDsItemNoteT);
		oleDsItemNoteT.setOleDsItemT(this);

		return oleDsItemNoteT;
	}

	public OleDsItemNoteT removeOleDsItemNoteT(OleDsItemNoteT oleDsItemNoteT) {
		getOleDsItemNoteTs().remove(oleDsItemNoteT);
		oleDsItemNoteT.setOleDsItemT(null);

		return oleDsItemNoteT;
	}

	public List<OleDsItemStatSearchT> getOleDsItemStatSearchTs() {
		return this.oleDsItemStatSearchTs;
	}

	public void setOleDsItemStatSearchTs(List<OleDsItemStatSearchT> oleDsItemStatSearchTs) {
		this.oleDsItemStatSearchTs = oleDsItemStatSearchTs;
	}

	public OleDsItemStatSearchT addOleDsItemStatSearchT(OleDsItemStatSearchT oleDsItemStatSearchT) {
		getOleDsItemStatSearchTs().add(oleDsItemStatSearchT);
		oleDsItemStatSearchT.setOleDsItemT(this);

		return oleDsItemStatSearchT;
	}

	public OleDsItemStatSearchT removeOleDsItemStatSearchT(OleDsItemStatSearchT oleDsItemStatSearchT) {
		getOleDsItemStatSearchTs().remove(oleDsItemStatSearchT);
		oleDsItemStatSearchT.setOleDsItemT(null);

		return oleDsItemStatSearchT;
	}

	public OleDsHighDensityStorageT getOleDsHighDensityStorageT() {
		return this.oleDsHighDensityStorageT;
	}

	public void setOleDsHighDensityStorageT(OleDsHighDensityStorageT oleDsHighDensityStorageT) {
		this.oleDsHighDensityStorageT = oleDsHighDensityStorageT;
	}

	public OleDsHoldingsT getOleDsHoldingsT() {
		return this.oleDsHoldingsT;
	}

	public void setOleDsHoldingsT(OleDsHoldingsT oleDsHoldingsT) {
		this.oleDsHoldingsT = oleDsHoldingsT;
	}

	public List<OleDsItmFormerIdentifierT> getOleDsItmFormerIdentifierTs() {
		return this.oleDsItmFormerIdentifierTs;
	}

	public void setOleDsItmFormerIdentifierTs(List<OleDsItmFormerIdentifierT> oleDsItmFormerIdentifierTs) {
		this.oleDsItmFormerIdentifierTs = oleDsItmFormerIdentifierTs;
	}

	public OleDsItmFormerIdentifierT addOleDsItmFormerIdentifierT(OleDsItmFormerIdentifierT oleDsItmFormerIdentifierT) {
		getOleDsItmFormerIdentifierTs().add(oleDsItmFormerIdentifierT);
		oleDsItmFormerIdentifierT.setOleDsItemT(this);

		return oleDsItmFormerIdentifierT;
	}

	public OleDsItmFormerIdentifierT removeOleDsItmFormerIdentifierT(OleDsItmFormerIdentifierT oleDsItmFormerIdentifierT) {
		getOleDsItmFormerIdentifierTs().remove(oleDsItmFormerIdentifierT);
		oleDsItmFormerIdentifierT.setOleDsItemT(null);

		return oleDsItmFormerIdentifierT;
	}

	public List<OleDsLocCheckinCountT> getOleDsLocCheckinCountTs() {
		return this.oleDsLocCheckinCountTs;
	}

	public void setOleDsLocCheckinCountTs(List<OleDsLocCheckinCountT> oleDsLocCheckinCountTs) {
		this.oleDsLocCheckinCountTs = oleDsLocCheckinCountTs;
	}

	public OleCatShvlgSchmT getOleCatShvlgSchmT() {
		return oleCatShvlgSchmT;
	}

	public void setOleCatShvlgSchmT(OleCatShvlgSchmT oleCatShvlgSchmT) {
		this.oleCatShvlgSchmT = oleCatShvlgSchmT;
	}

	public OleDlvrItemAvailStatT getOleDlvrItemAvailStatT() {
		return oleDlvrItemAvailStatT;
	}

	public void setOleDlvrItemAvailStatT(OleDlvrItemAvailStatT oleDlvrItemAvailStatT) {
		this.oleDlvrItemAvailStatT = oleDlvrItemAvailStatT;
	}

	public OleCatItmTypT getItemTypeRecord() {
		return itemTypeRecord;
	}

	public void setItemTypeRecord(OleCatItmTypT itemTypeRecord) {
		this.itemTypeRecord = itemTypeRecord;
	}

	public OleCatItmTypT getTempItemTypeRecord() {
		return tempItemTypeRecord;
	}

	public void setTempItemTypeRecord(OleCatItmTypT tempItemTypeRecord) {
		this.tempItemTypeRecord = tempItemTypeRecord;
	}

	public OleDsLocCheckinCountT addOleDsLocCheckinCountT(OleDsLocCheckinCountT oleDsLocCheckinCountT) {
		getOleDsLocCheckinCountTs().add(oleDsLocCheckinCountT);
		oleDsLocCheckinCountT.setOleDsItemT(this);

		return oleDsLocCheckinCountT;
	}

	public OleDsLocCheckinCountT removeOleDsLocCheckinCountT(OleDsLocCheckinCountT oleDsLocCheckinCountT) {
		getOleDsLocCheckinCountTs().remove(oleDsLocCheckinCountT);
		oleDsLocCheckinCountT.setOleDsItemT(null);

		return oleDsLocCheckinCountT;
	}

}