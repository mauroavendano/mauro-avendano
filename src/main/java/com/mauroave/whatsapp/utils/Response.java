package com.mauroave.whatsapp.utils;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Response<T> {

    protected Date serverTime;
    private T result;
    private List<String> messages;
    private String path;
    private Boolean success;
    private Integer statusCode;
    private String statusMessage;

    protected Response(ResponseBuilder responseBuilder) {
        Objects.requireNonNull(responseBuilder);
        this.serverTime = responseBuilder.serverTime;
        this.success = responseBuilder.success;
        this.statusCode = responseBuilder.statusCode;
        this.statusMessage = responseBuilder.statusMessage;
        this.result = (T) responseBuilder.result;
        this.path = responseBuilder.path;
        this.messages = responseBuilder.messages;
    }

    public Response() {

    }


    public Date getServerTime() {
        return serverTime;
    }

    public void setServerTime(Date serverTime) {
        this.serverTime = serverTime;
    }


    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return the success
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    /**
     * @return the statusCode
     */
    public Integer getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode the statusCode to set
     */
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @return the statusMessage
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * @param statusMessage the statusMessage to set
     */
    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
