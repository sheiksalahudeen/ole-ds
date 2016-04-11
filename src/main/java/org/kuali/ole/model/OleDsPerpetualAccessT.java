package org.kuali.ole.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ole_ds_perpetual_access_t database table.
 * 
 */
@Entity
@Table(name="ole_ds_perpetual_access_t")
public class OleDsPerpetualAccessT implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="HOLDINGS_PERPETUAL_ACCESS_ID")
	private Integer holdingsPerpetualAccessId;

	@Column(name="PERPETUAL_ACCESS_END_DATE")
	private String perpetualAccessEndDate;

	@Column(name="PERPETUAL_ACCESS_END_ISSUE")
	private String perpetualAccessEndIssue;

	@Column(name="PERPETUAL_ACCESS_END_VOLUME")
	private String perpetualAccessEndVolume;

	@Column(name="PERPETUAL_ACCESS_START_DATE")
	private String perpetualAccessStartDate;

	@Column(name="PERPETUAL_ACCESS_START_ISSUE")
	private String perpetualAccessStartIssue;

	@Column(name="PERPETUAL_ACCESS_START_VOLUME")
	private String perpetualAccessStartVolume;

	//bi-directional many-to-one association to OleDsHoldingsT
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="HOLDINGS_ID")
	private OleDsHoldingsT oleDsHoldingsT;

	public OleDsPerpetualAccessT() {
	}

	public Integer getHoldingsPerpetualAccessId() {
		return this.holdingsPerpetualAccessId;
	}

	public void setHoldingsPerpetualAccessId(Integer holdingsPerpetualAccessId) {
		this.holdingsPerpetualAccessId = holdingsPerpetualAccessId;
	}

	public String getPerpetualAccessEndDate() {
		return this.perpetualAccessEndDate;
	}

	public void setPerpetualAccessEndDate(String perpetualAccessEndDate) {
		this.perpetualAccessEndDate = perpetualAccessEndDate;
	}

	public String getPerpetualAccessEndIssue() {
		return this.perpetualAccessEndIssue;
	}

	public void setPerpetualAccessEndIssue(String perpetualAccessEndIssue) {
		this.perpetualAccessEndIssue = perpetualAccessEndIssue;
	}

	public String getPerpetualAccessEndVolume() {
		return this.perpetualAccessEndVolume;
	}

	public void setPerpetualAccessEndVolume(String perpetualAccessEndVolume) {
		this.perpetualAccessEndVolume = perpetualAccessEndVolume;
	}

	public String getPerpetualAccessStartDate() {
		return this.perpetualAccessStartDate;
	}

	public void setPerpetualAccessStartDate(String perpetualAccessStartDate) {
		this.perpetualAccessStartDate = perpetualAccessStartDate;
	}

	public String getPerpetualAccessStartIssue() {
		return this.perpetualAccessStartIssue;
	}

	public void setPerpetualAccessStartIssue(String perpetualAccessStartIssue) {
		this.perpetualAccessStartIssue = perpetualAccessStartIssue;
	}

	public String getPerpetualAccessStartVolume() {
		return this.perpetualAccessStartVolume;
	}

	public void setPerpetualAccessStartVolume(String perpetualAccessStartVolume) {
		this.perpetualAccessStartVolume = perpetualAccessStartVolume;
	}

	public OleDsHoldingsT getOleDsHoldingsT() {
		return this.oleDsHoldingsT;
	}

	public void setOleDsHoldingsT(OleDsHoldingsT oleDsHoldingsT) {
		this.oleDsHoldingsT = oleDsHoldingsT;
	}

}