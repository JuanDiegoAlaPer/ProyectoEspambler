/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espambler.Vista;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author juand
 */
public class Compilacion {

    public Compilacion() {
    }
    
    public double compilar(String codigo, ArrayList<String> nombres, ArrayList<Character> tipos, ArrayList<String> valores, ArrayList<String> lineasEnumeradas){
        double acumulador = 0;
        ArrayList<String> lineas = new ArrayList<>();
        separarLineas(codigo,lineas); 
        Scanner entrada= new Scanner(System.in);
        
        for (int i = 0 ; i < lineas.size(); i++){
            int error = i + 1;
            String errorS = error+"";
            
            if (error <= 9)
                errorS+="  ";
            
            lineasEnumeradas.add(errorS+" "+lineas.get(i));
            
            switch (separarPalabras(lineas.get(i),0)) {
                case "declarar":
                    if (longitud(lineas.get(i)) != 3){
                        
                        if (longitud(lineas.get(i)) < 3)
                            System.out.println("Error en la línea: "+errorS+" Campos insuficientes");
                        else
                            System.out.println("Error en la línea: "+errorS+" Hay más campos de los necesarios o falta el tipo");
                    }
                    else{
                        declarar(separarPalabras(lineas.get(i),1), separarPalabras(lineas.get(i),2), nombres, tipos, valores, error);
                    }   
                break;
                    
                case "leer":
                    if (longitud(lineas.get(i)) != 2){
                        if (longitud(lineas.get(i)) < 2)
                            System.out.println("Error en la línea: "+errorS+" Campos insuficientes");
                        else
                            System.out.println("Error en la línea: "+errorS+" Hay más campos de los necesarios o variable mal escrita");
                    }
                    else{
                        if(buscarVariable(separarPalabras(lineas.get(i),1), nombres, error)){
                            System.out.println("Ingrese el valor para la variable "+separarPalabras(lineas.get(i),1)+":");
                            String valor = entrada.next();
                            if (validaNumero(valor, error))
                                leer(separarPalabras(lineas.get(i),1), valor, nombres, valores, error);
                        }
                    }   
                break;
                    
                case "cargar":
                    if (longitud(lineas.get(i)) != 2){
                        if (longitud(lineas.get(i)) < 2)
                            System.out.println("Error en la línea: "+errorS+" Campos insuficientes");
                        else
                            System.out.println("Error en la línea: "+errorS+" Hay más campos de los necesarios o variable mal escrita");
                    }
                    else{
                        if (empiezaEnCaracterLetra(separarPalabras(lineas.get(i),1))){
                            acumulador = cargarVariable(acumulador, separarPalabras(lineas.get(i),1), nombres, tipos, valores, error);
                        }
                        else{
                            if(validaNumero(separarPalabras(lineas.get(i),1), error))
                                acumulador = cargarKN(acumulador, Double.parseDouble(separarPalabras(lineas.get(i),1)), error);
                        }
                    }   
                break;
                    
                case "almacenar":
                    if (longitud(lineas.get(i)) != 2){
                        if (longitud(lineas.get(i)) < 2)
                            System.out.println("Error en la línea: "+errorS+" Campos insuficientes");
                        else
                            System.out.println("Error en la línea: "+errorS+" Hay más campos de los necesarios o variable mal escrita");
                    }
                    else{
                        almacenar(acumulador, separarPalabras(lineas.get(i),1), nombres, tipos, valores, error);
                    }   
                break;
                    
                case "sumar":
                    if (longitud(lineas.get(i)) != 2){
                        if (longitud(lineas.get(i)) < 2)
                            System.out.println("Error en la línea: "+errorS+" Campos insuficientes");
                        else
                            System.out.println("Error en la línea: "+errorS+" Hay más campos de los necesarios o variable mal escrita");
                    }
                    else{
                        acumulador = sumar(acumulador, separarPalabras(lineas.get(i),1), nombres, tipos, valores, error);
                    }   
                break;
                    
                case "restar":
                    if (longitud(lineas.get(i)) != 2){
                        if (longitud(lineas.get(i)) < 2)
                            System.out.println("Error en la línea: "+errorS+" Campos insuficientes");
                        else
                            System.out.println("Error en la línea: "+errorS+" Hay más campos de los necesarios o variable mal escrita");
                    }
                    else{
                        acumulador = restar(acumulador, separarPalabras(lineas.get(i),1), nombres, tipos, valores, error);
                    }   
                break;
                    
                case "multiplicar":
                    if (longitud(lineas.get(i)) != 2){
                        if (longitud(lineas.get(i)) < 2)
                            System.out.println("Error en la línea: "+errorS+" Campos insuficientes");
                        else
                            System.out.println("Error en la línea: "+errorS+" Hay más campos de los necesarios o variable mal escrita");
                    }
                    else{
                        acumulador = multiplicar(acumulador, separarPalabras(lineas.get(i),1), nombres, tipos, valores, error);
                    }   
                break;
                    
                case "dividir":
                    if (longitud(lineas.get(i)) != 2){
                        if (longitud(lineas.get(i)) < 2)
                            System.out.println("Error en la línea: "+errorS+" Campos insuficientes");
                        else
                            System.out.println("Error en la línea: "+errorS+" Hay más campos de los necesarios o variable mal escrita");
                    }
                    else{
                        acumulador = dividir(acumulador, separarPalabras(lineas.get(i),1), nombres, tipos, valores, error);
                    }   
                break;
                    
                case "mostrar":
                    if (longitud(lineas.get(i)) != 2){
                        if (longitud(lineas.get(i)) < 2)
                            System.out.println("Error en la línea: "+errorS+" Campos insuficientes");
                        else
                            System.out.println("Error en la línea: "+errorS+" Hay más campos de los necesarios o variable mal escrita");
                    }
                    else{
                        if (empiezaEnCaracterLetra(separarPalabras(lineas.get(i),1))){
                            mostrarVariable(separarPalabras(lineas.get(i),1), nombres, valores, error);
                        }
                        else{
                            mostrarKN(separarPalabras(lineas.get(i),1), error);
                        }
                    }   
                break;
                    
                default:
                    System.out.println("Error en la línea: "+errorS+" Funcion mal escrita o inexistente");
                break;
            }
        }
        return acumulador;
    }
    
