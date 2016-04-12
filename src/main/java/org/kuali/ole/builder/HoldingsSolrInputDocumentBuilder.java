package org.kuali.ole.builder;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.common.SolrInputDocument;
import org.kuali.ole.builder.resolvers.HoldingsFieldValueResolver;
import org.kuali.ole.constants.OleDsConstants;
import org.kuali.ole.constants.enums.DocCategory;
import org.kuali.ole.constants.enums.DocFormat;
import org.kuali.ole.constants.enums.DocType;
import org.kuali.ole.model.*;
import org.kuali.ole.util.OleDsHelperUtil;
import org.kuali.ole.util.DocumentUniqueIDPrefixUtil;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by pvsubrah on 10/29/15.
 */
public class HoldingsSolrInputDocumentBuilder extends OleDsConstants {
    Map<String, String> fieldNamesMap;
    List<HoldingsFieldValueResolver> holdingsFieldValueResolvers;
    private OleDsHelperUtil oleDsHelperUtil;

    public Map<String, String> getFieldNamesMap() {
        fieldNamesMap = new HashMap<String, String>();
        return fieldNamesMap;
    }

    public List<HoldingsFieldValueResolver> getHoldingsFieldValueResolvers() {
        holdingsFieldValueResolvers = new ArrayList<HoldingsFieldValueResolver>();
        return holdingsFieldValueResolvers;
    }

