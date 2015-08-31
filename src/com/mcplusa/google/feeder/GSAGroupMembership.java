package com.mcplusa.google.feeder;

import java.util.ArrayList;
import java.util.List;

public class GSAGroupMembership {
  private GSAGroupPrincipal principal;
  private List<GSAGroupPrincipal> members;
  
  public GSAGroupMembership() {
    members = new ArrayList<GSAGroupPrincipal>();
  }
  
  public GSAGroupPrincipal getPrincipal() {
    return principal;
  }
  public void setPrincipal(GSAGroupPrincipal principal) {
    this.principal = principal;
  }
  
  public List<GSAGroupPrincipal> getMembers() {
    return members;
  }
  public void setMembers(List<GSAGroupPrincipal> members) {
    this.members = members;
  }
  public void addMember(GSAGroupPrincipal member) {
    this.members.add(member);
  }
}
