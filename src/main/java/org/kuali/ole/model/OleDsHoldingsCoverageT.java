package org.kuali.ole.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ole_ds_holdings_coverage_t database table.
 * 
 */
@Entity
@Table(name="ole_ds_holdings_coverage_t")
public class OleDsHoldingsCoverageT implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="HOLDINGS_COVERAGE_ID")
	private Integer holdingsCoverageId;

	@Column(name="COVERAGE_END_DATE")
	private String coverageEndDate;

	@Column(name="COVERAGE_END_ISSUE")
	private String coverageEndIssue;

	@Column(name="COVERAGE_END_VOLUME")
	private String coverageEndVolume;

	@Column(name="COVERAGE_START_DATE")
	private String coverageStartDate;

	@Column(name="COVERAGE_START_ISSUE")
	private String coverageStartIssue;

	@Column(name="COVERAGE_START_VOLUME")
	private String coverageStartVolume;

	//bi-directional many-to-one association to OleDsHoldingsT
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="HOLDINGS_ID")
	private OleDsHoldingsT oleDsHoldingsT;

	public OleDsHoldingsCoverageT() {
	}

	public Integer getHoldingsCoverageId() {
		return this.holdingsCoverageId;
	}

	public void setHoldingsCoverageId(Integer holdingsCoverageId) {
		this.holdingsCoverageId = holdingsCoverageId;
	}

	public String getCoverageEndDate() {
		return this.coverageEndDate;
	}

	public void setCoverageEndDate(String coverageEndDate) {
		this.coverageEndDate = coverageEndDate;
	}

	public String getCoverageEndIssue() {
		return this.coverageEndIssue;
	}

	public void setCoverageEndIssue(String coverageEndIssue) {
		this.coverageEndIssue = coverageEndIssue;
	}

	public String getCoverageEndVolume() {
		return this.coverageEndVolume;
	}

	public void setCoverageEndVolume(String coverageEndVolume) {
		this.coverageEndVolume = coverageEndVolume;
	}

	public String getCoverageStartDate() {
		return this.coverageStartDate;
	}

	public void setCoverageStartDate(String coverageStartDate) {
		this.coverageStartDate = coverageStartDate;
	}

	public String getCoverageStartIssue() {
		return this.coverageStartIssue;
	}

	public void setCoverageStartIssue(String coverageStartIssue) {
		this.coverageStartIssue = coverageStartIssue;
	}

	public String getCoverageStartVolume() {
		return this.coverageStartVolume;
	}

	public void setCoverageStartVolume(String coverageStartVolume) {
		this.coverageStartVolume = coverageStartVolume;
	}

	public OleDsHoldingsT getOleDsHoldingsT() {
		return this.oleDsHoldingsT;
	}

	public void setOleDsHoldingsT(OleDsHoldingsT oleDsHoldingsT) {
		this.oleDsHoldingsT = oleDsHoldingsT;
	}

}