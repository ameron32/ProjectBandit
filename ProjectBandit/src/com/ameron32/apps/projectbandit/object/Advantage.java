package com.ameron32.apps.projectbandit.object;

import com.parse.ParseClassName;
import com.parse.ParseObject;


 @ParseClassName("CAdvGURPS")
public class Advantage extends ParseObject {
	
	/*
	 * OUTDATED:
	 * Advantages are created by the AdvantageEditor by loading a .CSV file into the system.
	 * MOST variables are imported through this process.
	 * Other variables are set by the user after the fact.
	 */
	
	 String advTypeString;
	 String aORd;
	 int calcCost;
	 String description;
	 int id;
	 boolean isExotic;
	 boolean isFakeCost;
	 boolean isForbidden;
	 boolean isLeveled;
	 boolean isMental;
	 boolean isMundane;
	 boolean isPhysical;
	 boolean isSocial;
	 boolean isSuper;
//	 String myNotes;
	 String nameString;
	 int pageInt;
	 String superTypeString;
	 String cost;
  int ver;

	boolean hasNotes;
	
	public Advantage() {
	  
	}

  public Advantage(int id, int ver, String aORd, String nameString,
			String advTypeString, String superTypeString, String cost,
			int pageInt, boolean isLeveled, boolean hasNotes,
			boolean isFakeCost, int calcCost, boolean isPhysical,
			boolean isMental, boolean isSocial, boolean isExotic,
			boolean isSuper, boolean isMundane, boolean isForbidden, String description) {
		setDetails(id, ver, aORd, nameString, advTypeString, superTypeString, cost, pageInt, isLeveled, hasNotes, isFakeCost, calcCost, isPhysical, isMental, isSocial, isExotic, isSuper, isMundane, isForbidden, description);
	}

  private void setDetails(int id,
      int ver, String aORd,
      String nameString,
      String advTypeString,
      String superTypeString,
      String cost, int pageInt,
      boolean isLeveled,
      boolean hasNotes,
      boolean isFakeCost, int calcCost,
      boolean isPhysical,
      boolean isMental,
      boolean isSocial,
      boolean isExotic,
      boolean isSuper,
      boolean isMundane,
      boolean isForbidden,
      String description) {
    this.id = id;
		this.ver = ver;
		this.aORd = aORd;
		this.nameString = nameString;
		this.advTypeString = advTypeString;
		this.superTypeString = superTypeString;
		this.cost = cost;
		this.pageInt = pageInt;
		this.isLeveled = isLeveled;
		this.hasNotes = hasNotes;
		this.isFakeCost = isFakeCost;
		this.calcCost = calcCost;
		this.isPhysical = isPhysical;
		this.isMental = isMental;
		this.isSocial = isSocial;
		this.isExotic = isExotic;
		this.isSuper = isSuper;
		this.isMundane = isMundane;
		this.isForbidden = isForbidden;
		this.description = description;
  }
	
	@Override
	public String toString() {
		return "Advantage [id=" + id + ", aORd=" + aORd + ", nameString="
				+ nameString + ", advTypeString=" + advTypeString
				+ ", superTypeString=" + superTypeString + ", cost=" + cost
				+ ", pageInt=" + pageInt + ", isLeveled=" + isLeveled
				+ ", hasNotes=" + hasNotes + ", isFakeCost=" + isFakeCost
				+ ", calcCost=" + calcCost + ", isPhysical=" + isPhysical
				+ ", isMental=" + isMental + ", isSocial=" + isSocial
				+ ", isExotic=" + isExotic + ", isSuper=" + isSuper
				+ ", isMundane=" + isMundane + ", isForbidden=" + isForbidden
				+ "]";
	}
	
	public void pullData() {
	  int calcCost = this.getInt("iCalcCost");
	  int id = this.getInt("iId");
	  String aORd = this.getString("sAorD");
	  String nameString = this.getString("sName");
	  String advTypeString = this.getString("sAdvType");
	  String superTypeString = this.getString("sSuperType");
//	  String cost = this.getString("iCost");
	  int pageInt = this.getInt("iPage");
	  boolean isLeveled = this.getBoolean("isLeveled");
//    boolean hasNotes = this.getBoolean("hasNotes");
	  boolean isFakeCost = this.getBoolean("isFakeCost");
//    boolean isPhysical = this.getBoolean("isPhysical");
//    boolean isMental = this.getBoolean("isMental");
//    boolean isSocial = this.getBoolean("isSocial");
//    boolean isExotic = this.getBoolean("isExotic");
//    boolean isSuper = this.getBoolean("isSuper");
//    boolean isMundane = this.getBoolean("isMundane");
	  boolean isForbidden = this.getBoolean("isForbidden");
    // FIXME I cheated to get the front and back off of the
    // description.
    // Once proper punctuation is handled, this will cause problems.
    String description = this.getString("sDescription");
//    .substring(3, this.getString("description").length() - 3);
    
    setPMSESM(this.getString("sListPMSESM"));
    setDetails(id, 0, aORd, nameString, advTypeString, superTypeString, "", pageInt, isLeveled, false, isFakeCost, calcCost, isPhysical, isMental, isSocial, isExotic, isSuper, isMundane, isForbidden, description);
	}
	
  private void setPMSESM(String list) {
//    boolean isPhysical;
//    boolean isMental;
//    boolean isSocial;
//    boolean isExotic;
//    boolean isSuper;
//    boolean isMundane;
    
    final String[] sTmp = list.split(";");
    final int[] iTmp = new int[sTmp.length];
    
    for (int i = 0; i < sTmp.length; i++) {
      final String t = sTmp[i];
      sTmp[i] = t.replace(";", "");
      iTmp[i] = Integer.valueOf(sTmp[i]);
      final boolean is = (iTmp[i] == 1);
      
      switch (i) {
      case 0:
        isPhysical = is;
        break;
      case 1:
        isMental = is;
        break;
      case 2:
        isSocial = is;
        break;
      case 3:
        isExotic = is;
        break;
      case 4:
        isSuper = is;
        break;
      case 5:
        isMundane = is;
        break;
      default:
        // unreachable
      }
    }
	}
}
