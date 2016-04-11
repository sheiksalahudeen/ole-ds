package org.kuali.ole.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ole_ds_holdings_stat_search_t database table.
 * 
 */
@Entity
@Table(name="ole_ds_holdings_stat_search_t")
public class OleDsHoldingsStatSearchT implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="HOLDINGS_STAT_SEARCH_ID")
	private Integer holdingsStatSearchId;

	@Column(name="STAT_SEARCH_CODE_ID")
	private Integer statSearchCodeId;

	//bi-directional many-to-one association to OleDsHoldingsT
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="HOLDINGS_ID")
	private OleDsHoldingsT oleDsHoldingsT;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="STAT_SEARCH_CODE_ID", insertable=false ,updatable=false)
	private OleCatStatSrchCdT oleCatStatSrchCdT;

	public OleDsHoldingsStatSearchT() {
	}

	public Integer getHoldingsStatSearchId() {
		return this.holdingsStatSearchId;
	}

	public void setHoldingsStatSearchId(Integer holdingsStatSearchId) {
		this.holdingsStatSearchId = holdingsStatSearchId;
	}

	public Integer getStatSearchCodeId() {
		return this.statSearchCodeId;
	}

	public void setStatSearchCodeId(Integer statSearchCodeId) {
		this.statSearchCodeId = statSearchCodeId;
	}

	public OleDsHoldingsT getOleDsHoldingsT() {
		return this.oleDsHoldingsT;
	}

	public void setOleDsHoldingsT(OleDsHoldingsT oleDsHoldingsT) {
		this.oleDsHoldingsT = oleDsHoldingsT;
	}

	public OleCatStatSrchCdT getOleCatStatSrchCdT() {
		return oleCatStatSrchCdT;
	}

	public void setOleCatStatSrchCdT(OleCatStatSrchCdT oleCatStatSrchCdT) {
		this.oleCatStatSrchCdT = oleCatStatSrchCdT;
	}
}