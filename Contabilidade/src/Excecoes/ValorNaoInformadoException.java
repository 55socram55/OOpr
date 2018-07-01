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
public class ValorNaoInformadoException extends DadosDespesaIncompletosException{
    public ValorNaoInformadoException() {
        super("Valor da despesa nao informado, por favor tentar novamente");
    }
    
}
