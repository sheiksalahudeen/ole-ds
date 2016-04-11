package org.kuali.ole.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ole_ds_access_location_t database table.
 * 
 */
@Entity
@Table(name="ole_ds_access_location_t")
public class OleDsAccessLocationT implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ACCESS_LOCATION_ID")
	private Integer accessLocationId;

	//bi-directional many-to-one association to OleDsAccessLocationCodeT
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ACCESS_LOCATION_CODE_ID")
	private OleDsAccessLocationCodeT oleDsAccessLocationCodeT;

	//bi-directional many-to-one association to OleDsHoldingsT
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="HOLDINGS_ID")
	private OleDsHoldingsT oleDsHoldingsT;

	public OleDsAccessLocationT() {
	}

	public Integer getAccessLocationId() {
		return this.accessLocationId;
	}

	public void setAccessLocationId(Integer accessLocationId) {
		this.accessLocationId = accessLocationId;
	}

	public OleDsAccessLocationCodeT getOleDsAccessLocationCodeT() {
		return this.oleDsAccessLocationCodeT;
	}

	public void setOleDsAccessLocationCodeT(OleDsAccessLocationCodeT oleDsAccessLocationCodeT) {
		this.oleDsAccessLocationCodeT = oleDsAccessLocationCodeT;
	}

	public OleDsHoldingsT getOleDsHoldingsT() {
		return this.oleDsHoldingsT;
	}

	public void setOleDsHoldingsT(OleDsHoldingsT oleDsHoldingsT) {
		this.oleDsHoldingsT = oleDsHoldingsT;
	}

}