package com.mcplusa.google.feeder.enums;

public enum ACLCaseSensitivityType {
    ACL_PRINCIPAL_CASE_SENSITIVE("everything-case-sensitive"),
    ACL_PRINCIPAL_CASE_INSENSITIVE("everything-case-insensitive");
    
    private final String aclCaseSensitiveType;
    
    ACLCaseSensitivityType(String aclCaseSensitiveType) {
        this.aclCaseSensitiveType = aclCaseSensitiveType;
    }
    
    public String getValue() {
        return this.aclCaseSensitiveType;
    }
}
