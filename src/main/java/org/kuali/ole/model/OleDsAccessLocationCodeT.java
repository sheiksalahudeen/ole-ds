package org.kuali.ole.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ole_ds_access_location_code_t database table.
 * 
 */
@Entity
@Table(name="ole_ds_access_location_code_t")
public class OleDsAccessLocationCodeT implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ACCESS_LOCATION_CODE_ID")
	private Integer accessLocationCodeId;

	private String code;

	private String name;

	//bi-directional many-to-one association to OleDsAccessLocationT
	@OneToMany(mappedBy="oleDsAccessLocationCodeT")
	private List<OleDsAccessLocationT> oleDsAccessLocationTs;

	public OleDsAccessLocationCodeT() {
	}

	public Integer getAccessLocationCodeId() {
		return this.accessLocationCodeId;
	}

	public void setAccessLocationCodeId(Integer accessLocationCodeId) {
		this.accessLocationCodeId = accessLocationCodeId;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<OleDsAccessLocationT> getOleDsAccessLocationTs() {
		return this.oleDsAccessLocationTs;
	}

	public void setOleDsAccessLocationTs(List<OleDsAccessLocationT> oleDsAccessLocationTs) {
		this.oleDsAccessLocationTs = oleDsAccessLocationTs;
	}

	public OleDsAccessLocationT addOleDsAccessLocationT(OleDsAccessLocationT oleDsAccessLocationT) {
		getOleDsAccessLocationTs().add(oleDsAccessLocationT);
		oleDsAccessLocationT.setOleDsAccessLocationCodeT(this);

		return oleDsAccessLocationT;
	}

	public OleDsAccessLocationT removeOleDsAccessLocationT(OleDsAccessLocationT oleDsAccessLocationT) {
		getOleDsAccessLocationTs().remove(oleDsAccessLocationT);
		oleDsAccessLocationT.setOleDsAccessLocationCodeT(null);

		return oleDsAccessLocationT;
	}

}