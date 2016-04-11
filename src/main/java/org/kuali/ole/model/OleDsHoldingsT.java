package org.kuali.ole.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ole_ds_holdings_t database table.
 * 
 */
@Entity
@Table(name="ole_ds_holdings_t")
public class OleDsHoldingsT implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="HOLDINGS_ID")
	private Integer holdingsId;

	@Column(name="ACCESS_PASSWORD")
	private String accessPassword;

	@Column(name="ACCESS_STATUS")
	private String accessStatus;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ACCESS_STATUS_DATE_UPDATED")
	private Date accessStatusDateUpdated;

	@Column(name="ACCESS_USERNAME")
	private String accessUsername;

	@Column(name="ADMIN_PASSWORD")
	private String adminPassword;

	@Column(name="ADMIN_URL")
	private String adminUrl;

	@Column(name="ADMIN_USERNAME")
	private String adminUsername;

	@Column(name="ALLOW_ILL")
	private String allowIll;

	@Column(name="AUTHENTICATION_TYPE_ID")
	private Integer authenticationTypeId;

	@Column(name="CALL_NUMBER")
	private String callNumber;

	@Column(name="CALL_NUMBER_PREFIX")
	private String callNumberPrefix;

	@Column(name="CALL_NUMBER_TYPE_ID")
	private Integer callNumberTypeId;

	@Column(name="CANCELLATION_DECISION_DT")
	private Timestamp cancellationDecisionDt;

	@Column(name="CANCELLATION_EFFECTIVE_DT")
	private Timestamp cancellationEffectiveDt;

	@Column(name="CANCELLATION_REASON")
	private String cancellationReason;

	@Column(name="COPY_NUMBER")
	private String copyNumber;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="CURRENT_SBRCPTN_END_DT")
	private Timestamp currentSbrcptnEndDt;

	@Column(name="CURRENT_SBRCPTN_START_DT")
	private Timestamp currentSbrcptnStartDt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_CREATED")
	private Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_UPDATED")
	private Date dateUpdated;

	@Column(name="E_RESOURCE_ID")
	private String eResourceId;

	@Column(name="FORMER_HOLDINGS_ID")
	private String formerHoldingsId;

	@Column(name="GOKB_IDENTIFIER")
	private Integer gokbIdentifier;

	@Column(name="HOLDINGS_TYPE")
	private String holdingsType;

	private String imprint;

	@Column(name="INITIAL_SBRCPTN_START_DT")
	private Timestamp initialSbrcptnStartDt;

	@Column(name="LOCAL_PERSISTENT_URI")
	private String localPersistentUri;

	private String location;

	@Column(name="LOCATION_ID")
	private Integer locationId;

	@Column(name="LOCATION_LEVEL")
	private String locationLevel;

	@Column(name="NUMBER_SIMULT_USERS")
	private Integer numberSimultUsers;

	private String platform;

	@Column(name="PROXIED_RESOURCE")
	private String proxiedResource;

	private String publisher;

	@Column(name="RECEIPT_STATUS_ID")
	private Integer receiptStatusId;

	@Column(name="SHELVING_ORDER")
	private String shelvingOrder;

	@Lob
	@Column(name="SOURCE_HOLDINGS_CONTENT")
	private String sourceHoldingsContent;

	@Column(name="STAFF_ONLY")
	private String staffOnly;

	@Column(name="SUBSCRIPTION_STATUS")
	private String subscriptionStatus;

	@Column(name="UNIQUE_ID_PREFIX")
	private String uniqueIdPrefix;

	@Column(name="UPDATED_BY")
	private String updatedBy;

	//bi-directional many-to-one association to OleDsAccessLocationT
	@OneToMany(mappedBy="oleDsHoldingsT")
	private List<OleDsAccessLocationT> oleDsAccessLocationTs;

	//bi-directional many-to-one association to OleDsBibHoldingsT
	@OneToMany(mappedBy="oleDsHoldingsT")
	private List<OleDsBibHoldingsT> oleDsBibHoldingsTs;

	//bi-directional many-to-one association to OleDsExtOwnershipT
	@OneToMany(mappedBy="oleDsHoldingsT")
	private List<OleDsExtOwnershipT> oleDsExtOwnershipTs;

	//bi-directional many-to-one association to OleDsHoldingsCoverageT
	@OneToMany(mappedBy="oleDsHoldingsT")
	private List<OleDsHoldingsCoverageT> oleDsHoldingsCoverageTs;

	//bi-directional many-to-one association to OleDsHoldingsDonorT
	@OneToMany(mappedBy="oleDsHoldingsT")
	private List<OleDsHoldingsDonorT> oleDsHoldingsDonorTs;

	//bi-directional many-to-one association to OleDsHoldingsNoteT
	@OneToMany(mappedBy="oleDsHoldingsT")
	private List<OleDsHoldingsNoteT> oleDsHoldingsNoteTs;

	//bi-directional many-to-one association to OleDsHoldingsStatSearchT
	@OneToMany(mappedBy="oleDsHoldingsT")
	private List<OleDsHoldingsStatSearchT> oleDsHoldingsStatSearchTs;

	//bi-directional many-to-one association to OleDsBibT
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="BIB_ID")
	private OleDsBibT oleDsBibT;

	//bi-directional many-to-one association to OleDsHoldingsUriT
	@OneToMany(mappedBy="oleDsHoldingsT")
	private List<OleDsHoldingsUriT> oleDsHoldingsUriTs;

	//bi-directional many-to-one association to OleDsItemHoldingsT
	@OneToMany(mappedBy="oleDsHoldingsT")
	private List<OleDsItemHoldingsT> oleDsItemHoldingsTs;

	//bi-directional many-to-one association to OleDsItemT
	@OneToMany(mappedBy="oleDsHoldingsT")
	private List<OleDsItemT> oleDsItemTs;

	//bi-directional many-to-one association to OleDsPerpetualAccessT
	@OneToMany(mappedBy="oleDsHoldingsT")
	private List<OleDsPerpetualAccessT> oleDsPerpetualAccessTs;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CALL_NUMBER_TYPE_ID", insertable=false ,updatable=false)
	private OleCatShvlgSchmT oleCatShvlgSchmT;


	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="AUTHENTICATION_TYPE_ID", insertable=false ,updatable=false)
	private OleDsAuthenticationTypeT OleDsAuthenticationTypeT;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="RECEIPT_STATUS_ID", insertable=false ,updatable=false)
	private OleCatRcptStatT oleCatRcptStatT;

	public OleDsHoldingsT() {
	}

	public Integer getHoldingsId() {
		return this.holdingsId;
	}

	public void setHoldingsId(Integer holdingsId) {
		this.holdingsId = holdingsId;
	}

	public String getAccessPassword() {
		return this.accessPassword;
	}

	public void setAccessPassword(String accessPassword) {
		this.accessPassword = accessPassword;
	}

	public String getAccessStatus() {
		return this.accessStatus;
	}

	public void setAccessStatus(String accessStatus) {
		this.accessStatus = accessStatus;
	}

	public Date getAccessStatusDateUpdated() {
		return this.accessStatusDateUpdated;
	}

	public void setAccessStatusDateUpdated(Date accessStatusDateUpdated) {
		this.accessStatusDateUpdated = accessStatusDateUpdated;
	}

	public String getAccessUsername() {
		return this.accessUsername;
	}

	public void setAccessUsername(String accessUsername) {
		this.accessUsername = accessUsername;
	}

	public String getAdminPassword() {
		return this.adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public String getAdminUrl() {
		return this.adminUrl;
	}

	public void setAdminUrl(String adminUrl) {
		this.adminUrl = adminUrl;
	}

	public String getAdminUsername() {
		return this.adminUsername;
	}

	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}

	public String getAllowIll() {
		return this.allowIll;
	}

	public void setAllowIll(String allowIll) {
		this.allowIll = allowIll;
	}

	public Integer getAuthenticationTypeId() {
		return this.authenticationTypeId;
	}

	public void setAuthenticationTypeId(Integer authenticationTypeId) {
		this.authenticationTypeId = authenticationTypeId;
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

	public Timestamp getCancellationDecisionDt() {
		return this.cancellationDecisionDt;
	}

	public void setCancellationDecisionDt(Timestamp cancellationDecisionDt) {
		this.cancellationDecisionDt = cancellationDecisionDt;
	}

	public Timestamp getCancellationEffectiveDt() {
		return this.cancellationEffectiveDt;
	}

	public void setCancellationEffectiveDt(Timestamp cancellationEffectiveDt) {
		this.cancellationEffectiveDt = cancellationEffectiveDt;
	}

	public String getCancellationReason() {
		return this.cancellationReason;
	}

	public void setCancellationReason(String cancellationReason) {
		this.cancellationReason = cancellationReason;
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

	public Timestamp getCurrentSbrcptnEndDt() {
		return this.currentSbrcptnEndDt;
	}

	public void setCurrentSbrcptnEndDt(Timestamp currentSbrcptnEndDt) {
		this.currentSbrcptnEndDt = currentSbrcptnEndDt;
	}

	public Timestamp getCurrentSbrcptnStartDt() {
		return this.currentSbrcptnStartDt;
	}

	public void setCurrentSbrcptnStartDt(Timestamp currentSbrcptnStartDt) {
		this.currentSbrcptnStartDt = currentSbrcptnStartDt;
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

	public String getEResourceId() {
		return this.eResourceId;
	}

	public void setEResourceId(String eResourceId) {
		this.eResourceId = eResourceId;
	}

	public String getFormerHoldingsId() {
		return this.formerHoldingsId;
	}

	public void setFormerHoldingsId(String formerHoldingsId) {
		this.formerHoldingsId = formerHoldingsId;
	}

	public Integer getGokbIdentifier() {
		return this.gokbIdentifier;
	}

	public void setGokbIdentifier(Integer gokbIdentifier) {
		this.gokbIdentifier = gokbIdentifier;
	}

	public String getHoldingsType() {
		return this.holdingsType;
	}

	public void setHoldingsType(String holdingsType) {
		this.holdingsType = holdingsType;
	}

	public String getImprint() {
		return this.imprint;
	}

	public void setImprint(String imprint) {
		this.imprint = imprint;
	}

	public Timestamp getInitialSbrcptnStartDt() {
		return this.initialSbrcptnStartDt;
	}

	public void setInitialSbrcptnStartDt(Timestamp initialSbrcptnStartDt) {
		this.initialSbrcptnStartDt = initialSbrcptnStartDt;
	}

	public String getLocalPersistentUri() {
		return this.localPersistentUri;
	}

	public void setLocalPersistentUri(String localPersistentUri) {
		this.localPersistentUri = localPersistentUri;
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

	public Integer getNumberSimultUsers() {
		return this.numberSimultUsers;
	}

	public void setNumberSimultUsers(Integer numberSimultUsers) {
		this.numberSimultUsers = numberSimultUsers;
	}

	public String getPlatform() {
		return this.platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getProxiedResource() {
		return this.proxiedResource;
	}

	public void setProxiedResource(String proxiedResource) {
		this.proxiedResource = proxiedResource;
	}

	public String getPublisher() {
		return this.publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Integer getReceiptStatusId() {
		return this.receiptStatusId;
	}

	public void setReceiptStatusId(Integer receiptStatusId) {
		this.receiptStatusId = receiptStatusId;
	}

	public String getShelvingOrder() {
		return this.shelvingOrder;
	}

	public void setShelvingOrder(String shelvingOrder) {
		this.shelvingOrder = shelvingOrder;
	}

	public String getSourceHoldingsContent() {
		return this.sourceHoldingsContent;
	}

	public void setSourceHoldingsContent(String sourceHoldingsContent) {
		this.sourceHoldingsContent = sourceHoldingsContent;
	}

	public String getStaffOnly() {
		return this.staffOnly;
	}

	public void setStaffOnly(String staffOnly) {
		this.staffOnly = staffOnly;
	}

	public String getSubscriptionStatus() {
		return this.subscriptionStatus;
	}

	public void setSubscriptionStatus(String subscriptionStatus) {
		this.subscriptionStatus = subscriptionStatus;
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

	public List<OleDsAccessLocationT> getOleDsAccessLocationTs() {
		return this.oleDsAccessLocationTs;
	}

	public void setOleDsAccessLocationTs(List<OleDsAccessLocationT> oleDsAccessLocationTs) {
		this.oleDsAccessLocationTs = oleDsAccessLocationTs;
	}

	public OleDsAccessLocationT addOleDsAccessLocationT(OleDsAccessLocationT oleDsAccessLocationT) {
		getOleDsAccessLocationTs().add(oleDsAccessLocationT);
		oleDsAccessLocationT.setOleDsHoldingsT(this);

		return oleDsAccessLocationT;
	}

	public OleDsAccessLocationT removeOleDsAccessLocationT(OleDsAccessLocationT oleDsAccessLocationT) {
		getOleDsAccessLocationTs().remove(oleDsAccessLocationT);
		oleDsAccessLocationT.setOleDsHoldingsT(null);

		return oleDsAccessLocationT;
	}

	public List<OleDsBibHoldingsT> getOleDsBibHoldingsTs() {
		return this.oleDsBibHoldingsTs;
	}

	public void setOleDsBibHoldingsTs(List<OleDsBibHoldingsT> oleDsBibHoldingsTs) {
		this.oleDsBibHoldingsTs = oleDsBibHoldingsTs;
	}

	public OleDsBibHoldingsT addOleDsBibHoldingsT(OleDsBibHoldingsT oleDsBibHoldingsT) {
		getOleDsBibHoldingsTs().add(oleDsBibHoldingsT);
		oleDsBibHoldingsT.setOleDsHoldingsT(this);

		return oleDsBibHoldingsT;
	}

	public OleDsBibHoldingsT removeOleDsBibHoldingsT(OleDsBibHoldingsT oleDsBibHoldingsT) {
		getOleDsBibHoldingsTs().remove(oleDsBibHoldingsT);
		oleDsBibHoldingsT.setOleDsHoldingsT(null);

		return oleDsBibHoldingsT;
	}

	public List<OleDsExtOwnershipT> getOleDsExtOwnershipTs() {
		return this.oleDsExtOwnershipTs;
	}

	public void setOleDsExtOwnershipTs(List<OleDsExtOwnershipT> oleDsExtOwnershipTs) {
		this.oleDsExtOwnershipTs = oleDsExtOwnershipTs;
	}

	public OleDsExtOwnershipT addOleDsExtOwnershipT(OleDsExtOwnershipT oleDsExtOwnershipT) {
		getOleDsExtOwnershipTs().add(oleDsExtOwnershipT);
		oleDsExtOwnershipT.setOleDsHoldingsT(this);

		return oleDsExtOwnershipT;
	}

	public OleDsExtOwnershipT removeOleDsExtOwnershipT(OleDsExtOwnershipT oleDsExtOwnershipT) {
		getOleDsExtOwnershipTs().remove(oleDsExtOwnershipT);
		oleDsExtOwnershipT.setOleDsHoldingsT(null);

		return oleDsExtOwnershipT;
	}

	public List<OleDsHoldingsCoverageT> getOleDsHoldingsCoverageTs() {
		return this.oleDsHoldingsCoverageTs;
	}

	public void setOleDsHoldingsCoverageTs(List<OleDsHoldingsCoverageT> oleDsHoldingsCoverageTs) {
		this.oleDsHoldingsCoverageTs = oleDsHoldingsCoverageTs;
	}

	public OleDsHoldingsCoverageT addOleDsHoldingsCoverageT(OleDsHoldingsCoverageT oleDsHoldingsCoverageT) {
		getOleDsHoldingsCoverageTs().add(oleDsHoldingsCoverageT);
		oleDsHoldingsCoverageT.setOleDsHoldingsT(this);

		return oleDsHoldingsCoverageT;
	}

	public OleDsHoldingsCoverageT removeOleDsHoldingsCoverageT(OleDsHoldingsCoverageT oleDsHoldingsCoverageT) {
		getOleDsHoldingsCoverageTs().remove(oleDsHoldingsCoverageT);
		oleDsHoldingsCoverageT.setOleDsHoldingsT(null);

		return oleDsHoldingsCoverageT;
	}

	public List<OleDsHoldingsDonorT> getOleDsHoldingsDonorTs() {
		return this.oleDsHoldingsDonorTs;
	}

	public void setOleDsHoldingsDonorTs(List<OleDsHoldingsDonorT> oleDsHoldingsDonorTs) {
		this.oleDsHoldingsDonorTs = oleDsHoldingsDonorTs;
	}

	public OleDsHoldingsDonorT addOleDsHoldingsDonorT(OleDsHoldingsDonorT oleDsHoldingsDonorT) {
		getOleDsHoldingsDonorTs().add(oleDsHoldingsDonorT);
		oleDsHoldingsDonorT.setOleDsHoldingsT(this);

		return oleDsHoldingsDonorT;
	}

	public OleDsHoldingsDonorT removeOleDsHoldingsDonorT(OleDsHoldingsDonorT oleDsHoldingsDonorT) {
		getOleDsHoldingsDonorTs().remove(oleDsHoldingsDonorT);
		oleDsHoldingsDonorT.setOleDsHoldingsT(null);

		return oleDsHoldingsDonorT;
	}

	public List<OleDsHoldingsNoteT> getOleDsHoldingsNoteTs() {
		return this.oleDsHoldingsNoteTs;
	}

	public void setOleDsHoldingsNoteTs(List<OleDsHoldingsNoteT> oleDsHoldingsNoteTs) {
		this.oleDsHoldingsNoteTs = oleDsHoldingsNoteTs;
	}

	public OleDsHoldingsNoteT addOleDsHoldingsNoteT(OleDsHoldingsNoteT oleDsHoldingsNoteT) {
		getOleDsHoldingsNoteTs().add(oleDsHoldingsNoteT);
		oleDsHoldingsNoteT.setOleDsHoldingsT(this);

		return oleDsHoldingsNoteT;
	}

	public OleDsHoldingsNoteT removeOleDsHoldingsNoteT(OleDsHoldingsNoteT oleDsHoldingsNoteT) {
		getOleDsHoldingsNoteTs().remove(oleDsHoldingsNoteT);
		oleDsHoldingsNoteT.setOleDsHoldingsT(null);

		return oleDsHoldingsNoteT;
	}

	public List<OleDsHoldingsStatSearchT> getOleDsHoldingsStatSearchTs() {
		return this.oleDsHoldingsStatSearchTs;
	}

	public void setOleDsHoldingsStatSearchTs(List<OleDsHoldingsStatSearchT> oleDsHoldingsStatSearchTs) {
		this.oleDsHoldingsStatSearchTs = oleDsHoldingsStatSearchTs;
	}

	public OleDsHoldingsStatSearchT addOleDsHoldingsStatSearchT(OleDsHoldingsStatSearchT oleDsHoldingsStatSearchT) {
		getOleDsHoldingsStatSearchTs().add(oleDsHoldingsStatSearchT);
		oleDsHoldingsStatSearchT.setOleDsHoldingsT(this);

		return oleDsHoldingsStatSearchT;
	}

	public OleDsHoldingsStatSearchT removeOleDsHoldingsStatSearchT(OleDsHoldingsStatSearchT oleDsHoldingsStatSearchT) {
		getOleDsHoldingsStatSearchTs().remove(oleDsHoldingsStatSearchT);
		oleDsHoldingsStatSearchT.setOleDsHoldingsT(null);

		return oleDsHoldingsStatSearchT;
	}

	public OleDsBibT getOleDsBibT() {
		return this.oleDsBibT;
	}

	public void setOleDsBibT(OleDsBibT oleDsBibT) {
		this.oleDsBibT = oleDsBibT;
	}

	public List<OleDsHoldingsUriT> getOleDsHoldingsUriTs() {
		return this.oleDsHoldingsUriTs;
	}

	public void setOleDsHoldingsUriTs(List<OleDsHoldingsUriT> oleDsHoldingsUriTs) {
		this.oleDsHoldingsUriTs = oleDsHoldingsUriTs;
	}

	public OleDsHoldingsUriT addOleDsHoldingsUriT(OleDsHoldingsUriT oleDsHoldingsUriT) {
		getOleDsHoldingsUriTs().add(oleDsHoldingsUriT);
		oleDsHoldingsUriT.setOleDsHoldingsT(this);

		return oleDsHoldingsUriT;
	}

	public OleDsHoldingsUriT removeOleDsHoldingsUriT(OleDsHoldingsUriT oleDsHoldingsUriT) {
		getOleDsHoldingsUriTs().remove(oleDsHoldingsUriT);
		oleDsHoldingsUriT.setOleDsHoldingsT(null);

		return oleDsHoldingsUriT;
	}

	public List<OleDsItemHoldingsT> getOleDsItemHoldingsTs() {
		return this.oleDsItemHoldingsTs;
	}

	public void setOleDsItemHoldingsTs(List<OleDsItemHoldingsT> oleDsItemHoldingsTs) {
		this.oleDsItemHoldingsTs = oleDsItemHoldingsTs;
	}

	public OleDsItemHoldingsT addOleDsItemHoldingsT(OleDsItemHoldingsT oleDsItemHoldingsT) {
		getOleDsItemHoldingsTs().add(oleDsItemHoldingsT);
		oleDsItemHoldingsT.setOleDsHoldingsT(this);

		return oleDsItemHoldingsT;
	}

	public OleDsItemHoldingsT removeOleDsItemHoldingsT(OleDsItemHoldingsT oleDsItemHoldingsT) {
		getOleDsItemHoldingsTs().remove(oleDsItemHoldingsT);
		oleDsItemHoldingsT.setOleDsHoldingsT(null);

		return oleDsItemHoldingsT;
	}

	public List<OleDsItemT> getOleDsItemTs() {
		return this.oleDsItemTs;
	}

	public void setOleDsItemTs(List<OleDsItemT> oleDsItemTs) {
		this.oleDsItemTs = oleDsItemTs;
	}

	public OleDsItemT addOleDsItemT(OleDsItemT oleDsItemT) {
		getOleDsItemTs().add(oleDsItemT);
		oleDsItemT.setOleDsHoldingsT(this);

		return oleDsItemT;
	}

	public OleDsItemT removeOleDsItemT(OleDsItemT oleDsItemT) {
		getOleDsItemTs().remove(oleDsItemT);
		oleDsItemT.setOleDsHoldingsT(null);

		return oleDsItemT;
	}

	public List<OleDsPerpetualAccessT> getOleDsPerpetualAccessTs() {
		return this.oleDsPerpetualAccessTs;
	}

	public void setOleDsPerpetualAccessTs(List<OleDsPerpetualAccessT> oleDsPerpetualAccessTs) {
		this.oleDsPerpetualAccessTs = oleDsPerpetualAccessTs;
	}

	public OleDsPerpetualAccessT addOleDsPerpetualAccessT(OleDsPerpetualAccessT oleDsPerpetualAccessT) {
		getOleDsPerpetualAccessTs().add(oleDsPerpetualAccessT);
		oleDsPerpetualAccessT.setOleDsHoldingsT(this);

		return oleDsPerpetualAccessT;
	}

	public OleDsPerpetualAccessT removeOleDsPerpetualAccessT(OleDsPerpetualAccessT oleDsPerpetualAccessT) {
		getOleDsPerpetualAccessTs().remove(oleDsPerpetualAccessT);
		oleDsPerpetualAccessT.setOleDsHoldingsT(null);

		return oleDsPerpetualAccessT;
	}

	public OleCatShvlgSchmT getOleCatShvlgSchmT() {
		return oleCatShvlgSchmT;
	}

	public void setOleCatShvlgSchmT(OleCatShvlgSchmT oleCatShvlgSchmT) {
		this.oleCatShvlgSchmT = oleCatShvlgSchmT;
	}

	public org.kuali.ole.model.OleDsAuthenticationTypeT getOleDsAuthenticationTypeT() {
		return OleDsAuthenticationTypeT;
	}

	public void setOleDsAuthenticationTypeT(org.kuali.ole.model.OleDsAuthenticationTypeT oleDsAuthenticationTypeT) {
		OleDsAuthenticationTypeT = oleDsAuthenticationTypeT;
	}

	public OleCatRcptStatT getOleCatRcptStatT() {
		return oleCatRcptStatT;
	}

	public void setOleCatRcptStatT(OleCatRcptStatT oleCatRcptStatT) {
		this.oleCatRcptStatT = oleCatRcptStatT;
	}
}