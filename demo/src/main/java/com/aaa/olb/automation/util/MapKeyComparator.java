package com.aaa.olb.automation.util;

import java.util.Comparator;

public class MapKeyComparator implements Comparator<Integer>{

	@Override
    public int compare(Integer str1, Integer str2) {
        
        return str1.compareTo(str2);
    }
}
