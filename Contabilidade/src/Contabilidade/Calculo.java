
package Contabilidade;
import Republica.*;
import java.util.Date;
import java.util.LinkedList;

public abstract class Calculo {
    
    protected LinkedList<Estudante> estudantes;
    protected LinkedList<Despesas> despesas;
    protected float totalDespesas;
    protected float totalRendimentos;
    protected Date data;
    
    protected abstract void calcular();
    
    protected  Calculo(LinkedList<Estudante> estudantes, LinkedList<Despesas> despesas, Date data){
       this.data = data;
       this.estudantes = estudantes ;
       this.despesas = despesas;
    }

}