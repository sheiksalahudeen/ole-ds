package org.kuali.ole.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the ole_ds_search_facet_size_t database table.
 * 
 */
@Entity
@Table(name="ole_ds_search_facet_size_t")
public class OleDsSearchFacetSizeT implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="DOC_SEARCH_FACET_SIZE_ID")
	private Integer docSearchFacetSizeId;

	@Column(name="LONG_SIZE")
	private Integer longSize;

	@Column(name="OBJ_ID")
	private String objId;

	@Column(name="SHORT_SIZE")
	private Integer shortSize;

	@Column(name="VER_NBR")
	private BigDecimal verNbr;

	public OleDsSearchFacetSizeT() {
	}

	public Integer getDocSearchFacetSizeId() {
		return this.docSearchFacetSizeId;
	}

	public void setDocSearchFacetSizeId(Integer docSearchFacetSizeId) {
		this.docSearchFacetSizeId = docSearchFacetSizeId;
	}

	public Integer getLongSize() {
		return this.longSize;
	}

	public void setLongSize(Integer longSize) {
		this.longSize = longSize;
	}

	public String getObjId() {
		return this.objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public Integer getShortSize() {
		return this.shortSize;
	}

	public void setShortSize(Integer shortSize) {
		this.shortSize = shortSize;
	}

	public BigDecimal getVerNbr() {
		return this.verNbr;
	}

	public void setVerNbr(BigDecimal verNbr) {
		this.verNbr = verNbr;
	}

}