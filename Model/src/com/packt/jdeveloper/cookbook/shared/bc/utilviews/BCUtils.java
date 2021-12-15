package com.packt.jdeveloper.cookbook.shared.bc.utilviews;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import oracle.jbo.AttributeDef;
import oracle.jbo.Row;
import oracle.jbo.StructureDef;
import oracle.jbo.ViewCriteria;
import oracle.jbo.ViewCriteriaItem;
import oracle.jbo.ViewCriteriaItemValue;
import oracle.jbo.ViewObject;
import oracle.jbo.common.ViewCriteriaRowImpl;

public class BCUtils {
    public BCUtils() {
        super();
    }
    
    public static void printRow(ViewObject vo, Row r) {
        String viewObjName = vo.getName();
        System.out.println("Printing attribute for a row in VO '" + viewObjName + "'");
        StructureDef def = r.getStructureDef();
        StringBuilder sb = new StringBuilder();
        int numAttrs = def.getAttributeCount();
        AttributeDef[] attrDefs = def.getAttributeDefs();
        for (int z = 0; z < numAttrs; z++) {
            Object value = r.getAttribute(z);
            sb.append(z > 0 ? " " : "").append(attrDefs[z].getName()).append("=").append(value == null ? "<null>" :
                                                                                         value).append(z <
                                                                                                       numAttrs - 1 ?
                                                                                                       "\n" : "");
        }
        System.out.println(sb.toString());
    }
    
    public static void printVC(ViewCriteria vc) {  
         List list = vc.getRows();  
         Iterator iter = list.iterator();  
         while (iter.hasNext()) {  
           ViewCriteriaRowImpl row = (ViewCriteriaRowImpl)iter.next();  
           ViewCriteriaItem[] vcitems = row.getCriteriaItemArray();  
           for (ViewCriteriaItem vcitem : vcitems) {  
             if (vcitem != null) {  
               System.out.println("[ VC Item :" + vcitem.getColumnName() + "Operator " + vcitem.getOperator() + " Value: " + vcitem.getValue() + " ]");  
               if (vcitem.getValueCount() > 1) {  
                 ArrayList<ViewCriteriaItemValue> values = vcitem.getValues();  
                 for (ViewCriteriaItemValue vcItemValue : values)  
                   System.out.println(" [[ Multi select value :" + vcItemValue.getValue() + " ]]");  
               } else if ((vcitem.getValue()) instanceof ViewCriteria) {  
                 System.out.println("<Nested VC is found...>");  
                 //Call recursively   
                 printVC((ViewCriteria)vcitem.getValue());  
               }  
             }  
           }  
         }  
       } 
}
