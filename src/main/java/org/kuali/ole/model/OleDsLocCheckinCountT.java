package org.kuali.ole.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ole_ds_loc_checkin_count_t database table.
 * 
 */
@Entity
@Table(name="ole_ds_loc_checkin_count_t")
public class OleDsLocCheckinCountT implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="CHECK_IN_LOCATION_ID")
	private Integer checkInLocationId;

	@Column(name="LOCATION_COUNT")
	private Integer locationCount;

	@Column(name="LOCATION_IN_HOUSE_COUNT")
	private Integer locationInHouseCount;

	@Column(name="LOCATION_NAME")
	private String locationName;

	//bi-directional many-to-one association to OleDsItemT
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ITEM_ID")
	private OleDsItemT oleDsItemT;

	public OleDsLocCheckinCountT() {
	}

	public Integer getCheckInLocationId() {
		return this.checkInLocationId;
	}

	public void setCheckInLocationId(Integer checkInLocationId) {
		this.checkInLocationId = checkInLocationId;
	}

	public Integer getLocationCount() {
		return this.locationCount;
	}

	public void setLocationCount(Integer locationCount) {
		this.locationCount = locationCount;
	}

	public Integer getLocationInHouseCount() {
		return this.locationInHouseCount;
	}

	public void setLocationInHouseCount(Integer locationInHouseCount) {
		this.locationInHouseCount = locationInHouseCount;
	}

	public String getLocationName() {
		return this.locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public OleDsItemT getOleDsItemT() {
		return this.oleDsItemT;
	}

	public void setOleDsItemT(OleDsItemT oleDsItemT) {
		this.oleDsItemT = oleDsItemT;
	}

}