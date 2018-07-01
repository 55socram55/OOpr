
package Excecoes;


public class DadosPessoaisIncompletosException extends Exception{
    
    public DadosPessoaisIncompletosException(){
       
        super("Dados pessoais incompletos, por favor tente novamente preenchendo todos os campos");
        
    }

}
