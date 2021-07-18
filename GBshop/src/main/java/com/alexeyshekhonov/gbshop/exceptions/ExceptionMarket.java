package com.alexeyshekhonov.gbshop.exceptions;

import lombok.Data;

import java.util.Date;

@Data
public class ExceptionMarket {
    private int status;
    private String message;
    private Date timestamp;

    public ExceptionMarket(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }
}
