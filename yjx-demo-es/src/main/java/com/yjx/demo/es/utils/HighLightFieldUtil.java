package com.yjx.demo.es.utils;

import org.elasticsearch.common.text.Text;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;

public class HighLightFieldUtil {

    public static String getValue(HighlightField field){
        String res = "";
        if(field == null || field.getFragments() == null){
            return res;
        }

        Text[] fragments = field.getFragments();
        for(int i=0; i<fragments.length; i++) {
            res+=fragments[i];
        }
        return res;
    }

}
