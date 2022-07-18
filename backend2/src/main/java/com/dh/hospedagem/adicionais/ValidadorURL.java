package com.dh.hospedagem.adicionais;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadorURL {

    public static boolean valida(String url){
        String regex = "((http|https)://)(www.)?"
                + "[a-zA-Z0-9@:%._\\+~#?&//=]"
                + "{2,256}\\.[a-z]"
                + "{2,6}\\b([-a-zA-Z0-9@:%"
                + "._\\+~#?&//=]*)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);

        // retorna true se a url e valida
        return matcher.matches();
    }

}
