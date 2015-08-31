package com.mcplusa.google.feeder.enums;

public enum ACLPrincipalScope {
    ACL_PRINCIPAL_SCOPE_USER("user"),
    ACL_PRINCIPAL_SCOPE_GROUP("group");
    
    private final String aclPrincipalScope;
    
    ACLPrincipalScope(String aclPrincipalScope) {
        this.aclPrincipalScope = aclPrincipalScope;
    }
    
    public String getValue() {
        return this.aclPrincipalScope;
    }
}
