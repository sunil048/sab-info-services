package com.sabtok.entity;

public enum LinkageStatus {

    PENDING ("PENDING"),
    FAILED ("FAILED"),
    SUCCESS ("SUCCESS");

    private String status;

    LinkageStatus(String status) {
        this.status  = status;
    }

    public String getStatus() {
        return status;
    }
}
