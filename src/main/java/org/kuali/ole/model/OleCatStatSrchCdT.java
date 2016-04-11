package org.kuali.ole.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by sheiksalahudeenm on 10/30/15.
 */
@Entity
@Table(name="ole_cat_stat_srch_cd_t")
@NamedQuery(name="OleCatStatSrchCdT.findAll", query="SELECT o FROM OleCatStatSrchCdT o")
public class OleCatStatSrchCdT implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="STAT_SRCH_CD_ID")
    private long statSrchCdId;

    @Column(name="OBJ_ID")
    private String objId;

    @Column(name="ROW_ACT_IND")
    private String rowActInd;

    private String src;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="SRC_DT")
    private Date srcDt;

    @Column(name="STAT_SRCH_CD")
    private String statSrchCd;

    @Column(name="STAT_SRCH_NM")
    private String statSrchNm;

    @Column(name="VER_NBR")
    private BigDecimal verNbr;

    public OleCatStatSrchCdT() {
    }

    public long getStatSrchCdId() {
        return this.statSrchCdId;
    }

    public void setStatSrchCdId(long statSrchCdId) {
        this.statSrchCdId = statSrchCdId;
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

    public String getStatSrchCd() {
        return this.statSrchCd;
    }

    public void setStatSrchCd(String statSrchCd) {
        this.statSrchCd = statSrchCd;
    }

    public String getStatSrchNm() {
        return this.statSrchNm;
    }

    public void setStatSrchNm(String statSrchNm) {
        this.statSrchNm = statSrchNm;
    }

    public BigDecimal getVerNbr() {
        return this.verNbr;
    }

    public void setVerNbr(BigDecimal verNbr) {
        this.verNbr = verNbr;
    }
}

