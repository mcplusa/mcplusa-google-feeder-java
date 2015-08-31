package com.mcplusa.google.feeder;

import com.mcplusa.google.feeder.enums.ACLCaseSensitivityType;
import com.mcplusa.google.feeder.enums.ACLInheritanceType;
import com.mcplusa.google.feeder.enums.ACLPrincipalAccess;
import com.mcplusa.google.feeder.enums.ACLPrincipalScope;
import java.util.ArrayList;
import java.util.List;

public class GSAACLItem {
    private String url;
    private ACLInheritanceType inheritanceType;
    private String inheritFrom;
    private List<GSAACLPrincipal> principals;
    
    public GSAACLItem() {
        this.principals = new ArrayList<GSAACLPrincipal>();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ACLInheritanceType getInheritanceType() {
        return inheritanceType;
    }

    public void setInheritanceType(ACLInheritanceType inheritanceType) {
        this.inheritanceType = inheritanceType;
    }

    public String getInheritFrom() {
        return inheritFrom;
    }

    public void setInheritFrom(String inheritFrom) {
        this.inheritFrom = inheritFrom;
    }

    public List<GSAACLPrincipal> getPrincipals() {
        return principals;
    }
    
    public void addUserPrincipal(String username, ACLPrincipalAccess access) {
        this.addUserPrincipal(username, access, 
                ACLCaseSensitivityType.ACL_PRINCIPAL_CASE_INSENSITIVE, 
                "Default");
    }

    public void addUserPrincipal(String username, ACLPrincipalAccess access, 
            ACLCaseSensitivityType caseSensitivity, String namespace) {
        this.addPrincipal(username, access, caseSensitivity, 
                namespace, ACLPrincipalScope.ACL_PRINCIPAL_SCOPE_USER);
    }
    
    public void addGroupPrincipal(String groupName, ACLPrincipalAccess access) {
        this.addGroupPrincipal(groupName, access, 
                ACLCaseSensitivityType.ACL_PRINCIPAL_CASE_INSENSITIVE, 
                "Default");
    }
    
    public void addGroupPrincipal(String groupName, ACLPrincipalAccess access, 
            ACLCaseSensitivityType caseSensitivity, String namespace) {
        this.addPrincipal(groupName, access, caseSensitivity, 
                namespace, ACLPrincipalScope.ACL_PRINCIPAL_SCOPE_GROUP);
    }
    
    private void addPrincipal(String name, ACLPrincipalAccess access, 
            ACLCaseSensitivityType caseSensitivity, String namespace, 
            ACLPrincipalScope scope) {
        GSAACLPrincipal principal = new GSAACLPrincipal();
        principal.setAccess(access);
        principal.setCaseSensitivityType(caseSensitivity);
        principal.setContent(name);
        principal.setNamespace(namespace);
        principal.setScope(scope);
        this.principals.add(principal);
    }
}
