package org.kuali.ole.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the ole_ds_doc_field_t database table.
 * 
 */
@Entity
@Table(name="ole_ds_doc_field_t")
public class OleDsDocFieldT implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="DOC_FIELD_ID")
	private Integer docFieldId;

	private String description;

	@Column(name="DISPLAY_LABEL")
	private String displayLabel;

	@Column(name="EXCLUDE_PATH")
	private String excludePath;

	@Column(name="INCLUDE_PATH")
	private String includePath;

	@Column(name="IS_DISPLAY")
	private String isDisplay;

	@Column(name="IS_EXPORT")
	private String isExport;

	@Column(name="IS_FACET")
	private String isFacet;

	@Column(name="IS_GLOBAL_EDIT")
	private String isGlobalEdit;

	@Column(name="IS_SEARCH")
	private String isSearch;

	private String name;

	@Column(name="OBJ_ID")
	private String objId;

	@Column(name="VER_NBR")
	private BigDecimal verNbr;

	//bi-directional many-to-one association to OleDsDocFormatT
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="DOC_FORMAT_ID")
	private OleDsDocFormatT oleDsDocFormatT;

	//bi-directional many-to-one association to OleDsDocTypeT
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="DOC_TYPE_ID")
	private OleDsDocTypeT oleDsDocTypeT;

	public OleDsDocFieldT() {
	}

	public Integer getDocFieldId() {
		return this.docFieldId;
	}

	public void setDocFieldId(Integer docFieldId) {
		this.docFieldId = docFieldId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDisplayLabel() {
		return this.displayLabel;
	}

	public void setDisplayLabel(String displayLabel) {
		this.displayLabel = displayLabel;
	}

	public String getExcludePath() {
		return this.excludePath;
	}

	public void setExcludePath(String excludePath) {
		this.excludePath = excludePath;
	}

	public String getIncludePath() {
		return this.includePath;
	}

	public void setIncludePath(String includePath) {
		this.includePath = includePath;
	}

	public String getIsDisplay() {
		return this.isDisplay;
	}

	public void setIsDisplay(String isDisplay) {
		this.isDisplay = isDisplay;
	}

	public String getIsExport() {
		return this.isExport;
	}

	public void setIsExport(String isExport) {
		this.isExport = isExport;
	}

	public String getIsFacet() {
		return this.isFacet;
	}

	public void setIsFacet(String isFacet) {
		this.isFacet = isFacet;
	}

	public String getIsGlobalEdit() {
		return this.isGlobalEdit;
	}

	public void setIsGlobalEdit(String isGlobalEdit) {
		this.isGlobalEdit = isGlobalEdit;
	}

	public String getIsSearch() {
		return this.isSearch;
	}

	public void setIsSearch(String isSearch) {
		this.isSearch = isSearch;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getObjId() {
		return this.objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public BigDecimal getVerNbr() {
		return this.verNbr;
	}

	public void setVerNbr(BigDecimal verNbr) {
		this.verNbr = verNbr;
	}

	public OleDsDocFormatT getOleDsDocFormatT() {
		return this.oleDsDocFormatT;
	}

	public void setOleDsDocFormatT(OleDsDocFormatT oleDsDocFormatT) {
		this.oleDsDocFormatT = oleDsDocFormatT;
	}

	public OleDsDocTypeT getOleDsDocTypeT() {
		return this.oleDsDocTypeT;
	}

	public void setOleDsDocTypeT(OleDsDocTypeT oleDsDocTypeT) {
		this.oleDsDocTypeT = oleDsDocTypeT;
	}

}