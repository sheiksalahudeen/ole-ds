package org.kuali.ole.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ole_ds_bib_holdings_t database table.
 * 
 */
@Entity
@Table(name="ole_ds_bib_holdings_t")
public class OleDsBibHoldingsT implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="BIB_HOLDINGS_ID")
	private Integer bibHoldingsId;

	//bi-directional many-to-one association to OleDsHoldingsT
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="HOLDINGS_ID")
	private OleDsHoldingsT oleDsHoldingsT;

	//bi-directional many-to-one association to OleDsBibT
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="BIB_ID")
	private OleDsBibT oleDsBibT;

	public OleDsBibHoldingsT() {
	}

	public Integer getBibHoldingsId() {
		return this.bibHoldingsId;
	}

	public void setBibHoldingsId(Integer bibHoldingsId) {
		this.bibHoldingsId = bibHoldingsId;
	}

	public OleDsHoldingsT getOleDsHoldingsT() {
		return this.oleDsHoldingsT;
	}

	public void setOleDsHoldingsT(OleDsHoldingsT oleDsHoldingsT) {
		this.oleDsHoldingsT = oleDsHoldingsT;
	}

	public OleDsBibT getOleDsBibT() {
		return this.oleDsBibT;
	}

	public void setOleDsBibT(OleDsBibT oleDsBibT) {
		this.oleDsBibT = oleDsBibT;
	}

}