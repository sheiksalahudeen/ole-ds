package org.kuali.ole.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ole_ds_high_density_storage_t database table.
 * 
 */
@Entity
@Table(name="ole_ds_high_density_storage_t")
public class OleDsHighDensityStorageT implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="HIGH_DENSITY_STORAGE_ID")
	private Integer highDensityStorageId;

	@Column(name="HIGH_DENSITY_MODULE")
	private String highDensityModule;

	@Column(name="HIGH_DENSITY_ROW")
	private String highDensityRow;

	@Column(name="HIGH_DENSITY_SHELF")
	private String highDensityShelf;

	@Column(name="HIGH_DENSITY_TRAY")
	private String highDensityTray;

	//bi-directional many-to-one association to OleDsItemT
	@OneToMany(mappedBy="oleDsHighDensityStorageT")
	private List<OleDsItemT> oleDsItemTs;

	public OleDsHighDensityStorageT() {
	}

	public Integer getHighDensityStorageId() {
		return this.highDensityStorageId;
	}

	public void setHighDensityStorageId(Integer highDensityStorageId) {
		this.highDensityStorageId = highDensityStorageId;
	}

	public String getHighDensityModule() {
		return this.highDensityModule;
	}

	public void setHighDensityModule(String highDensityModule) {
		this.highDensityModule = highDensityModule;
	}

	public String getHighDensityRow() {
		return this.highDensityRow;
	}

	public void setHighDensityRow(String highDensityRow) {
		this.highDensityRow = highDensityRow;
	}

	public String getHighDensityShelf() {
		return this.highDensityShelf;
	}

	public void setHighDensityShelf(String highDensityShelf) {
		this.highDensityShelf = highDensityShelf;
	}

	public String getHighDensityTray() {
		return this.highDensityTray;
	}

	public void setHighDensityTray(String highDensityTray) {
		this.highDensityTray = highDensityTray;
	}

	public List<OleDsItemT> getOleDsItemTs() {
		return this.oleDsItemTs;
	}

	public void setOleDsItemTs(List<OleDsItemT> oleDsItemTs) {
		this.oleDsItemTs = oleDsItemTs;
	}

	public OleDsItemT addOleDsItemT(OleDsItemT oleDsItemT) {
		getOleDsItemTs().add(oleDsItemT);
		oleDsItemT.setOleDsHighDensityStorageT(this);

		return oleDsItemT;
	}

	public OleDsItemT removeOleDsItemT(OleDsItemT oleDsItemT) {
		getOleDsItemTs().remove(oleDsItemT);
		oleDsItemT.setOleDsHighDensityStorageT(null);

		return oleDsItemT;
	}

}