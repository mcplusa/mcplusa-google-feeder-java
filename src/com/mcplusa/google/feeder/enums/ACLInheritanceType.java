package com.mcplusa.google.feeder.enums;

public enum ACLInheritanceType {
    ACL_INHERITANCE_TYPE_PARENT_OVERRIDES("parent-overrides"),
    ACL_INHERITANCE_TYPE_CHILD_OVERRIDES("child-overrides"),
    ACL_INHERITANCE_TYPE_AND_BOTH_PERMIT("and-both-permit"),
    ACL_INHERITANCE_TYPE_LEAF_NODE("leaf-node");
    
    private final String aclInheritanceType;
    
    ACLInheritanceType(String aclInheritanceType) {
        this.aclInheritanceType = aclInheritanceType;
    }
    
    public String getValue() {
        return this.aclInheritanceType;
    }
}