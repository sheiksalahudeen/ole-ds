package org.kuali.ole.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the ole_ds_doc_format_t database table.
 * 
 */
@Entity
@Table(name="ole_ds_doc_format_t")
public class OleDsDocFormatT implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="DOC_FORMAT_ID")
	private Integer docFormatId;

	private String description;

	@Column(name="DISPLAY_LABEL")
	private String displayLabel;

	private String name;

	@Column(name="OBJ_ID")
	private String objId;

	@Column(name="VER_NBR")
	private BigDecimal verNbr;

	//bi-directional many-to-one association to OleDsDocFieldT
	@OneToMany(mappedBy="oleDsDocFormatT")
	private List<OleDsDocFieldT> oleDsDocFieldTs;

	//bi-directional many-to-one association to OleDsDocTypeT
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="DOC_TYPE_ID")
	private OleDsDocTypeT oleDsDocTypeT;

	public OleDsDocFormatT() {
	}

	public Integer getDocFormatId() {
		return this.docFormatId;
	}

	public void setDocFormatId(Integer docFormatId) {
		this.docFormatId = docFormatId;
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
		oleDsDocFieldT.setOleDsDocFormatT(this);

		return oleDsDocFieldT;
	}

	public OleDsDocFieldT removeOleDsDocFieldT(OleDsDocFieldT oleDsDocFieldT) {
		getOleDsDocFieldTs().remove(oleDsDocFieldT);
		oleDsDocFieldT.setOleDsDocFormatT(null);

		return oleDsDocFieldT;
	}

	public OleDsDocTypeT getOleDsDocTypeT() {
		return this.oleDsDocTypeT;
	}

	public void setOleDsDocTypeT(OleDsDocTypeT oleDsDocTypeT) {
		this.oleDsDocTypeT = oleDsDocTypeT;
	}

}