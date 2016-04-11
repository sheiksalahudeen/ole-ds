package org.kuali.ole.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ole_ds_authentication_type_t database table.
 * 
 */
@Entity
@Table(name="ole_ds_authentication_type_t")
public class OleDsAuthenticationTypeT implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="AUTHENTICATION_TYPE_ID")
	private Integer authenticationTypeId;

	private String code;

	private String name;

	public OleDsAuthenticationTypeT() {
	}

	public Integer getAuthenticationTypeId() {
		return this.authenticationTypeId;
	}

	public void setAuthenticationTypeId(Integer authenticationTypeId) {
		this.authenticationTypeId = authenticationTypeId;
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
}