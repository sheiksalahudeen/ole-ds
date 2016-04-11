package org.kuali.ole.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by sheiksalahudeenm on 10/30/15.
 */
@Entity
@Table(name="ole_cat_type_ownership_t")
@NamedQuery(name="OleCatTypeOwnershipT.findAll", query="SELECT o FROM OleCatTypeOwnershipT o")
public class OleCatTypeOwnershipT implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="TYPE_OWNERSHIP_ID")
    private long typeOwnershipId;

    @Column(name="OBJ_ID")
    private String objId;

    @Column(name="ROW_ACT_IND")
    private String rowActInd;

    private String src;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="SRC_DT")
    private Date srcDt;

    @Column(name="TYPE_OWNERSHIP_CD")
    private String typeOwnershipCd;

    @Column(name="TYPE_OWNERSHIP_NM")
    private String typeOwnershipNm;

    @Column(name="VER_NBR")
    private BigDecimal verNbr;

    public OleCatTypeOwnershipT() {
    }

    public long getTypeOwnershipId() {
        return this.typeOwnershipId;
    }

    public void setTypeOwnershipId(long typeOwnershipId) {
        this.typeOwnershipId = typeOwnershipId;
    }

    public String getObjId() {
        return this.objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }

    public String getRowActInd() {
        return this.rowActInd;
    }

    public void setRowActInd(String rowActInd) {
        this.rowActInd = rowActInd;
    }

    public String getSrc() {
        return this.src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Date getSrcDt() {
        return this.srcDt;
    }

    public void setSrcDt(Date srcDt) {
        this.srcDt = srcDt;
    }

    public String getTypeOwnershipCd() {
        return this.typeOwnershipCd;
    }

    public void setTypeOwnershipCd(String typeOwnershipCd) {
        this.typeOwnershipCd = typeOwnershipCd;
    }

    public String getTypeOwnershipNm() {
        return this.typeOwnershipNm;
    }

    public void setTypeOwnershipNm(String typeOwnershipNm) {
        this.typeOwnershipNm = typeOwnershipNm;
    }

    public BigDecimal getVerNbr() {
        return this.verNbr;
    }

    public void setVerNbr(BigDecimal verNbr) {
        this.verNbr = verNbr;
    }
}
