package com.sabtok.entity;

public enum LinkageType {

    USER_STORY("USER_STORY"),
    INCIDENT("INCIDENT"),
    TOPIC("TOPIC"),
    REFERENCE("REFERENCE"),
    TEXT("TEXT"),
    DOCUMENT("DOCUMENT"),
    QUESTION("QUESTION"),
    VIDEO("VIDEO"),
    TASK("TASK"),
    SUB_TASK("SUB_TASK");

    private String linkType;

    LinkageType(String lnk) {
        this.linkType = lnk;
    }

    public String getLinkType() {
        return linkType;
    }
}
