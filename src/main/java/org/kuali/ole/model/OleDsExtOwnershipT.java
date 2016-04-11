package org.kuali.ole.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ole_ds_ext_ownership_t database table.
 * 
 */
@Entity
@Table(name="ole_ds_ext_ownership_t")
public class OleDsExtOwnershipT implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="EXT_OWNERSHIP_ID")
	private Integer extOwnershipId;

	@Column(name="EXT_OWNERSHIP_TYPE_ID")
	private Integer extOwnershipTypeId;

	private Integer ord;

	private String text;

	//bi-directional many-to-one association to OleDsExtOwnershipNoteT
	@OneToMany(mappedBy="oleDsExtOwnershipT")
	private List<OleDsExtOwnershipNoteT> oleDsExtOwnershipNoteTs;

	//bi-directional many-to-one association to OleDsHoldingsT
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="HOLDINGS_ID")
	private OleDsHoldingsT oleDsHoldingsT;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="EXT_OWNERSHIP_TYPE_ID", insertable=false ,updatable=false)
	private OleCatTypeOwnershipT oleCatTypeOwnershipT;

	public OleDsExtOwnershipT() {
	}

	public Integer getExtOwnershipId() {
		return this.extOwnershipId;
	}

	public void setExtOwnershipId(Integer extOwnershipId) {
		this.extOwnershipId = extOwnershipId;
	}

	public Integer getExtOwnershipTypeId() {
		return this.extOwnershipTypeId;
	}

	public void setExtOwnershipTypeId(Integer extOwnershipTypeId) {
		this.extOwnershipTypeId = extOwnershipTypeId;
	}

	public Integer getOrd() {
		return this.ord;
	}

	public void setOrd(Integer ord) {
		this.ord = ord;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<OleDsExtOwnershipNoteT> getOleDsExtOwnershipNoteTs() {
		return this.oleDsExtOwnershipNoteTs;
	}

	public void setOleDsExtOwnershipNoteTs(List<OleDsExtOwnershipNoteT> oleDsExtOwnershipNoteTs) {
		this.oleDsExtOwnershipNoteTs = oleDsExtOwnershipNoteTs;
	}

	public OleDsExtOwnershipNoteT addOleDsExtOwnershipNoteT(OleDsExtOwnershipNoteT oleDsExtOwnershipNoteT) {
		getOleDsExtOwnershipNoteTs().add(oleDsExtOwnershipNoteT);
		oleDsExtOwnershipNoteT.setOleDsExtOwnershipT(this);

		return oleDsExtOwnershipNoteT;
	}

	public OleDsExtOwnershipNoteT removeOleDsExtOwnershipNoteT(OleDsExtOwnershipNoteT oleDsExtOwnershipNoteT) {
		getOleDsExtOwnershipNoteTs().remove(oleDsExtOwnershipNoteT);
		oleDsExtOwnershipNoteT.setOleDsExtOwnershipT(null);

		return oleDsExtOwnershipNoteT;
	}

	public OleDsHoldingsT getOleDsHoldingsT() {
		return this.oleDsHoldingsT;
	}

	public void setOleDsHoldingsT(OleDsHoldingsT oleDsHoldingsT) {
		this.oleDsHoldingsT = oleDsHoldingsT;
	}

	public OleCatTypeOwnershipT getOleCatTypeOwnershipT() {
		return oleCatTypeOwnershipT;
	}

	public void setOleCatTypeOwnershipT(OleCatTypeOwnershipT oleCatTypeOwnershipT) {
		this.oleCatTypeOwnershipT = oleCatTypeOwnershipT;
	}
}