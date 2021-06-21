package utils;

public class Utilitarios {

    public static Boolean VerificaObjetoValido(Object obj) {
        if (obj instanceof String) {
            return obj != null && String.valueOf(obj).length() > 0;
        } else {
            return obj != null;
        }
    }

    public static String getStringProp(String prop) {
        try {
            if (VerificaObjetoValido(prop)) {
                return Manipulador.getProp().getProperty(prop).replaceAll("/n", "").trim();
            }

            return "";
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }

}