    public boolean repetido(ArrayList<String> nombres, String aux){
        for (String f : nombres){
            if (aux.equals(f)){
                return false;
            }
        }
        return true;
    }
    
    public boolean empiezaEnCaracterLetra(String nombre){
        return nombre.charAt(0) == 'a'||nombre.charAt(0) == 'A'
                ||nombre.charAt(0) == 'b'||nombre.charAt(0) == 'B'
                ||nombre.charAt(0) == 'c'||nombre.charAt(0) == 'C'
                ||nombre.charAt(0) == 'd'||nombre.charAt(0) == 'D'
                ||nombre.charAt(0) == 'e'||nombre.charAt(0) == 'E'
                ||nombre.charAt(0) == 'f'||nombre.charAt(0) == 'F'
                ||nombre.charAt(0) == 'g'||nombre.charAt(0) == 'G'
                ||nombre.charAt(0) == 'h'||nombre.charAt(0) == 'H'
                ||nombre.charAt(0) == 'i'||nombre.charAt(0) == 'I'
                ||nombre.charAt(0) == 'j'||nombre.charAt(0) == 'J'
                ||nombre.charAt(0) == 'k'||nombre.charAt(0) == 'K'
                ||nombre.charAt(0) == 'l'||nombre.charAt(0) == 'L'
                ||nombre.charAt(0) == 'm'||nombre.charAt(0) == 'M'
                ||nombre.charAt(0) == 'n'||nombre.charAt(0) == 'N'
                ||nombre.charAt(0) == 'ñ'||nombre.charAt(0) == 'Ñ'
                ||nombre.charAt(0) == 'o'||nombre.charAt(0) == 'O'
                ||nombre.charAt(0) == 'p'||nombre.charAt(0) == 'P'
                ||nombre.charAt(0) == 'q'||nombre.charAt(0) == 'Q'
                ||nombre.charAt(0) == 'r'||nombre.charAt(0) == 'R'
                ||nombre.charAt(0) == 's'||nombre.charAt(0) == 'S'
                ||nombre.charAt(0) == 't'||nombre.charAt(0) == 'T'
                ||nombre.charAt(0) == 'u'||nombre.charAt(0) == 'U'
                ||nombre.charAt(0) == 'v'||nombre.charAt(0) == 'V'
                ||nombre.charAt(0) == 'w'||nombre.charAt(0) == 'W'
                ||nombre.charAt(0) == 'x'||nombre.charAt(0) == 'X'
                ||nombre.charAt(0) == 'y'||nombre.charAt(0) == 'Y'
                ||nombre.charAt(0) == 'z'||nombre.charAt(0) == 'Z';
    }
    
    public boolean declarar(String nombre, String tipo, ArrayList<String> nombres, ArrayList<Character> tipos, ArrayList<String> valores, int error){
        if (empiezaEnCaracterLetra(nombre)){
            
            if (repetido(nombres,nombre) == true){
                
                if(tipo.charAt(0) == 'i' || tipo.charAt(0) == 'f'){
                    nombres.add(nombre);
                    tipos.add(tipo.charAt(0));
                    if (tipo.charAt(0) == 'i')
                        valores.add("0");
                    else
                        valores.add("0.0");
                    return true;
                }
                else{
                    System.out.println("Error en la línea: "+error+" El tipo de la variable no es válido");
                }
            }
            else
                System.out.println("Error en la línea: "+error+" Ya hay una variable declarada con el nombre: "+nombre);
        }
        else{
            System.out.println("Error en la línea: "+error+" La variable debe de empezar por un caracter letra");
        }
        return false;
    }
    
