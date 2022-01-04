package com.next.demandanteGenerator.Service;

import com.google.gson.Gson;
import com.next.demandanteGenerator.model.Cidade;
import com.next.demandanteGenerator.utility.Util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class EnderecoService {

    static String webService = "http://viacep.com.br/ws/";
    static int codigoSucesso = 200;

    public static Cidade buscaEnderecoPelo(String cep) throws Exception{
        String urlParaChamada = webService + cep + "/json";

        try {
            URL url = new URL(urlParaChamada);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            if(connection.getResponseCode() != codigoSucesso)
                throw new RuntimeException("Http error code : " + connection.getResponseCode());

            BufferedReader resposta = new BufferedReader(new InputStreamReader((connection.getInputStream())));
            String jsonEmString = Util.converteJsonEmString(resposta);

            Gson gson = new Gson();

            Cidade cidade = gson.fromJson(jsonEmString, Cidade.class);
            return cidade;
        }catch (Exception e){
            throw new Exception("Erro: " + e);
        }
    }
}
