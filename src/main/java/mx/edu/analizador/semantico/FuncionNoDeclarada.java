package mx.edu.analizador.semantico;

import mx.edu.analizador.lexico.TipoToken;
import mx.edu.analizador.lexico.Token;
import mx.edu.analizador.semantico.ErrorSemantico;

import java.util.ArrayList;
import java.util.List;

public class FuncionNoDeclarada {

    public List<ErrorSemantico> analizar(List<Token> tokens) {

        List<String> funciones = new ArrayList<>();
        List<ErrorSemantico> errores = new ArrayList<>();

        // se declara ya que estas se usan para mostrar informacion no como funciones
        funciones.add("printf");
        funciones.add("scanf");

        // se busca en la lista las funciones y se revisan las declaraciones y llamadas
        for (int i = 1; i < tokens.size() - 1; i++) {

         Token actual = tokens.get(i);
         Token anterior = tokens.get(i - 1);
         Token siguiente = tokens.get(i + 1);

         if (actual.tipo() == TipoToken.IDENTIFICADOR && siguiente.lexema().equals("(")) {
         String tipo = anterior.lexema();

         int parentesis = 0;
         int posicionCierre = -1;

         // buscar () y se guarda el ultimo )
         for (int j = i + 1; j < tokens.size(); j++) {
          if (tokens.get(j).lexema().equals("(")) {
          parentesis++;
          }

          if (tokens.get(j).lexema().equals(")")) {
          parentesis--;

          if (parentesis == 0) {
           posicionCierre = j;
            break;
            }
          }
         }


            if (posicionCierre != -1 && posicionCierre + 1 < tokens.size()) {
            String despues = tokens.get(posicionCierre + 1).lexema();

            if (tipo.equals("void") || tipo.equals("int") || tipo.equals("char") || tipo.equals("float") || tipo.equals("double") || tipo.equals("bool")) {

            if (despues.equals(";") || despues.equals("{")) {
            boolean existe = false;

            // se recorre la lista de funciones para comprobar si el nombre ya esta guardado
            for (int j = 0; j < funciones.size(); j++) {
            if (funciones.get(j).equals(actual.lexema())) {
            existe = true;
            break;

            }
         }

         // si la funcion no existe en la lista, se agrega su nombre
         if (existe == false) {
          funciones.add(actual.lexema());
          }
        }
         else {
         boolean existe = false;

         // se recorre la lista para buscar el nombre de la funcion actual y saber si se encuentra ya declaraada
         for (int j = 0; j < funciones.size(); j++) {
         if (funciones.get(j).equals(actual.lexema())) {
         existe = true;
         break;
         }
      }

         // si la funcion no existe en la lista, pues se agregar
          if (existe == false) {
          errores.add(new ErrorSemantico(
                  "Función no declarada",
                  "Funcion no declarada: " + actual.lexema(),
                   actual.linea(), actual.inicio(), actual.fin()));
          }
      }

    }
          else {
          boolean existe = false;

          // se recorre la lista para buscar el nombre de la funcion actual
          for (int j = 0; j < funciones.size(); j++) {
          if (funciones.get(j).equals(actual.lexema())) {
         existe = true;
         break;
         }
        }

        // si la funcion no existe en la lista, pues se agrega
        if (existe == false) {
        errores.add(new ErrorSemantico(
                "Función no declarada",
                "Funcion no declarada: " + actual.lexema(),
                actual.linea(), actual.inicio(), actual.fin()));
        }
       }
      }
     }
    }

        return errores;
    }
}