    public boolean leer(String nombre, String valor, ArrayList<String> nombres, ArrayList<String> valores, int error){
        for (int i = 0; i < nombres.size(); i++){
            if (nombres.get(i).equals(nombre)){
                valores.set(i, valor);
                return true;
            } 
        }
        System.out.println("Error en la línea: "+error+" No se encontró la variable con el nombre: "+nombre);
        return false;
    }
    
    public double cargarVariable(double acumulador, String nombre, ArrayList<String> nombres, ArrayList<Character> tipos, ArrayList<String> valores, int error){
        for (int i = 0; i < nombres.size(); i++){
            if (nombres.get(i).equals(nombre)){
                if (tipos.get(i) == 'i'){
                    acumulador = Integer.parseInt(valores.get(i));
                    return acumulador;
                }
                else if (tipos.get(i) == 'f'){
                    acumulador = Double.parseDouble(valores.get(i));
                    return acumulador;
                }    
            }
        }
        System.out.println("Error en la línea: "+error+" No se encontró la variable con el nombre: "+nombre);
        return acumulador;
    }
    
    public double cargarKN(double acumulador, double valor, int error){
        acumulador = valor;
        return acumulador;
    }
    
    public boolean almacenar(double acumulador, String nombre, ArrayList<String> nombres, ArrayList<Character> tipos, ArrayList<String> valores, int error){
        for (int i = 0; i < nombres.size(); i++){
            if (nombres.get(i).equals(nombre)){
                if (tipos.get(i) == 'i'){
                    int aux = 0;
                    String acumuladorS = parseInt(acumulador+"");
                    aux = Integer.parseInt(acumuladorS);
                    valores.set(i, aux+"");
                    return true;
                }
                else if (tipos.get(i) == 'f'){
                    valores.set(i, acumulador+"");
                    return true;
                }
            }
        }
        System.out.println("Error en la línea: "+error+" No se encontró la variable con el nombre: "+nombre);
        return false;
    }
    
    public double sumar(double acumulador, String sumando, ArrayList<String> nombres, ArrayList<Character> tipos, ArrayList<String> valores, int error){
        if(empiezaEnCaracterLetra(sumando)){
            for (int i = 0; i < nombres.size(); i++){
                if (nombres.get(i).equals(sumando)){
                        if (tipos.get(i) == 'i'){
                            acumulador += Integer.parseInt(valores.get(i));
                            return acumulador;
                        }
                        else if (tipos.get(i) == 'f'){
                            acumulador += Double.parseDouble(valores.get(i));
                            return acumulador;
                        }
                }
            }
            System.out.println("Error en la línea: "+error+" No se encontró la variable con el nombre: "+sumando);
        }
        else{
            acumulador += Double.parseDouble(sumando);
            return acumulador;
        }
        return acumulador;
    }
    
    public double restar(double acumulador, String minuendo, ArrayList<String> nombres, ArrayList<Character> tipos, ArrayList<String> valores, int error){
        if(empiezaEnCaracterLetra(minuendo)){
            for (int i = 0; i < nombres.size(); i++){
                if (nombres.get(i).equals(minuendo)){
                    if (nombres.get(i).equals(minuendo)){
                        if (tipos.get(i) == 'i'){
                            acumulador -= Integer.parseInt(valores.get(i));
                            return acumulador;
                        }
                        else if (tipos.get(i) == 'f'){
                            acumulador -= Double.parseDouble(valores.get(i));
                            return acumulador;
                        }
                    }
                }
            }
            System.out.println("Error en la línea: "+error+" No se encontró la variable con el nombre: "+minuendo);
        }
        else{
            acumulador -= Double.parseDouble(minuendo);
            return acumulador;
        }
        return acumulador;
    }
    
    public double multiplicar(double acumulador, String factor, ArrayList<String> nombres, ArrayList<Character> tipos, ArrayList<String> valores, int error){
        if(empiezaEnCaracterLetra(factor)){
            for (int i = 0; i < nombres.size(); i++){
                if (nombres.get(i).equals(factor)){
                    if (nombres.get(i).equals(factor)){
                        if (tipos.get(i) == 'i'){
                            acumulador *= Integer.parseInt(valores.get(i));
                            return acumulador;
                        }
                        else if (tipos.get(i) == 'f'){
                            acumulador *= Double.parseDouble(valores.get(i));
                            return acumulador;
                        }
                    }
                }
            }
            System.out.println("Error en la línea: "+error+" No se encontró la variable con el nombre: "+factor);
        }
        else{
            acumulador *= Double.parseDouble(factor);
            return acumulador;
        }
        return acumulador;
    }
    
