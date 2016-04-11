package org.kuali.ole.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ole_ds_ext_ownership_note_t database table.
 * 
 */
@Entity
@Table(name="ole_ds_ext_ownership_note_t")
public class OleDsExtOwnershipNoteT implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="EXT_OWNERSHIP_NOTE_ID")
	private Integer extOwnershipNoteId;

	private String note;

	private String type;

	//bi-directional many-to-one association to OleDsExtOwnershipT
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="EXT_OWNERSHIP_ID")
	private OleDsExtOwnershipT oleDsExtOwnershipT;

	public OleDsExtOwnershipNoteT() {
	}

	public Integer getExtOwnershipNoteId() {
		return this.extOwnershipNoteId;
	}

	public void setExtOwnershipNoteId(Integer extOwnershipNoteId) {
		this.extOwnershipNoteId = extOwnershipNoteId;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public OleDsExtOwnershipT getOleDsExtOwnershipT() {
		return this.oleDsExtOwnershipT;
	}

	public void setOleDsExtOwnershipT(OleDsExtOwnershipT oleDsExtOwnershipT) {
		this.oleDsExtOwnershipT = oleDsExtOwnershipT;
	}

}