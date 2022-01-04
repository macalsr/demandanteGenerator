package com.next.demandanteGenerator.utility;

import java.io.BufferedReader;
import java.io.IOException;

public class Util {

    public static String converteJsonEmString(BufferedReader bufferedReader) throws IOException{
        String resposta, jsomEmString = "";
        while ((resposta = bufferedReader.readLine())!= null){
            jsomEmString += resposta;
        }
        return jsomEmString;
    }
}
