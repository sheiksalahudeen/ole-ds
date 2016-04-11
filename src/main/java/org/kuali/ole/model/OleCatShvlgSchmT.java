package org.kuali.ole.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by sheiksalahudeenm on 10/30/15.
 */
@Entity
@Table(name="ole_cat_shvlg_schm_t")
@NamedQuery(name="OleCatShvlgSchmT.findAll", query="SELECT o FROM OleCatShvlgSchmT o")
public class OleCatShvlgSchmT implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="SHVLG_SCHM_ID")
    private long shvlgSchmId;

    @Column(name="OBJ_ID")
    private String objId;

    @Column(name="ROW_ACT_IND")
    private String rowActInd;

    @Column(name="SHVLG_SCHM_CD")
    private String shvlgSchmCd;

    @Column(name="SHVLG_SCHM_NM")
    private String shvlgSchmNm;

    private String src;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="SRC_DT")
    private Date srcDt;

    @Column(name="VER_NBR")
    private BigDecimal verNbr;

    public OleCatShvlgSchmT() {
    }

    public long getShvlgSchmId() {
        return this.shvlgSchmId;
    }

    public void setShvlgSchmId(long shvlgSchmId) {
        this.shvlgSchmId = shvlgSchmId;
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

    public String getShvlgSchmCd() {
        return this.shvlgSchmCd;
    }

    public void setShvlgSchmCd(String shvlgSchmCd) {
        this.shvlgSchmCd = shvlgSchmCd;
    }

    public String getShvlgSchmNm() {
        return this.shvlgSchmNm;
    }

    public void setShvlgSchmNm(String shvlgSchmNm) {
        this.shvlgSchmNm = shvlgSchmNm;
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

    public BigDecimal getVerNbr() {
        return this.verNbr;
    }

    public void setVerNbr(BigDecimal verNbr) {
        this.verNbr = verNbr;
    }
}
