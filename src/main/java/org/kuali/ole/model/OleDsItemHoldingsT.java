package org.kuali.ole.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ole_ds_item_holdings_t database table.
 * 
 */
@Entity
@Table(name="ole_ds_item_holdings_t")
public class OleDsItemHoldingsT implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ITEM_HOLDINGS_ID")
	private Integer itemHoldingsId;

	//bi-directional many-to-one association to OleDsHoldingsT
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="HOLDINGS_ID")
	private OleDsHoldingsT oleDsHoldingsT;

	//bi-directional many-to-one association to OleDsItemT
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ITEM_ID")
	private OleDsItemT oleDsItemT;

	public OleDsItemHoldingsT() {
	}

	public Integer getItemHoldingsId() {
		return this.itemHoldingsId;
	}

	public void setItemHoldingsId(Integer itemHoldingsId) {
		this.itemHoldingsId = itemHoldingsId;
	}

	public OleDsHoldingsT getOleDsHoldingsT() {
		return this.oleDsHoldingsT;
	}

	public void setOleDsHoldingsT(OleDsHoldingsT oleDsHoldingsT) {
		this.oleDsHoldingsT = oleDsHoldingsT;
	}

	public OleDsItemT getOleDsItemT() {
		return this.oleDsItemT;
	}

	public void setOleDsItemT(OleDsItemT oleDsItemT) {
		this.oleDsItemT = oleDsItemT;
	}

}