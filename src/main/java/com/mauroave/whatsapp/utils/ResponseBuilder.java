package com.mauroave.whatsapp.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResponseBuilder<T> {

    protected List<String> messages = new ArrayList<String>();
    protected String messageCode;
    protected String path;
    protected Boolean success;
    protected T result;
    protected Date serverTime;
    protected Integer statusCode;
    protected String statusMessage;

    public ResponseBuilder addMessage(String message){
        this.messages.add(message);
        return this;
    }
    public ResponseBuilder addMessages(List<String> message){
        if(message != null) {
            this.messages.addAll(message);
        }
        return this;
    }
    public ResponseBuilder success(Boolean success) {
        this.success = success;
        return this;
    }
    public ResponseBuilder path(String path) {
        this.path = path;
        return this;

    }
    public ResponseBuilder result(T result) {
        this.result = result;
        return this;

    }
    public ResponseBuilder statusCode(Integer statusCode) {
        this.statusCode = statusCode;
        return this;

    }
    public ResponseBuilder statusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
        return this;

    }
    public Response makeSuccessResponse(String path, T result) {
        this.success = true;
        this.statusCode = 200;
        this.statusMessage = "Success";
        this.addMessage("Operación ejecutada con exito");
        this.serverTime = new Date();
        this.path = path;
        this.result = result;
        return new Response<T>(this);
    }
    public Response makeSuccessResponse(String path) {
        return makeSuccessResponse(path, null);
    }
    public Response build() {
        this.serverTime = new Date();
        return new Response<T>(this);
    }
}
