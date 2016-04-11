package org.kuali.ole.builder;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.solr.common.SolrInputDocument;
import org.kuali.ole.constants.OleDsConstants;
import org.kuali.ole.constants.enums.DocCategory;
import org.kuali.ole.model.*;
import org.kuali.ole.util.OleDsHelperUtil;
import org.kuali.ole.util.DocumentUniqueIDPrefixUtil;

import java.util.*;

/**
 * Created by pvsubrah on 10/29/15.
 */
public class ItemsSolrInputDocumentBuilder extends OleDsConstants {


    private static final Logger LOG = Logger.getLogger(ItemsSolrInputDocumentBuilder.class);
    private OleDsHelperUtil oleDsHelperUtil;



    public List<SolrInputDocument> build(OleDsItemT oleDsItemT) {
        ArrayList<SolrInputDocument> solrInputDocuments = new ArrayList<SolrInputDocument>();


        SolrInputDocument solrInputDocument = new SolrInputDocument();

        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + DOC_CATEGORY, DocCategory.WORK.getCode());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + DOC_TYPE, DOC_TYPE_ITEM_VALUE);
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + DOC_FORMAT, DOC_FORMAT_INSTANCE_VALUE);
        String itemIdWithPrefix = DocumentUniqueIDPrefixUtil.getPrefixedId(oleDsItemT.getUniqueIdPrefix(), String.valueOf(oleDsItemT.getItemId()));
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ID, itemIdWithPrefix);
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.ITEM_IDENTIFIER, itemIdWithPrefix);
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + LOCALID_DISPLAY, oleDsItemT.getItemId());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + LOCALID_SEARCH, oleDsItemT.getItemId());


        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.CLMS_RET_FLAG, Boolean.valueOf(oleDsItemT.getClaimsReturned()));
        Date claimsReturnedDateCreated = oleDsItemT.getClaimsReturnedDateCreated();
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.CLMS_RET_FLAG_CRE_DATE, (claimsReturnedDateCreated != null ? DOCSTORE_DATE_FORMAT.format(claimsReturnedDateCreated) : null));
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.CLMS_RET_NOTE, oleDsItemT.getClaimsReturnedNote());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.CURRENT_BORROWER, oleDsItemT.getCurrentBorrower());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.PROXY_BORROWER, oleDsItemT.getProxyBorrower());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.DUE_DATE_TIME, oleDsItemT.getDueDateTime());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.ORG_DUE_DATE_TIME, oleDsItemT.getOrgDueDateTime());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.ITEM_STATUS_EFFECTIVE_DATE, oleDsItemT.getItemStatusDateUpdated());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.CHECK_OUT_DUE_DATE_TIME, oleDsItemT.getCheckOutDateTime());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.STAFF_ONLY_FLAG, oleDsItemT.getStaffOnly()); // TODO : Need to create boolean converter
        // solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.IS_ANALYTIC, oleDsItemT.isAnalytic()); // TODO : Need to check
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.ITEM_IDENTIFIER_SEARCH, oleDsItemT.getItemId());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.BARCODE_ARSL_SEARCH, oleDsItemT.getBarcodeArsl());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.COPY_NUMBER_SEARCH, oleDsItemT.getCopyNumber());
        // solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.COPY_NUMBER_LABEL_SEARCH, oleDsItemT.getCopyNumberLabel()); // TODO : Need to check
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.PURCHASE_ORDER_LINE_ITEM_IDENTIFIER_SEARCH, oleDsItemT.getPurchaseOrderLineItemId());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.VENDOR_LINE_ITEM_IDENTIFIER_SEARCH, oleDsItemT.getVendorLineItemId());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.ENUMERATION_SEARCH, oleDsItemT.getEnumeration());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.CHRONOLOGY_SEARCH, oleDsItemT.getChronology());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.MISSING_PIECE_FLAG_NOTE_SEARCH, oleDsItemT.getMissingPiecesNote());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.CLAIMS_RETURNED_NOTE_SEARCH, oleDsItemT.getClaimsReturnedNote());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.DAMAGED_ITEM_NOTE_SEARCH, oleDsItemT.getItemDamagedNote());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.MISSING_PIECE_FLAG_SEARCH, oleDsItemT.getMissingPieces()); // TODO : Need to create boolean converter
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.CLAIMS_RETURNED_FLAG_SEARCH, oleDsItemT.getClaimsReturned()); // TODO : Need to create boolean converter
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.ITEM_DAMAGED_FLAG_SEARCH, oleDsItemT.getItemDamagedStatus());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.MISSING_PIECE_COUNT_SEARCH,oleDsItemT.getMissingPiecesCount());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.NUMBER_OF_PIECES_SEARCH,oleDsItemT.getNumPieces());


        Date date = new Date();
        // Item call number should be indexed if it is available at item level or holdings level.
        String itemCallNumber = null;
        if (StringUtils.isNotEmpty(oleDsItemT.getCallNumber())) {
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.CALL_NUMBER_SEARCH, oleDsItemT.getCallNumber());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.CALL_NUMBER_DISPLAY, oleDsItemT.getCallNumber());
            solrInputDocument.setField(ItemConstants.CALL_NUMBER_SORT, oleDsItemT.getCallNumber());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.CALL_NUMBER_PREFIX_SEARCH, oleDsItemT.getCallNumberPrefix());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.CALL_NUMBER_PREFIX_DISPLAY, oleDsItemT.getCallNumberPrefix());

            //Shelving scheme code should be indexed if it is available at holdings level
            String shelvingSchemeCode = "";
            String shelvingSchemeValue = "";

            if (null != oleDsItemT.getOleCatShvlgSchmT()) {
                shelvingSchemeCode = oleDsItemT.getOleCatShvlgSchmT().getShvlgSchmCd();
                shelvingSchemeValue = oleDsItemT.getOleCatShvlgSchmT().getShvlgSchmNm();
                if (StringUtils.isNotEmpty(shelvingSchemeCode)) {
                    solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.SHELVING_SCHEME_CODE_SEARCH, shelvingSchemeCode);
                    solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.SHELVING_SCHEME_CODE_DISPLAY, shelvingSchemeCode);
                }
                if (StringUtils.isNotEmpty(shelvingSchemeValue)) {
                    solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.SHELVING_SCHEME_VALUE_SEARCH, shelvingSchemeValue);
                    solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.SHELVING_SCHEME_VALUE_DISPLAY, shelvingSchemeValue);
                }
            }

            String shelvingOrder = null;
            //TODO:Shelving order not present at item level
            if (StringUtils.isNotBlank(oleDsItemT.getShelvingOrder())) {
                shelvingOrder = oleDsItemT.getShelvingOrder();
            }else if (StringUtils.isEmpty(shelvingOrder) && oleDsItemT.getCallNumber() != null) {
                try {
                    //Build sortable key for a valid call number
                    if (oleDsItemT.getOleCatShvlgSchmT() != null) {
                        if(StringUtils.isNotEmpty(itemCallNumber) && itemCallNumber.trim().length() > 0) {
                            shelvingOrder = getOleDsHelperUtil().buildSortableCallNumber(itemCallNumber, oleDsItemT.getShelvingOrder());
                        }
                    }
                } catch (Exception e) {
                    LOG.error(e.getMessage(), e);
                }
            }
            if (StringUtils.isNotEmpty(shelvingOrder)) {
                shelvingOrder = shelvingOrder.replaceAll(" ", "-");
                solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.SHELVING_ORDER_SORT, shelvingOrder + itemIdWithPrefix);
                solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.SHELVING_ORDER_SEARCH, shelvingOrder);
                solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.SHELVING_ORDER_DISPLAY, shelvingOrder);
            }
            if (oleDsItemT.getCallNumberPrefix() != null) {
                solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.CALLNUMBER_PREFIX_SORT, oleDsItemT.getCallNumberPrefix());
            }
            if (oleDsItemT.getCallNumber() != null) {
                solrInputDocument.setField(ItemConstants.CALLNUMBER_SORT, oleDsItemT.getCallNumber());
            }
            if (oleDsItemT.getEnumeration() != null) {
                String enumerationSort = getOleDsHelperUtil().getNormalizedEnumeration(oleDsItemT.getEnumeration());
                solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.ENUMERATION_SORT, enumerationSort);
            }
            if (oleDsItemT.getChronology() != null) {
                solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.CHRONOLOGY_SORT, oleDsItemT.getChronology());
            }
            if (oleDsItemT.getCopyNumber() != null) {
                String copyNumberSort = getOleDsHelperUtil().getNormalizedEnumeration(oleDsItemT.getCopyNumber());
                solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.COPYNUMBER_SORT, copyNumberSort);
            }
            if (null != oleDsItemT.getBarcode()) {
                solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.ITEM_BARCODE_SORT, oleDsItemT.getBarcode());
            }
        }

        if (oleDsItemT.getOleDlvrItemAvailStatT() != null) {
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.ITEM_STATUS_DISPLAY, oleDsItemT.getOleDlvrItemAvailStatT().getItemAvailStatCd());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.ITEM_STATUS_SEARCH, oleDsItemT.getOleDlvrItemAvailStatT().getItemAvailStatNm());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.ITEM_STATUS_SORT, oleDsItemT.getOleDlvrItemAvailStatT().getItemAvailStatNm());
        }

        StringBuffer loactionLevelStr = new StringBuffer(" ");
        if (oleDsItemT.getLocation() != null &&
                oleDsItemT.getLocationLevel() != null) {
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + LOCATION_LEVEL_SEARCH, oleDsItemT.getLocation());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + LOCATION_LEVEL_NAME_SEARCH, oleDsItemT.getLocationLevel());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + LOCATION_LEVEL_DISPLAY, oleDsItemT.getLocation());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + LOCATION_LEVEL_NAME_DISPLAY, oleDsItemT.getLocationLevel());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + LOCATION_LEVEL_SORT, oleDsItemT.getLocation());
            StringTokenizer locationTokenizer = new StringTokenizer(oleDsItemT.getLocation(),"/");
            StringTokenizer locationLevelTokenizer = new StringTokenizer(oleDsItemT.getLocationLevel(),"/");
            while(locationLevelTokenizer.hasMoreTokens()){
                String locationLevel = locationLevelTokenizer.nextToken();
                String location = locationTokenizer.nextToken();
                getOleDsHelperUtil().buildLocationLevels(location,locationLevel,solrInputDocument,loactionLevelStr);
            }
        }


        if (oleDsItemT.getItemTypeRecord() != null) {
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.ITEM_TYPE_FULL_VALUE_SEARCH, oleDsItemT.getItemTypeRecord().getItmTypNm());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.ITEM_TYPE_CODE_VALUE_SEARCH, oleDsItemT.getItemTypeRecord().getItmTypCd());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.ITEM_TYPE_FULL_VALUE_DISPLAY, oleDsItemT.getItemTypeRecord().getItmTypNm());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.ITEM_TYPE_CODE_VALUE_DISPLAY, oleDsItemT.getItemTypeRecord().getItmTypCd());
        }

        if (oleDsItemT.getTempItemTypeRecord() != null) {
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.TEMPORARY_ITEM_TYPE_FULL_VALUE_SEARCH, oleDsItemT.getTempItemTypeRecord().getItmTypNm());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.TEMPORARY_ITEM_TYPE_CODE_VALUE_SEARCH, oleDsItemT.getTempItemTypeRecord().getItmTypCd());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.TEMPORARY_ITEM_TYPE_FULL_VALUE_DISPLAY, oleDsItemT.getTempItemTypeRecord().getItmTypNm());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.TEMPORARY_ITEM_TYPE_CODE_VALUE_DISPLAY, oleDsItemT.getTempItemTypeRecord().getItmTypCd());
        }

        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.ITEM_BARCODE_SEARCH, oleDsItemT.getBarcode());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.ITEM_BARCODE_DISPLAY, oleDsItemT.getBarcode());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.ITEM_URI_SEARCH, oleDsItemT.getUri());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.ITEM_URI_DISPLAY, oleDsItemT.getUri());

        List<OleDsItemStatSearchT> oleDsItemStatSearchTs = oleDsItemT.getOleDsItemStatSearchTs();
        for (Iterator<OleDsItemStatSearchT> iterator = oleDsItemStatSearchTs.iterator(); iterator.hasNext(); ) {
            OleDsItemStatSearchT oleDsItemStatSearchT = iterator.next();
            if (null != oleDsItemStatSearchT && null != oleDsItemStatSearchT.getOleCatStatSrchCdT()) {
                solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.STATISTICAL_SEARCHING_CODE_VALUE_SEARCH, oleDsItemStatSearchT.getOleCatStatSrchCdT().getStatSrchCd());
                solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.STATISTICAL_SEARCHING_CODE_VALUE_DISPLAY, oleDsItemStatSearchT.getOleCatStatSrchCdT().getStatSrchCd());
                solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.STATISTICAL_SEARCHING_FULL_VALUE_SEARCH, oleDsItemStatSearchT.getOleCatStatSrchCdT().getStatSrchNm());
                solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.STATISTICAL_SEARCHING_FULL_VALUE_DISPLAY, oleDsItemStatSearchT.getOleCatStatSrchCdT().getStatSrchNm());
            }
        }

        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.ITEM_IDENTIFIER_DISPLAY, itemIdWithPrefix);
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.BARCODE_ARSL_DISPLAY, oleDsItemT.getBarcodeArsl());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.COPY_NUMBER_DISPLAY, oleDsItemT.getCopyNumber());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.PURCHASE_ORDER_LINE_ITEM_IDENTIFIER_DISPLAY, oleDsItemT.getPurchaseOrderLineItemId());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.VENDOR_LINE_ITEM_IDENTIFIER_DISPLAY, oleDsItemT.getVendorLineItemId());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.ENUMERATION_DISPLAY, oleDsItemT.getEnumeration());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.CHRONOLOGY_DISPLAY, oleDsItemT.getChronology());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.MISSING_PIECE_FLAG_NOTE_DISPLAY, oleDsItemT.getMissingPiecesNote());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.CLAIMS_RETURNED_NOTE_DISPLAY, oleDsItemT.getClaimsReturnedNote());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.DAMAGED_ITEM_NOTE_DISPLAY, oleDsItemT.getItemDamagedNote());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.MISSING_PIECE_FLAG_DISPLAY, oleDsItemT.getMissingPieces());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.CLAIMS_RETURNED_FLAG_DISPLAY, oleDsItemT.getClaimsReturned());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.ITEM_DAMAGED_FLAG_DISPLAY, oleDsItemT.getItemDamagedStatus());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.MISSING_PIECE_COUNT_DISPLAY,oleDsItemT.getMissingPiecesCount());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.NUMBER_OF_PIECES_DISPLAY,oleDsItemT.getNumPieces());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + CREATED_BY,oleDsItemT.getCreatedBy());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + UPDATED_BY,oleDsItemT.getUpdatedBy());


        Date createdDate = null;
        date = new Date();
        solrInputDocument.setField(ItemConstants.DATE_UPDATED, oleDsItemT.getDateUpdated());
        solrInputDocument.setField(ItemConstants.DATE_ENTERED, oleDsItemT.getDateCreated());

        List<OleDsItemDonorT> oleDsItemDonorTs = oleDsItemT.getOleDsItemDonorTs();
        for (Iterator<OleDsItemDonorT> iterator = oleDsItemDonorTs.iterator(); iterator.hasNext(); ) {
            OleDsItemDonorT oleDsItemDonorT = iterator.next();
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.DONOR_CODE_SEARCH, oleDsItemDonorT.getDonorCode());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.DONOR_CODE_DISPLAY, oleDsItemDonorT.getDonorCode());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.DONOR_PUBLIC_DISPLAY, oleDsItemDonorT.getDonorDisplayNote());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.DONOR_NOTE_DISPLAY, oleDsItemDonorT.getDonorNote());
        }
        if (oleDsItemT.getOleDsHighDensityStorageT() != null) {
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.HIGHDENSITYSTORAGE_ROW_DISPLAY, oleDsItemT.getOleDsHighDensityStorageT().getHighDensityRow());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.HIGHDENSITYSTORAGE_MODULE_DISPLAY, oleDsItemT.getOleDsHighDensityStorageT().getHighDensityModule());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.HIGHDENSITYSTORAGE_SHELF_DISPLAY, oleDsItemT.getOleDsHighDensityStorageT().getHighDensityShelf());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.HIGHDENSITYSTORAGE_TRAY_DISPLAY, oleDsItemT.getOleDsHighDensityStorageT().getHighDensityTray());
        }
        List<OleDsItemNoteT> oleDsItemNoteTs = oleDsItemT.getOleDsItemNoteTs();
        for (Iterator<OleDsItemNoteT> iterator = oleDsItemNoteTs.iterator(); iterator.hasNext(); ) {
            OleDsItemNoteT oleDsItemNoteT = iterator.next();
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.ITEMNOTE_VALUE_DISPLAY, oleDsItemNoteT.getNote());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.ITEMNOTE_TYPE_DISPLAY, oleDsItemNoteT.getType());
        }
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + ItemConstants.NUMBER_OF_RENEW, oleDsItemT.getNumOfRenew());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + UNIQUE_ID, itemIdWithPrefix);
        
        solrInputDocuments.add(solrInputDocument);
        return solrInputDocuments;
    }

    public OleDsHelperUtil getOleDsHelperUtil() {
        if (null == oleDsHelperUtil) {
            oleDsHelperUtil = new OleDsHelperUtil();
        }
        return oleDsHelperUtil;
    }

    public void setOleDsHelperUtil(OleDsHelperUtil oleDsHelperUtil) {
        this.oleDsHelperUtil = oleDsHelperUtil;
    }
}
