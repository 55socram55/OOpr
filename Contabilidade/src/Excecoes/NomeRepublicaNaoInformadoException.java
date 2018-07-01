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
public class NomeRepublicaNaoInformadoException extends Exception{
    public NomeRepublicaNaoInformadoException(){
         super("O nome da republica nao foi informado, por favor tentar novamente");
    }    
}