    public double dividir(double acumulador, String divisor, ArrayList<String> nombres, ArrayList<Character> tipos, ArrayList<String> valores, int error){
        if(empiezaEnCaracterLetra(divisor)){
            for (int i = 0; i < nombres.size(); i++){
                if (nombres.get(i).equals(divisor)){
                    if (nombres.get(i).equals(divisor)){
                        if (tipos.get(i) == 'i'){
                            if(Integer.parseInt(valores.get(i)) != 0){
                                acumulador /= Integer.parseInt(valores.get(i));
                                return acumulador;
                            }
                            else{
                                System.out.println("Error en la línea: "+error+" No se puede dividir entre 0");
                                return acumulador;
                            }
                        }
                        else if (tipos.get(i) == 'f'){
                            if(Double.parseDouble(valores.get(i)) != 0){
                                acumulador /= Double.parseDouble(valores.get(i));
                                return acumulador;
                            }
                            else{
                                System.out.println("Error en la línea: "+error+" No se puede dividir entre 0");
                                return acumulador;
                            }
                        }
                    }
                }
            }
            System.out.println("Error en la línea: "+error+" No se encontró la variable con el nombre: "+divisor);
        }
        else{
            if (Double.parseDouble(divisor) != 0){
                acumulador /= Double.parseDouble(divisor);
                return acumulador;
            }
            else{
                System.out.println("Error en la línea: "+error+" No se puede dividir entre 0");
                return acumulador;
            }
        }
        return acumulador;
    }
    
    public boolean mostrarVariable(String nombre, ArrayList<String> nombres, ArrayList<String> valores, int error){
        for (int i = 0; i < nombres.size(); i++){
            if (nombres.get(i).equals(nombre)){
                System.out.println(valores.get(i));
                return true;
            }
        }
        System.out.println("Error en la línea: "+error+" No se encontró la variable con el nombre: "+nombre);
        return false;
    }
    
    public boolean mostrarKN(String valor, int error){
        if(validaNumero(valor,error))
            System.out.println(valor);
        return true;
    }
    
    public String mostrarVariables(ArrayList<String> nombres, ArrayList<Character> tipos, ArrayList<String> valores){
        String answer = "";
        for (int i = 0; i < nombres.size(); i++){
            answer += nombres.get(i) + " " + tipos.get(i) + " " + valores.get(i) + "\n";
        }
        return answer;
    }
    
    public void separarLineas (String codigo, ArrayList<String> codeLines) {
        Collections.addAll(codeLines, codigo.split("\n"));
    }
    
    public String separarPalabras (String codigo, int posicion) {
        ArrayList<String> palabras = new ArrayList<>();
        Collections.addAll(palabras, codigo.split(" "));
        return palabras.get(posicion);
    }
    
    public int longitud(String codigo){
        ArrayList<String> palabras = new ArrayList<>();
        Collections.addAll(palabras, codigo.split(" "));
        return palabras.size();
    }
    
    public String parseInt (String codigo) {
        boolean decimales = false;
        String answer = "";
        for (int i = 0; i < codigo.length(); i++){
            if (codigo.charAt(i) == '.' || decimales){
                decimales = true;
            }
            else
                answer += codigo.charAt(i);
        }
        return answer;
    }
    
    public boolean buscarVariable(String nombre,  ArrayList<String> nombres, int error){
        for (int i = 0; i < nombres.size(); i++){
            if (nombres.get(i).equals(nombre)){
                return true;
            }
        }
        System.out.println("Error en la línea: "+error+" No se encontró la variable: "+nombre);
        return false;
    }
    
    public boolean validaNumero(String numero, int error){
        int contador = 0;
        
        if(numero.charAt(0) == '-')
            contador++;
        
        for (int i = 0; i < numero.length(); i++){
            if (numero.charAt(i) == '0' || numero.charAt(i) == '1'
                ||numero.charAt(i) == '2' || numero.charAt(i) == '3'
                ||numero.charAt(i) == '4' || numero.charAt(i) == '5'
                ||numero.charAt(i) == '6' || numero.charAt(i) == '7'
                ||numero.charAt(i) == '8' || numero.charAt(i) == '9'
                ||numero.charAt(i) == '.'){
                contador++;
            }
            
            if (contador == numero.length())
                return true;
        }
        System.out.println("Error en la línea: "+error+" El valor ingresado no es válido");
        return false;
    }
    
    public String imprimir(ArrayList<String> codigo){
        String answer = "";
        
        for(int i = 0; i < codigo.size(); i++){
            answer += codigo.get(i) + "\n";
        }
        
        return answer;
    }
}