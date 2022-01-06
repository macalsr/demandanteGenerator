package com.next.demandanteGenerator.model;


public enum Sexo {
   FEMININO("F"),
   MASCULINO("M"),
   ABSTRATO("A");

private String sexo;

   Sexo(String sexo){
      this.sexo = sexo;
   }

   public String getSexo(){
      return sexo;
   }
//   CREATE TYPE mood AS ENUM ('sad', 'ok', 'happy');
}
