package org.kuali.ole.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ole_ds_bib_info_t database table.
 * 
 */
@Entity
@Table(name="ole_ds_bib_info_t")
public class OleDsBibInfoT implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="BIB_ID_STR")
	private String bibIdStr;

	private String author;

	@Column(name="BIB_ID")
	private Integer bibId;

	private String isxn;

	private String publisher;

	private String title;

	public OleDsBibInfoT() {
	}

	public String getBibIdStr() {
		return this.bibIdStr;
	}

	public void setBibIdStr(String bibIdStr) {
		this.bibIdStr = bibIdStr;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getBibId() {
		return this.bibId;
	}

	public void setBibId(Integer bibId) {
		this.bibId = bibId;
	}

	public String getIsxn() {
		return this.isxn;
	}

	public void setIsxn(String isxn) {
		this.isxn = isxn;
	}

	public String getPublisher() {
		return this.publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}