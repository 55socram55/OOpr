/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excecoes;

/**
 *
 * @author 9afff
 */
public class NenhumaRepublicaEncontradaException extends Exception{
    public NenhumaRepublicaEncontradaException(){
       super("Nenhuma Republica encontrada, por favor cadastre uma republica e tente novamente");
    }
    
}
