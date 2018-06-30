
package Excecoes;


public class DadosPessoaisIncompletos extends Exception{
    
    public DadosPessoaisIncompletos(){
       
        super("Dados pessoais incompletos, por favor preencher todos os campos novamente");
        
    }

}
