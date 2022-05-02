package sms.model;

public class SMS {
    private String msisdn;
    private String recipient;
    private String sender;
    private String shortCode;
    private String transactionId;
    private String timestamp;
    private String promo;
    private String message;
    private boolean success;

    public String getMsisdn(){
        return msisdn;
    }
    public void setMsisdn(String msisdn){
        this.msisdn = msisdn;
    }

    public String getRecipient(){
        return recipient;
    }

    public void setRecipient(String recipient){
        this.recipient = recipient;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}