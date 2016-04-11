package org.kuali.ole.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ole_ds_holdings_donor_t database table.
 * 
 */
@Entity
@Table(name="ole_ds_holdings_donor_t")
public class OleDsHoldingsDonorT implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="HOLDINGS_DONOR_ID")
	private Integer holdingsDonorId;

	@Column(name="DONOR_CODE")
	private String donorCode;

	@Column(name="DONOR_DISPLAY_NOTE")
	private String donorDisplayNote;

	@Column(name="DONOR_NOTE")
	private String donorNote;

	//bi-directional many-to-one association to OleDsHoldingsT
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="HOLDINGS_ID")
	private OleDsHoldingsT oleDsHoldingsT;

	public OleDsHoldingsDonorT() {
	}

	public Integer getHoldingsDonorId() {
		return this.holdingsDonorId;
	}

	public void setHoldingsDonorId(Integer holdingsDonorId) {
		this.holdingsDonorId = holdingsDonorId;
	}

	public String getDonorCode() {
		return this.donorCode;
	}

	public void setDonorCode(String donorCode) {
		this.donorCode = donorCode;
	}

	public String getDonorDisplayNote() {
		return this.donorDisplayNote;
	}

	public void setDonorDisplayNote(String donorDisplayNote) {
		this.donorDisplayNote = donorDisplayNote;
	}

	public String getDonorNote() {
		return this.donorNote;
	}

	public void setDonorNote(String donorNote) {
		this.donorNote = donorNote;
	}

	public OleDsHoldingsT getOleDsHoldingsT() {
		return this.oleDsHoldingsT;
	}

	public void setOleDsHoldingsT(OleDsHoldingsT oleDsHoldingsT) {
		this.oleDsHoldingsT = oleDsHoldingsT;
	}

}