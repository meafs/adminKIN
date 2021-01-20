package com.sust.adminkinblood;

import java.util.Comparator;

public class Sorter implements Comparator<Dnr_Healper> {
    @Override
    public int compare(Dnr_Healper t1, Dnr_Healper t2) {

        String one = String.valueOf(t1.getDonateTimes());
        String two = String.valueOf(t2.getDonateTimes());

        return one.compareTo(two);
    }
}
