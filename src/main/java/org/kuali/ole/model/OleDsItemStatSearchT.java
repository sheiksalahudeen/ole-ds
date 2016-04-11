package org.kuali.ole.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ole_ds_item_stat_search_t database table.
 * 
 */
@Entity
@Table(name="ole_ds_item_stat_search_t")
public class OleDsItemStatSearchT implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ITEM_STAT_SEARCH_ID")
	private Integer itemStatSearchId;

	@Column(name="STAT_SEARCH_CODE_ID")
	private Integer statSearchCodeId;

	//bi-directional many-to-one association to OleDsItemT
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ITEM_ID")
	private OleDsItemT oleDsItemT;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="STAT_SEARCH_CODE_ID", insertable=false ,updatable=false)
	private OleCatStatSrchCdT oleCatStatSrchCdT;

	public OleDsItemStatSearchT() {
	}

	public Integer getItemStatSearchId() {
		return this.itemStatSearchId;
	}

	public void setItemStatSearchId(Integer itemStatSearchId) {
		this.itemStatSearchId = itemStatSearchId;
	}

	public Integer getStatSearchCodeId() {
		return this.statSearchCodeId;
	}

	public void setStatSearchCodeId(Integer statSearchCodeId) {
		this.statSearchCodeId = statSearchCodeId;
	}

	public OleDsItemT getOleDsItemT() {
		return this.oleDsItemT;
	}

	public void setOleDsItemT(OleDsItemT oleDsItemT) {
		this.oleDsItemT = oleDsItemT;
	}

	public OleCatStatSrchCdT getOleCatStatSrchCdT() {
		return oleCatStatSrchCdT;
	}

	public void setOleCatStatSrchCdT(OleCatStatSrchCdT oleCatStatSrchCdT) {
		this.oleCatStatSrchCdT = oleCatStatSrchCdT;
	}
}