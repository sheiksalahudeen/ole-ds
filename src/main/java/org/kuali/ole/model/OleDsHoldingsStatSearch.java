package org.kuali.ole.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ole_ds_holdings_stat_search_s database table.
 * 
 */
@Entity
@Table(name="ole_ds_holdings_stat_search_s")
public class OleDsHoldingsStatSearch implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	public OleDsHoldingsStatSearch() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

}