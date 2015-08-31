package com.mcplusa.google.feeder.enums;

public enum ACLPrincipalAccess {
    ACL_PRINCIPAL_ACCESS_PERMIT("permit"),
    ACL_PRINCIPAL_ACCESS_DENY("deny");
    
    private final String aclPrincipalAccess;
    
    ACLPrincipalAccess(String aclPrincipalAccess) {
        this.aclPrincipalAccess = aclPrincipalAccess;
    }
    
    public String getValue() {
        return this.aclPrincipalAccess;
    }
}
