package com.mbsoft.verificadorproducto;

public class Producto {
    private String codigo;

    public Producto() {
    }

    public Producto(String cod) {
        codigo = cod;

    }

    /**
     * Indica si la sumatoria de los digitos del código geografico (o su recurrencia) es igual al digito verificador
     *
     * @param codigo
     * @return boolean : true si es igual
     */
    public static boolean verificar(String codigo) {
        int a = codigo.indexOf("-");
        int b = codigo.indexOf("-", a + 1);
        String crg = codigo.substring(a + 1, b);
        int dv = Character.getNumericValue(codigo.charAt(codigo.length() - 1));
        int sum = 0;
        do {
            if (crg.length() >= 2) {
                sum = 0;
            }
            for (int i = 0; i < crg.length(); i++) {
                int dig = Character.getNumericValue(crg.charAt(i));
                sum += dig;
            }
            crg = String.valueOf(sum);
        } while (sum >= 10);
        return sum == dv;
    }
}
