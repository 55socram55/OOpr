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
public class CategoriaNaoInformadaException extends DadosDespesaIncompletosException{
    public CategoriaNaoInformadaException(){
        super("A categoria da despesa nao foi informada, por favor tente novamente");
    }
}
