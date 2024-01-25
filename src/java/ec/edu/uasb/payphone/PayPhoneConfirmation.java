/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.payphone;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author victor.barba
 */
public class PayPhoneConfirmation {

    private String email;
    private String cardType;
    private String bin;
    private String lastDigits;
    private String deferredCode;
    private String deferredMessage;
    private boolean deferred;
    private String cardBrandCode;
    private String cardBrand;
    private BigDecimal amount;
    private String clientTransactionId;
    private String phoneNumber;
    private long statusCode;
    private String transactionStatus;
    private String authorizationCode;
    private long messageCode;
    private String transactionId;
    private String document;
    private String currency;
    private String optionalParameter1;
    private String optionalParameter2;
    private String optionalParameter3;
    private String optionalParameter4;
    private String storeName;
    private Date date;
    private String regionIso;
    private String transactionType;
    private String recap;
    private String reference;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public String getLastDigits() {
        return lastDigits;
    }

    public void setLastDigits(String lastDigits) {
        this.lastDigits = lastDigits;
    }

    public String getDeferredCode() {
        return deferredCode;
    }

    public void setDeferredCode(String deferredCode) {
        this.deferredCode = deferredCode;
    }

    public String getDeferredMessage() {
        return deferredMessage;
    }

    public void setDeferredMessage(String deferredMessage) {
        this.deferredMessage = deferredMessage;
    }

    public boolean isDeferred() {
        return deferred;
    }

    public void setDeferred(boolean deferred) {
        this.deferred = deferred;
    }

    public String getCardBrandCode() {
        return cardBrandCode;
    }

    public void setCardBrandCode(String cardBrandCode) {
        this.cardBrandCode = cardBrandCode;
    }

    public String getCardBrand() {
        return cardBrand;
    }

    public void setCardBrand(String cardBrand) {
        this.cardBrand = cardBrand;
    }

    public BigDecimal getAmount() {
        amount = amount.divide(new BigDecimal("100"), 2, 2);
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getClientTransactionId() {
        return clientTransactionId;
    }

    public void setClientTransactionId(String clientTransactionId) {
        this.clientTransactionId = clientTransactionId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(long statusCode) {
        this.statusCode = statusCode;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public long getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(long messageCode) {
        this.messageCode = messageCode;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getOptionalParameter1() {
        return optionalParameter1;
    }

    public void setOptionalParameter1(String optionalParameter1) {
        this.optionalParameter1 = optionalParameter1;
    }

    public String getOptionalParameter2() {
        return optionalParameter2;
    }

    public void setOptionalParameter2(String optionalParameter2) {
        this.optionalParameter2 = optionalParameter2;
    }

    public String getOptionalParameter3() {
        return optionalParameter3;
    }

    public void setOptionalParameter3(String optionalParameter3) {
        this.optionalParameter3 = optionalParameter3;
    }

    public String getOptionalParameter4() {
        return optionalParameter4;
    }

    public void setOptionalParameter4(String optionalParameter4) {
        this.optionalParameter4 = optionalParameter4;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRegionIso() {
        return regionIso;
    }

    public void setRegionIso(String regionIso) {
        this.regionIso = regionIso;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getRecap() {
        return recap;
    }

    public void setRecap(String recap) {
        this.recap = recap;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }


    public PayPhoneConfirmation() {
    }

    @Override
    public String toString() {
        return "PayPhoneConfirmation{" + "email=" + email
                + ", cardType=" + cardType
                + ", bin=" + bin
                + ", lastDigits=" + lastDigits
                + ", deferredCode=" + deferredCode
                + ", deferred=" + deferred
                + ", cardBrandCode=" + cardBrandCode
                + ", cardBrand=" + cardBrand
                + ", amount=" + amount
                + ", clientTransactionId=" + clientTransactionId
                + ", phoneNumber=" + phoneNumber
                + ", statusCode=" + statusCode
                + ", transactionStatus=" + transactionStatus
                + ", authorizationCode=" + authorizationCode
                + ", messageCode=" + messageCode
                + ", transactionId=" + transactionId
                + ", document=" + document
                + ", currency=" + currency
                + ", optionalParameter1=" + optionalParameter1
                + ", optionalParameter2=" + optionalParameter2
                + ", optionalParameter3=" + optionalParameter3
                + ", optionalParameter4=" + optionalParameter4
                + ", storeName=" + storeName
                + ", date=" + date
                + ", regionIso=" + regionIso
                + ", transactionType=" + transactionType
                + ", recap=" + recap
                + ", reference=" + reference
                + '}';
    }

}
