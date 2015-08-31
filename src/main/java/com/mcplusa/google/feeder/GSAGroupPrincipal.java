package com.mcplusa.google.feeder;

import com.mcplusa.google.feeder.enums.ACLCaseSensitivityType;
import com.mcplusa.google.feeder.enums.ACLPrincipalScope;

public class GSAGroupPrincipal {
  private String namespace;
  private ACLCaseSensitivityType caseSensitivityType;
  private ACLPrincipalScope scope;
  private String content;
  
  public String getNamespace() {
    return namespace;
  }
  public void setNamespace(String namespace) {
    this.namespace = namespace;
  }
  
  public ACLCaseSensitivityType getCaseSensitivityType() {
    return caseSensitivityType;
  }
  public void setCaseSensitivityType(ACLCaseSensitivityType caseSensitivityType) {
    this.caseSensitivityType = caseSensitivityType;
  }
  public void setCaseSensitivity(boolean sensitive) {
    if (sensitive) {
      this.setCaseSensitivityType(ACLCaseSensitivityType.ACL_PRINCIPAL_CASE_SENSITIVE);
    } else {
      this.setCaseSensitivityType(ACLCaseSensitivityType.ACL_PRINCIPAL_CASE_INSENSITIVE);
    }
  }
  
  public ACLPrincipalScope getScope() {
    return scope;
  }
  public void setScope(ACLPrincipalScope scope) {
    this.scope = scope;
  }
  public void setScopeUser() {
    this.scope = ACLPrincipalScope.ACL_PRINCIPAL_SCOPE_USER;
  }
  public void setScopeGroup() {
    this.scope = ACLPrincipalScope.ACL_PRINCIPAL_SCOPE_GROUP;
  }
  
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
}
