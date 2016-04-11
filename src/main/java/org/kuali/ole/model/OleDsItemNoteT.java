package org.kuali.ole.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ole_ds_item_note_t database table.
 * 
 */
@Entity
@Table(name="ole_ds_item_note_t")
public class OleDsItemNoteT implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ITEM_NOTE_ID")
	private Integer itemNoteId;

	private String note;

	private String type;

	//bi-directional many-to-one association to OleDsItemT
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ITEM_ID")
	private OleDsItemT oleDsItemT;

	public OleDsItemNoteT() {
	}

	public Integer getItemNoteId() {
		return this.itemNoteId;
	}

	public void setItemNoteId(Integer itemNoteId) {
		this.itemNoteId = itemNoteId;
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

	public OleDsItemT getOleDsItemT() {
		return this.oleDsItemT;
	}

	public void setOleDsItemT(OleDsItemT oleDsItemT) {
		this.oleDsItemT = oleDsItemT;
	}

}