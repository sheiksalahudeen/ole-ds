package org.kuali.ole.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ole_ds_item_donor_t database table.
 * 
 */
@Entity
@Table(name="ole_ds_item_donor_t")
public class OleDsItemDonorT implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ITEM_DONOR_ID")
	private Integer itemDonorId;

	@Column(name="DONOR_CODE")
	private String donorCode;

	@Column(name="donor_display_note")
	private String donorDisplayNote;

	@Column(name="donor_note")
	private String donorNote;

	//bi-directional many-to-one association to OleDsItemT
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ITEM_ID")
	private OleDsItemT oleDsItemT;

	public OleDsItemDonorT() {
	}

	public Integer getItemDonorId() {
		return this.itemDonorId;
	}

	public void setItemDonorId(Integer itemDonorId) {
		this.itemDonorId = itemDonorId;
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

	public OleDsItemT getOleDsItemT() {
		return this.oleDsItemT;
	}

	public void setOleDsItemT(OleDsItemT oleDsItemT) {
		this.oleDsItemT = oleDsItemT;
	}

}