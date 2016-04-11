package org.kuali.ole.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by SheikS on 11/11/15.
 */
@Entity
@Table(name="ole_dlvr_item_avail_stat_t")
@NamedQuery(name="OleDlvrItemAvailStatT.findAll", query="SELECT o FROM OleDlvrItemAvailStatT o")
public class OleDlvrItemAvailStatT implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ITEM_AVAIL_STAT_ID")
    private String itemAvailStatId;

    @Column(name="ITEM_AVAIL_STAT_CD")
    private String itemAvailStatCd;

    @Column(name="ITEM_AVAIL_STAT_NM")
    private String itemAvailStatNm;

    @Column(name="OBJ_ID")
    private String objId;

    @Column(name="ROW_ACT_IND")
    private String rowActInd;

    @Column(name="VER_NBR")
    private BigDecimal verNbr;

    public OleDlvrItemAvailStatT() {
    }

    public String getItemAvailStatId() {
        return this.itemAvailStatId;
    }

    public void setItemAvailStatId(String itemAvailStatId) {
        this.itemAvailStatId = itemAvailStatId;
    }

    public String getItemAvailStatCd() {
        return this.itemAvailStatCd;
    }

    public void setItemAvailStatCd(String itemAvailStatCd) {
        this.itemAvailStatCd = itemAvailStatCd;
    }

    public String getItemAvailStatNm() {
        return this.itemAvailStatNm;
    }

    public void setItemAvailStatNm(String itemAvailStatNm) {
        this.itemAvailStatNm = itemAvailStatNm;
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

    public BigDecimal getVerNbr() {
        return this.verNbr;
    }

    public void setVerNbr(BigDecimal verNbr) {
        this.verNbr = verNbr;
    }

}
