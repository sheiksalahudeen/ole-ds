package org.kuali.ole.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the ole_ds_search_result_page_t database table.
 * 
 */
@Entity
@Table(name="ole_ds_search_result_page_t")
public class OleDsSearchResultPageT implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="DOC_SEARCH_PAGE_SIZE_ID")
	private Integer docSearchPageSizeId;

	@Column(name="OBJ_ID")
	private String objId;

	@Column(name="PAGE_SIZE")
	private Integer pageSize;

	@Column(name="VER_NBR")
	private BigDecimal verNbr;

	public OleDsSearchResultPageT() {
	}

	public Integer getDocSearchPageSizeId() {
		return this.docSearchPageSizeId;
	}

	public void setDocSearchPageSizeId(Integer docSearchPageSizeId) {
		this.docSearchPageSizeId = docSearchPageSizeId;
	}

	public String getObjId() {
		return this.objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public Integer getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public BigDecimal getVerNbr() {
		return this.verNbr;
	}

	public void setVerNbr(BigDecimal verNbr) {
		this.verNbr = verNbr;
	}

}