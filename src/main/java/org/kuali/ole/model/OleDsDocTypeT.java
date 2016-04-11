package org.kuali.ole.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the ole_ds_doc_type_t database table.
 * 
 */
@Entity
@Table(name="ole_ds_doc_type_t")
public class OleDsDocTypeT implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="DOC_TYPE_ID")
	private Integer docTypeId;

	private String description;

	@Column(name="DISPLAY_LABEL")
	private String displayLabel;

	private String name;

	@Column(name="OBJ_ID")
	private String objId;

	@Column(name="VER_NBR")
	private BigDecimal verNbr;

	//bi-directional many-to-one association to OleDsDocFieldT
	@OneToMany(mappedBy="oleDsDocTypeT")
	private List<OleDsDocFieldT> oleDsDocFieldTs;

	//bi-directional many-to-one association to OleDsDocFormatT
	@OneToMany(mappedBy="oleDsDocTypeT")
	private List<OleDsDocFormatT> oleDsDocFormatTs;

	public OleDsDocTypeT() {
	}

	public Integer getDocTypeId() {
		return this.docTypeId;
	}

	public void setDocTypeId(Integer docTypeId) {
		this.docTypeId = docTypeId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDisplayLabel() {
		return this.displayLabel;
	}

	public void setDisplayLabel(String displayLabel) {
		this.displayLabel = displayLabel;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getObjId() {
		return this.objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public BigDecimal getVerNbr() {
		return this.verNbr;
	}

	public void setVerNbr(BigDecimal verNbr) {
		this.verNbr = verNbr;
	}

	public List<OleDsDocFieldT> getOleDsDocFieldTs() {
		return this.oleDsDocFieldTs;
	}

	public void setOleDsDocFieldTs(List<OleDsDocFieldT> oleDsDocFieldTs) {
		this.oleDsDocFieldTs = oleDsDocFieldTs;
	}

	public OleDsDocFieldT addOleDsDocFieldT(OleDsDocFieldT oleDsDocFieldT) {
		getOleDsDocFieldTs().add(oleDsDocFieldT);
		oleDsDocFieldT.setOleDsDocTypeT(this);

		return oleDsDocFieldT;
	}

	public OleDsDocFieldT removeOleDsDocFieldT(OleDsDocFieldT oleDsDocFieldT) {
		getOleDsDocFieldTs().remove(oleDsDocFieldT);
		oleDsDocFieldT.setOleDsDocTypeT(null);

		return oleDsDocFieldT;
	}

	public List<OleDsDocFormatT> getOleDsDocFormatTs() {
		return this.oleDsDocFormatTs;
	}

	public void setOleDsDocFormatTs(List<OleDsDocFormatT> oleDsDocFormatTs) {
		this.oleDsDocFormatTs = oleDsDocFormatTs;
	}

	public OleDsDocFormatT addOleDsDocFormatT(OleDsDocFormatT oleDsDocFormatT) {
		getOleDsDocFormatTs().add(oleDsDocFormatT);
		oleDsDocFormatT.setOleDsDocTypeT(this);

		return oleDsDocFormatT;
	}

	public OleDsDocFormatT removeOleDsDocFormatT(OleDsDocFormatT oleDsDocFormatT) {
		getOleDsDocFormatTs().remove(oleDsDocFormatT);
		oleDsDocFormatT.setOleDsDocTypeT(null);

		return oleDsDocFormatT;
	}

}