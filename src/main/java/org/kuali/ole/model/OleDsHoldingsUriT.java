package org.kuali.ole.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ole_ds_holdings_uri_t database table.
 * 
 */
@Entity
@Table(name="ole_ds_holdings_uri_t")
public class OleDsHoldingsUriT implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="HOLDINGS_URI_ID")
	private Integer holdingsUriId;

	private String text;

	private String uri;

	//bi-directional many-to-one association to OleDsHoldingsT
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="HOLDINGS_ID")
	private OleDsHoldingsT oleDsHoldingsT;

	public OleDsHoldingsUriT() {
	}

	public Integer getHoldingsUriId() {
		return this.holdingsUriId;
	}

	public void setHoldingsUriId(Integer holdingsUriId) {
		this.holdingsUriId = holdingsUriId;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUri() {
		return this.uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public OleDsHoldingsT getOleDsHoldingsT() {
		return this.oleDsHoldingsT;
	}

	public void setOleDsHoldingsT(OleDsHoldingsT oleDsHoldingsT) {
		this.oleDsHoldingsT = oleDsHoldingsT;
	}

}