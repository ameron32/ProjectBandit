package com.ameron32.apps.projectbandit.object;

import android.util.Log;

import com.ameron32.lib.recyclertableview.TableAdapter.Columnable;
import com.parse.ParseClassName;
import com.parse.ParseObject;


 @ParseClassName("CAdv3GURPS")
public class Advantage extends AbsBanditObject<Advantage.Column> {
	
	/*
	 * OUTDATED:
	 * Advantages are created by the AdvantageEditor by loading a .CSV file into the system.
	 * MOST variables are imported through this process.
	 * Other variables are set by the user after the fact.
	 */
   

	
	public Advantage() {
	  // REQUIRED EMPTY CONSTRUCTOR (PARSE)
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder(); 
		sb.append("Advantage: ");
		for (Column c : columns) {
		  sb.append("\n  ");
		  sb.append(c.key);
		  sb.append(": ");
		  final String shorten = asString(c);
		  sb.append(shorten);
		}
		return sb.toString();
	}

  public static class Column extends AbsBanditObject.Column {
    
    public Column(String key, _DataType dataType) {
      super(key, dataType);
    }
  }
   
  private static final String SNAME = "sName";
  private static final String SDESCRIPTION = "sDescription";
  private static final String SID = "sId";
  private static final String IIDX = "iIdx";
  private static final String SADPQ = "sADPQ";
  private static final String SADVTYPE = "sAdvType";
  private static final String SSUPERTYPE = "sSuperType";
  private static final String SBOOKCOST = "sBookCost";
  private static final String IPAGE = "iPage";
  private static final String BISLEVELED = "bIsLeveled";
  private static final String BISMULTICOST = "bIsMultiCost";
  private static final String BISVARIABLECOST = "bIsVariableCost";
  private static final String IBASECOST = "iBaseCost";
  private static final String SMULTICOST = "sMultiCost";
  private static final String IPERLEVELCOST = "iPerLevelCost";
  private static final String SPERLEVELMULTICOST = "sPerLevelMultiCost";
  private static final String BHASNOTES = "bHasNotes";
  private static final String BISFAKECOST = "bIsFakeCost";
  private static final String ICALCCOST = "iCalcCost";
  private static final String SLISTPMSESM = "sListPMSESM";
  private static final String SREFS = "sRefs";
  private static final String SDOCUMENTSOURCE = "sDocumentSource";
  private static final String BISFORBIDDEN = "bIsForbidden";
  
  private static Column[] columns = {
    new Column(SNAME, _DataType.String),
    new Column(SDESCRIPTION, _DataType.String),
    new Column(SID, _DataType.String),
    new Column(IIDX, _DataType.Integer),
    new Column(SADPQ, _DataType.String),
    new Column(SADVTYPE, _DataType.String),
    new Column(SSUPERTYPE, _DataType.String),
    new Column(SBOOKCOST, _DataType.String),
    new Column(IPAGE, _DataType.Integer),
    new Column(BISLEVELED, _DataType.Boolean),
    new Column(BISMULTICOST, _DataType.Boolean),
    new Column(BISVARIABLECOST, _DataType.Boolean),
    new Column(IBASECOST, _DataType.Integer),
    new Column(SMULTICOST, _DataType.String),
    new Column(IPERLEVELCOST, _DataType.Integer),
    new Column(SPERLEVELMULTICOST, _DataType.String),
    new Column(BHASNOTES, _DataType.Boolean),
    new Column(BISFAKECOST, _DataType.Boolean),
    new Column(ICALCCOST, _DataType.Integer),
    new Column(SLISTPMSESM, _DataType.ListOfStrings),
    new Column(SREFS, _DataType.String),
    new Column(SDOCUMENTSOURCE, _DataType.String),
    new Column(BISFORBIDDEN, _DataType.Boolean)
  };
  
  @Override public Advantage.Column get(
      int columnPosition) {
    return columns[columnPosition];
  }

  @Override public int getColumnCount() {
    return columns.length;
  }
}
