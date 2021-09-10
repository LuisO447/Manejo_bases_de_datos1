/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dbprogra2b;

import datos.ClsAlumnoJDBC;
import dominio.ClsAlumno;
import java.sql.*;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luis
 */
public class ejemploPrincipal {
    public static void ej1(){
        //paso 1 definir string de conexion;
        
        String url = "jdbc:mysql://localhost:3306/db_alumnos?zeroDateTimeBehavior=convertToNull&useSSL=false&useTimezone=true&serverTimezone=UTC";
        
        try {
            //paso 2 crear el objeto de conexion
            Connection conexion = DriverManager.getConnection(url, "root", "1l2u3i4s"); //es una l minuscula y antes hay un uno
            
            //paso 3 crear el obj statement
            Statement sentencia = conexion.createStatement();
            
            //paso 4 creamos la instruccion sql
            String sql = "select * from db_alumnos1";
            
            //paso 5 ejecutamos el query
            ResultSet resultado = sentencia.executeQuery(sql);
            
            //paso 6 recorrer el resultado
            while(resultado.next()){
                System.out.println("id = "+resultado.getInt(1));
                System.out.println("Nobre = "+resultado.getNString(2));
                System.out.println("nota1 = "+resultado.getInt("nota1")); //se puede poner tambien 3 en vez de nota1
                System.out.println("nota2 = "+resultado.getInt("nota2"));
                
            }
            conexion.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
    }
    public static void main(String[] args) {
        //ej1();
        
        Scanner tecla = new Scanner(System.in);
        Scanner tecla2 = new Scanner(System.in);
        Scanner tecla3 = new Scanner(System.in);
        Scanner tecla4 = new Scanner(System.in);
        Scanner tecla5 = new Scanner(System.in);
        
        int opcion = 0;
        boolean salir = false;
        int opcionMenu = 0;
        
        ClsAlumnoJDBC alJDBC = new ClsAlumnoJDBC();
        ClsAlumno nuevo = new ClsAlumno();
        
        
        
        
        System.out.println("Bienvenido al programa de lista de alumnos");

        do{
            
            System.out.println("Ingresa una opcion porfavor:");
            System.out.println("1. Ver datos\n2. Ingresar datos\n3. Actualizar un dato\n4. Borrar un dato\n5. Salir");
            
            opcionMenu = tecla.nextInt();
            
            switch(opcionMenu){
                
                case 1:
                    
                    System.out.println("Aca estan tus datos: ");
                    List<ClsAlumno> todos = alJDBC.seleccion();
        
                    for(ClsAlumno alumno : todos){
                        System.out.println("Alumno = "+alumno);
                    }
                    
                    break;
                case 2:
                    
                    System.out.println("Ingresa los datos en orden: ");
                    System.out.println("Ingresa el nombre: ");
                    String nombreNuevo = tecla.next();
                    nuevo.setNombre(nombreNuevo);
                    System.out.println("Ingresa la nota 1: ");
                    int nota1Nueva = tecla2.nextInt();
                    nuevo.setNota1(nota1Nueva);
                    System.out.println("Ingresa la nota 2: ");
                    int nota2Nueva = tecla3.nextInt();
                    nuevo.setNota2(nota2Nueva);
                    alJDBC.insert(nuevo);
                    
                    
                    break;
                case 3:
                    
                    System.out.println("Pon los nuevos datos: ");
                    System.out.println("Introduce el nuevo nombre: ");
                    String nombreAct = tecla.next();
                    nuevo.setNombre(nombreAct);
                    System.out.println("Introduce la nueva nota 1: ");
                    int nota1Act = tecla.nextInt();
                    nuevo.setNota1(nota1Act);
                    System.out.println("Introduce la nueva nota 2: ");
                    int nota2Act = tecla.nextInt();
                    nuevo.setNota2(nota2Act);
                    System.out.println("Cual es el codigo de la persona a modificar??\nIntroduce el codigo: ");
                    int codigoMod = tecla.nextInt();
                    nuevo.setCodigo(codigoMod);
                    alJDBC.actualizar(nuevo);
                    
                    break;
                case 4:
                    
                    System.out.println("Que dato quieres borrar?? ");
                    System.out.println("Introduce el codigo de la persona a borrar: ");
                    int codigoBorrar = tecla.nextInt();
                    nuevo.setCodigo(codigoBorrar);
                    System.out.println("Introduce el nombre de la persona a borrar de la base de datos: ");
                    String nombreBorrar = tecla.next();
                    nuevo.setNombre(nombreBorrar);
                    System.out.println("Introduce la nota 1 para borrarla: ");
                    int nota1Borrar = tecla.nextInt();
                    nuevo.setNota1(nota1Borrar);
                    System.out.println("Introduce la nota 2 para borrarla: ");
                    int nota2Borrar = tecla.nextInt();
                    nuevo.setNota1(nota2Borrar);
                    alJDBC.borrar(nuevo);
                    
                    break;
                case 5:
                    
                    System.exit(0);
                    
                    break;
                default:
                    System.out.println("Esa opcion no esta disponible vuelve a intentar");
                
            }
            
        }while(salir=true);
        
        //seleccionar informacion
        
        
    }
}
