package com.projeto.api.service.Validation.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidaFormato {
    // CPF
    private static final int[] weightSsn = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

    // CNPJ
    private static final int[] weightTin = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    private static int calculate(final String str, final int[] weight) {
        int sum = 0;
        for (int i = str.length() - 1, digit; i >= 0; i--) {
            digit = Integer.parseInt(str.substring(i, i + 1));
            sum += digit * weight[weight.length - str.length() + i];
        }
        sum = 11 - sum % 11;
        return sum > 9 ? 0 : sum;
    }

    /**
     * Valida CPF
     *
     * @param ssn
     * @return
     */
    public static boolean isValidCPF(final String ssn) {
        final String cpf = ssn.replaceAll("[.-]", "");
        if ((cpf == null) || (cpf.length() != 11) || cpf.matches(cpf.charAt(0) + "{11}")) return false;
        final Integer digit1 = calculate(cpf.substring(0, 9), weightSsn);
        final Integer digit2 = calculate(cpf.substring(0, 9) + digit1, weightSsn);
        return cpf.equals(cpf.substring(0, 9) + digit1.toString() + digit2.toString());
    }

    /**
     * Valida CNPJ
     *
     * @param tin
     * @return
     */
    public static boolean isValidCNPJ(final String tin) {
        if ((tin == null) || (tin.length() != 14) || tin.matches(tin.charAt(0) + "{14}")) return false;

        final Integer digit1 = calculate(tin.substring(0, 12), weightTin);
        final Integer digit2 = calculate(tin.substring(0, 12) + digit1, weightTin);
        return tin.equals(tin.substring(0, 12) + digit1.toString() + digit2.toString());
    }

    public static boolean isValidEmail(String email) {
        boolean isEmailIdValid = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                isEmailIdValid = true;
            }
        }
        return isEmailIdValid;
    }
}
