package com.juara.yayasan.Utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.DecimalFormat;

public class NumberFormatUtils {

    public static TextWatcher rupiahTextWatcher(final EditText editText) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = editable.toString();
                if (s.isEmpty()) return;
                editText.removeTextChangedListener(this);
                try {
                    String cleanString = formatOnlyNumber(s);
                    String formatted = formatRp(cleanString);
                    editText.setText(formatted);
                    editText.setSelection(formatted.length());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                editText.addTextChangedListener(this);
            }
        };
    }

    public static String formatOnlyNumber(String text) {
        if (text == null || text.equals("") || text.equals("00"))
            return "0";
        else
            return text.replaceAll("[^0-9]+", "");
    }

    public static String formatRp(String currency) {
        if (!currency.equals("")) {
            try {
                DecimalFormat formatter = new DecimalFormat("###,###,###");
                return "Rp. " + formatter.format(Double.parseDouble(currency));
            } catch (Exception e) {
                return currency;
            }
        }
        return "0";
    }
}
