package org.kuali.ole.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ole_ds_itm_former_identifier_t database table.
 * 
 */
@Entity
@Table(name="ole_ds_itm_former_identifier_t")
public class OleDsItmFormerIdentifierT implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ITEM_FORMER_IDENTIFIER_ID")
	private Integer itemFormerIdentifierId;

	private String type;

	private String value;

	//bi-directional many-to-one association to OleDsItemT
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ITEM_ID")
	private OleDsItemT oleDsItemT;

	public OleDsItmFormerIdentifierT() {
	}

	public Integer getItemFormerIdentifierId() {
		return this.itemFormerIdentifierId;
	}

	public void setItemFormerIdentifierId(Integer itemFormerIdentifierId) {
		this.itemFormerIdentifierId = itemFormerIdentifierId;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public OleDsItemT getOleDsItemT() {
		return this.oleDsItemT;
	}

	public void setOleDsItemT(OleDsItemT oleDsItemT) {
		this.oleDsItemT = oleDsItemT;
	}

}