package com.example.basemvp.common.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class CurrencyUtils {
    public static String convertFloatToCurrency(Float money) {
        try {
            NumberFormat formatter = new DecimalFormat("###,###");
            String resp = formatter.format(money);
            resp = resp.replaceAll(",", ".");
            return resp;
        } catch (Exception e) {
            return "";
        }
    }
}
