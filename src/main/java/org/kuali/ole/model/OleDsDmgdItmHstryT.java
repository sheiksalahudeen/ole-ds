package org.kuali.ole.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the ole_ds_dmgd_itm_hstry_t database table.
 * 
 */
@Entity
@Table(name="ole_ds_dmgd_itm_hstry_t")
public class OleDsDmgdItmHstryT implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ITM_DMGD_ID")
	private Integer itmDmgdId;

	@Column(name="DMGD_ITM_DATE")
	private Timestamp dmgdItmDate;

	@Column(name="DMGD_ITM_NOTE")
	private String dmgdItmNote;

	@Column(name="DMGD_PATRON_ID")
	private String dmgdPatronId;

	@Column(name="OPERATOR_ID")
	private String operatorId;

	@Column(name="PATRON_BARCODE")
	private String patronBarcode;

	//bi-directional many-to-one association to OleDsItemT
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ITEM_ID")
	private OleDsItemT oleDsItemT;

	public OleDsDmgdItmHstryT() {
	}

	public Integer getItmDmgdId() {
		return this.itmDmgdId;
	}

	public void setItmDmgdId(Integer itmDmgdId) {
		this.itmDmgdId = itmDmgdId;
	}

	public Timestamp getDmgdItmDate() {
		return this.dmgdItmDate;
	}

	public void setDmgdItmDate(Timestamp dmgdItmDate) {
		this.dmgdItmDate = dmgdItmDate;
	}

	public String getDmgdItmNote() {
		return this.dmgdItmNote;
	}

	public void setDmgdItmNote(String dmgdItmNote) {
		this.dmgdItmNote = dmgdItmNote;
	}

	public String getDmgdPatronId() {
		return this.dmgdPatronId;
	}

	public void setDmgdPatronId(String dmgdPatronId) {
		this.dmgdPatronId = dmgdPatronId;
	}

	public String getOperatorId() {
		return this.operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getPatronBarcode() {
		return this.patronBarcode;
	}

	public void setPatronBarcode(String patronBarcode) {
		this.patronBarcode = patronBarcode;
	}

	public OleDsItemT getOleDsItemT() {
		return this.oleDsItemT;
	}

	public void setOleDsItemT(OleDsItemT oleDsItemT) {
		this.oleDsItemT = oleDsItemT;
	}

}