    public List<SolrInputDocument> build(OleDsHoldingsT oleDsHoldingsT) {
        ArrayList<SolrInputDocument> solrInputDocuments = new ArrayList<SolrInputDocument>();

        SolrInputDocument solrInputDocument = new SolrInputDocument();


        //TODO: Implement
//        Set<String> fieldNames = getFieldNamesMap().keySet();
//        for (Iterator<String> iterator = fieldNames.iterator(); iterator.hasNext(); ) {
//            String fieldName = iterator.next();
//
//            for (Iterator<HoldingsFieldValueResolver> iterator1 = getHoldingsFieldValueResolvers().iterator(); iterator.hasNext(); ) {
//                HoldingsFieldValueResolver fieldValueResolver = iterator1.next();
//                if(fieldValueResolver.isInterested(fieldName)){
//                    fieldValueResolver.setFieldValue(solrInputDocument, oleDsHoldingsT);
//
//                }
//            }
//        }

        String holdingsIdentifierWithPrefix = DocumentUniqueIDPrefixUtil.getPrefixedId(oleDsHoldingsT.getUniqueIdPrefix(), String.valueOf(oleDsHoldingsT.getHoldingsId()));

        if(oleDsHoldingsT != null && oleDsHoldingsT.getGokbIdentifier() != null) {
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.GOKB_IDENTIFIER, oleDsHoldingsT.getGokbIdentifier());
        }

        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + DOC_CATEGORY, DocCategory.WORK.getCode());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + DOC_FORMAT, DocFormat.OLEML.getCode());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + LOCALID_SEARCH, oleDsHoldingsT.getHoldingsId());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + LOCALID_DISPLAY, oleDsHoldingsT.getHoldingsId());
        solrInputDocument.addField(ID, holdingsIdentifierWithPrefix);
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.HOLDINGS_IDENTIFIER, oleDsHoldingsT.getHoldingsId());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + UNIQUE_ID, holdingsIdentifierWithPrefix);
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.STAFF_ONLY_FLAG, oleDsHoldingsT.getStaffOnly());


        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.RECEIPT_STATUS_SEARCH, oleDsHoldingsT.getReceiptStatusId());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.RECEIPT_STATUS_DISPLAY, oleDsHoldingsT.getReceiptStatusId());

        if (oleDsHoldingsT.getCopyNumber() != null) {
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.COPY_NUMBER_SEARCH, oleDsHoldingsT.getCopyNumber());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.COPY_NUMBER_LABEL_SEARCH, oleDsHoldingsT.getCopyNumber());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.COPY_NUMBER_DISPLAY, oleDsHoldingsT.getCopyNumber());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.COPY_NUMBER_LABEL_DISPLAY, oleDsHoldingsT.getCopyNumber());
        }
        if (oleDsHoldingsT.getCallNumber() != null) {
            // solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.CALL_NUMBER_TYPE_SEARCH, oleDsHoldingsT.getOleCatShvlgSchmT().getType());  // Todo : Need to verify
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.CALL_NUMBER_SEARCH, oleDsHoldingsT.getCallNumber());
            solrInputDocument.setField(OLE_DS_SOLR_PREFIX + HoldingsConstants.CALL_NUMBER_SORT, oleDsHoldingsT.getCallNumber());
            //solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.ITEM_PART_SEARCH, oleDsHoldingsT.getCallNumber().getItemPart()); // Todo : Need to verify
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.CALL_NUMBER_PREFIX_SEARCH, oleDsHoldingsT.getCallNumberPrefix());
            // solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.CLASSIFICATION_PART_SEARCH, oleDsHoldingsT.getCallNumber().getClassificationPart()); // Need to Verify

            // solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.ITEM_PART_DISPLAY, oleDsHoldingsT.getCallNumber().getItemPart()); // Todo : Need to verify
            // solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.CALL_NUMBER_TYPE_DISPLAY, oleDsHoldingsT.getCallNumber().getType()); // Todo : Need to verify
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.CALL_NUMBER_DISPLAY, oleDsHoldingsT.getCallNumber());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.CALL_NUMBER_PREFIX_DISPLAY, oleDsHoldingsT.getCallNumberPrefix());
            //solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.CLASSIFICATION_PART_DISPLAY, oleDsHoldingsT.getCallNumber().getClassificationPart()); // Todo : Need to verify
            String shelvingSchemeCodeValue = "";
            if (oleDsHoldingsT.getOleCatShvlgSchmT() != null) {
                shelvingSchemeCodeValue = oleDsHoldingsT.getShelvingOrder();
                solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.SHELVING_SCHEME_VALUE_SEARCH, oleDsHoldingsT.getShelvingOrder());
                solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.SHELVING_SCHEME_CODE_SEARCH, oleDsHoldingsT.getShelvingOrder());
                solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.SHELVING_SCHEME_VALUE_DISPLAY, oleDsHoldingsT.getShelvingOrder());
                solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.SHELVING_SCHEME_CODE_DISPLAY, oleDsHoldingsT.getShelvingOrder());
            }
            String shelvingOrder = null;
            if (oleDsHoldingsT.getOleCatShvlgSchmT() != null && StringUtils.isNotEmpty(oleDsHoldingsT.getOleCatShvlgSchmT().getShvlgSchmCd())) {
                shelvingOrder = oleDsHoldingsT.getOleCatShvlgSchmT().getShvlgSchmCd();
            } else {
                if (StringUtils.isNotEmpty(oleDsHoldingsT.getCallNumber()) && oleDsHoldingsT.getCallNumber().trim().length() > 0) {
                    shelvingOrder = getOleDsHelperUtil().buildSortableCallNumber(oleDsHoldingsT.getCallNumber(), shelvingSchemeCodeValue);
                }
            }
            if (StringUtils.isNotEmpty(shelvingOrder)) {
                shelvingOrder = shelvingOrder.replaceAll(" ", "-");
                solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.SHELVING_ORDER_SORT, shelvingOrder + oleDsHoldingsT.getHoldingsId());
                solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.SHELVING_ORDER_SEARCH, shelvingOrder);
                solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.SHELVING_ORDER_DISPLAY, shelvingOrder);
            }
        }

        StringBuffer loactionLevelStr = new StringBuffer(" ");
        if (oleDsHoldingsT.getLocation() != null &&
                oleDsHoldingsT.getLocationLevel() != null) {
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + LOCATION_LEVEL_SEARCH, oleDsHoldingsT.getLocation());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + LOCATION_LEVEL_NAME_SEARCH, oleDsHoldingsT.getLocationLevel());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + LOCATION_LEVEL_DISPLAY, oleDsHoldingsT.getLocation());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + LOCATION_LEVEL_NAME_DISPLAY, oleDsHoldingsT.getLocationLevel());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + LOCATION_LEVEL_SORT, oleDsHoldingsT.getLocation());
            StringTokenizer locationTokenizer = new StringTokenizer(oleDsHoldingsT.getLocation(),"/");
            StringTokenizer locationLevelTokenizer = new StringTokenizer(oleDsHoldingsT.getLocationLevel(),"/");
            while(locationLevelTokenizer.hasMoreTokens()){
                String locationLevel = locationLevelTokenizer.nextToken();
                String location = locationTokenizer.nextToken();
                getOleDsHelperUtil().buildLocationLevels(location,locationLevel,solrInputDocument,loactionLevelStr);
            }
        }
        if (null != oleDsHoldingsT.getHoldingsType()) {
            if(oleDsHoldingsT.getHoldingsType().equalsIgnoreCase(PRINT)) {
                solrInputDocument.addField(OLE_DS_SOLR_PREFIX + DOC_TYPE, DocType.HOLDINGS.getCode());
                indexPHoldingsInformation(oleDsHoldingsT, solrInputDocument);
            }
            else {
                solrInputDocument.addField(OLE_DS_SOLR_PREFIX + DOC_TYPE, DocType.EHOLDINGS.getCode());
                indexEHoldingsInfomation(oleDsHoldingsT, solrInputDocument);
            }
        }

        solrInputDocuments.add(solrInputDocument);
        return solrInputDocuments;
    }



    private void indexPHoldingsInformation(OleDsHoldingsT oleDsHoldingsT, SolrInputDocument solrInputDocument) {
        for (OleDsHoldingsNoteT holdingNote : oleDsHoldingsT.getOleDsHoldingsNoteTs()) {
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.HOLDING_NOTE_SEARCH, holdingNote.getNote());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.HOLDING_NOTE_DISPLAY, holdingNote.getNote());
        }
        for (OleDsHoldingsUriT uri : oleDsHoldingsT.getOleDsHoldingsUriTs()) {
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.URI_SEARCH, uri.getUri());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.URI_DISPLAY, uri.getUri());
        }
        List<OleDsExtOwnershipT> oleDsExtOwnershipTs = oleDsHoldingsT.getOleDsExtOwnershipTs();
        if (CollectionUtils.isNotEmpty(oleDsExtOwnershipTs)) {
            for (Iterator<OleDsExtOwnershipT> iterator = oleDsExtOwnershipTs.iterator(); iterator.hasNext(); ) {
                OleDsExtOwnershipT oleDsExtOwnershipT = iterator.next();
                List<OleDsExtOwnershipNoteT> oleDsExtOwnershipNoteTs = oleDsExtOwnershipT.getOleDsExtOwnershipNoteTs();
                for (Iterator<OleDsExtOwnershipNoteT> oleDsExtOwnershipNoteTIterator = oleDsExtOwnershipNoteTs.iterator(); oleDsExtOwnershipNoteTIterator.hasNext(); ) {
                    OleDsExtOwnershipNoteT oleDsExtOwnershipNoteT = oleDsExtOwnershipNoteTIterator.next();
                    solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.EXTENT_OF_OWNERSHIP_NOTE_VALUE_DISPLAY,oleDsExtOwnershipNoteT.getNote());
                    solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.EXTENT_OF_OWNERSHIP_NOTE_TYPE_DISPLAY,oleDsExtOwnershipNoteT.getType());
                }
                if (null != oleDsExtOwnershipT.getOleCatTypeOwnershipT()) {
                    solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.EXTENT_OF_OWNERSHIP_TYPE_DISPLAY, oleDsExtOwnershipT.getOleCatTypeOwnershipT().getTypeOwnershipCd());
                }
            }
        }
        if (oleDsHoldingsT.getOleDsAccessLocationTs() != null && null != oleDsHoldingsT.getOleDsAuthenticationTypeT()) {
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.AUTHENTICATION_DISPLAY, oleDsHoldingsT.getOleDsAuthenticationTypeT().getName());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.PROXIED_DISPLAY, oleDsHoldingsT.getProxiedResource());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.NUMBER_OF_SIMULTANEOUS_USERS_DISPLAY, oleDsHoldingsT.getNumberSimultUsers());
            if (CollectionUtils.isNotEmpty(oleDsHoldingsT.getOleDsAccessLocationTs()) && null != oleDsHoldingsT.getOleDsAccessLocationTs().get(0).getOleDsAccessLocationCodeT()) {
                solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.ACCESS_LOCATION_DISPLAY, oleDsHoldingsT.getOleDsAccessLocationTs().get(0).getOleDsAccessLocationCodeT().getCode());
            } else {
                solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.ACCESS_LOCATION_DISPLAY, null);
            }
        }
    }

    private void indexEHoldingsInfomation(OleDsHoldingsT oleDsHoldingsT, SolrInputDocument solrInputDocument) {

        for (OleDsHoldingsNoteT holdingNote : oleDsHoldingsT.getOleDsHoldingsNoteTs()) {
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.HOLDING_NOTE_SEARCH, holdingNote.getNote());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.HOLDING_NOTE_DISPLAY, holdingNote.getNote());
        }
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.ACCESS_STATUS_DISPLAY, oleDsHoldingsT.getAccessStatus());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.ACCESS_STATUS_SEARCH, oleDsHoldingsT.getAccessStatus());
        for (OleDsHoldingsDonorT donorInfo : oleDsHoldingsT.getOleDsHoldingsDonorTs()) {
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.DONOR_CODE_SEARCH, donorInfo.getDonorCode());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.DONOR_CODE_DISPLAY, donorInfo.getDonorCode());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.DONOR_PUBLIC_DISPLAY, donorInfo.getDonorDisplayNote());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.DONOR_PUBLIC_SEARCH, donorInfo.getDonorDisplayNote());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.DONOR_NOTE_DISPLAY, donorInfo.getDonorNote());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.DONOR_NOTE_SEARCH, donorInfo.getDonorNote());
        }
        List<OleDsHoldingsStatSearchT> oleDsHoldingsStatSearchTs = oleDsHoldingsT.getOleDsHoldingsStatSearchTs();

        if (CollectionUtils.isNotEmpty(oleDsHoldingsStatSearchTs) && null != oleDsHoldingsStatSearchTs.get(0).getOleCatStatSrchCdT()) {
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.STATISTICAL_SEARCHING_CODE_VALUE_SEARCH, oleDsHoldingsStatSearchTs.get(0).getOleCatStatSrchCdT().getStatSrchNm());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.STATISTICAL_SEARCHING_CODE_VALUE_DISPLAY, oleDsHoldingsStatSearchTs.get(0).getOleCatStatSrchCdT().getStatSrchCd());
        }

       /* solrInputDocument.addField(OLE_DS_SOLR_PREFIX + PUBLISHER_SEARCH, oleHoldings.getPublisher());*/
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.E_PUBLISHER_DISPLAY, oleDsHoldingsT.getPublisher());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.E_PUBLISHER_SEARCH, oleDsHoldingsT.getPublisher());

        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.IMPRINT_SEARCH, oleDsHoldingsT.getImprint());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.IMPRINT_DISPLAY, oleDsHoldingsT.getImprint());

        if (oleDsHoldingsT.getPlatform() != null) {
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.ADMIN_URL_DISPLAY, oleDsHoldingsT.getAdminUrl());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.ADMIN_URL_SEARCH, oleDsHoldingsT.getAdminUrl());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.PLATFORM_DISPLAY, oleDsHoldingsT.getPlatform());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.ADMIN_USERNAME_DISPLAY, oleDsHoldingsT.getAdminUsername());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.ADMIN_USERNAME_SEARCH, oleDsHoldingsT.getAdminUsername());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.ADMIN_PASSWORD_DISPLAY, oleDsHoldingsT.getAdminPassword());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.ADMIN_PASSWORD_SEARCH, oleDsHoldingsT.getAdminPassword());
        }

        for (OleDsHoldingsUriT uri : oleDsHoldingsT.getOleDsHoldingsUriTs()) {
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.URL_DISPLAY, uri.getUri());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.URL_SEARCH, uri.getUri());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.LINK_TEXT_DISPLAY, uri.getText());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.LINK_TEXT_SEARCH, uri.getText());
        }

        if (oleDsHoldingsT.getSubscriptionStatus() != null) {
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.SUBSCRIPTION_DISPLAY, oleDsHoldingsT.getSubscriptionStatus());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.SUBSCRIPTION_SEARCH, oleDsHoldingsT.getSubscriptionStatus());

        }

        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.ACCESS_USERNAME_DISPLAY, oleDsHoldingsT.getAccessUsername());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.ACCESS_USERNAME_SEARCH, oleDsHoldingsT.getAccessUsername());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.ACCESS_PASSWORD_DISPLAY, oleDsHoldingsT.getAccessPassword());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.ACCESS_PASSWORD_SEARCH, oleDsHoldingsT.getAccessPassword());
        if (null != oleDsHoldingsT.getOleDsAuthenticationTypeT()) {
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.AUTHENTICATION_DISPLAY, oleDsHoldingsT.getOleDsAuthenticationTypeT().getCode());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.AUTHENTICATION_SEARCH, oleDsHoldingsT.getOleDsAuthenticationTypeT().getCode());
        }
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.PROXIED_DISPLAY, oleDsHoldingsT.getProxiedResource());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.PROXIED_SEARCH, oleDsHoldingsT.getProxiedResource());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.NUMBER_OF_SIMULTANEOUS_USERS_DISPLAY, oleDsHoldingsT.getNumberSimultUsers());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.NUMBER_OF_SIMULTANEOUS_USERS_SEARCH, oleDsHoldingsT.getNumberSimultUsers());
        if (CollectionUtils.isNotEmpty(oleDsHoldingsT.getOleDsAccessLocationTs()) && null != oleDsHoldingsT.getOleDsAccessLocationTs().get(0).getOleDsAccessLocationCodeT()) {
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.ACCESS_LOCATION_DISPLAY, oleDsHoldingsT.getOleDsAccessLocationTs().get(0).getOleDsAccessLocationCodeT().getCode());
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.ACCESS_LOCATION_SEARCH, oleDsHoldingsT.getOleDsAccessLocationTs().get(0).getOleDsAccessLocationCodeT().getCode());
        }
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.PERSIST_LINK_SEARCH, oleDsHoldingsT.getLocalPersistentUri());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.PERSIST_LINK_DISPLAY, oleDsHoldingsT.getLocalPersistentUri());

        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.ILL_DISPLAY, oleDsHoldingsT.getAllowIll());
        solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.ILL_SEARCH, oleDsHoldingsT.getAllowIll());

        List<OleDsHoldingsCoverageT> oleDsHoldingsCoverageTs = oleDsHoldingsT.getOleDsHoldingsCoverageTs();
        if (CollectionUtils.isNotEmpty(oleDsHoldingsCoverageTs)) {
            for (Iterator<OleDsHoldingsCoverageT> iterator = oleDsHoldingsCoverageTs.iterator(); iterator.hasNext(); ) {
                OleDsHoldingsCoverageT oleDsHoldingsCoverageT = iterator.next();
                if (StringUtils.isNotBlank(oleDsHoldingsCoverageT.getCoverageStartDate()) || StringUtils.isNotBlank(oleDsHoldingsCoverageT.getCoverageEndDate())) {
                    solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.E_INSTANCE_COVERAGE_DATE, oleDsHoldingsCoverageT.getCoverageStartDate() + "-" + oleDsHoldingsCoverageT.getCoverageEndDate());
                } else if (CollectionUtils.isNotEmpty(oleDsHoldingsT.getOleDsPerpetualAccessTs())) {
                    List<OleDsPerpetualAccessT> oleDsPerpetualAccessTs = oleDsHoldingsT.getOleDsPerpetualAccessTs();
                    for (Iterator<OleDsPerpetualAccessT> oleDsPerpetualAccessTIterator = oleDsPerpetualAccessTs.iterator(); oleDsPerpetualAccessTIterator.hasNext(); ) {
                        OleDsPerpetualAccessT oleDsPerpetualAccessT = oleDsPerpetualAccessTIterator.next();
                        if (StringUtils.isNotBlank(oleDsPerpetualAccessT.getPerpetualAccessStartDate()) || StringUtils.isNotBlank(oleDsPerpetualAccessT.getPerpetualAccessEndDate())) {
                            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + HoldingsConstants.E_INSTANCE_PERPETUAL_ACCESS_DATE, oleDsPerpetualAccessT.getPerpetualAccessStartDate() + "-" + oleDsPerpetualAccessT.getPerpetualAccessEndDate());
                        }
                    }
                }
            }
        }

    }

    public OleDsHelperUtil getOleDsHelperUtil() {
        if(null == oleDsHelperUtil){
            oleDsHelperUtil = new OleDsHelperUtil();
        }
        return oleDsHelperUtil;
    }

    public void setOleDsHelperUtil(OleDsHelperUtil oleDsHelperUtil) {
        this.oleDsHelperUtil = oleDsHelperUtil;
    }
}

