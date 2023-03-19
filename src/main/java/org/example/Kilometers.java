package org.example;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;
@Getter
@ToString
@Setter
@Slf4j
public class Kilometers {
    private List<List<String>> allKilometers = new ArrayList<>();
    private Set<Integer> kilometersAfterConvert = new HashSet<>();
    private List<Number> result = new LinkedList<>();

    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }


    void getAllRailwayLines() {
        for (List<String> kilometer : allKilometers) {
            List<String> collect = kilometer.stream().filter(Kilometers::isNumeric).toList();
            collect.forEach(a -> kilometersAfterConvert.add(Integer.valueOf(a)));
        }
    }

    void giveAllKilometers() throws ParseException {
        for (List<String> kilometer : allKilometers) {
            for (String s : kilometer) {
                String replace = s.replace(",", ".");
                NumberFormat format = NumberFormat.getInstance(Locale.US);

                Number number = null;
                if (isNumeric(replace)) {
                    number = format.parse(replace);
                    if (replace.contains(".")) {
                        double d = number.doubleValue();
                        result.add(d);
                    } else {
                        int i = number.intValue();
                        result.add(i);
                    }
                }
            }
        }
    }
}
