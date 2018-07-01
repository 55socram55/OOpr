
package Contabilidade;

import Excecoes.*;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import Republica.Estudante;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Contabilidade {
    
    private LinkedList <Despesas> despesas = new LinkedList<>();
    private LinkedList <Estudante> estudantes;
    private Date data;
    
    public Contabilidade(LinkedList <Estudante> estudantes, Date data){
            this.data = data;
            this.estudantes = estudantes;
    }

    public void setEstudantes(LinkedList<Estudante> estudantes) {
        this.estudantes = estudantes;
    }
    
    
    public void cadastrarDespesa() throws DadosDespesaIncompletosException{
        
           String desc = JOptionPane.showInputDialog("Informe a descrição da despesa: ");
           if(desc.equals("")){
              throw new DescricaoNaoInformadaException();
           }
           String valorS = JOptionPane.showInputDialog("Informe o valor da despsa: ");
           if(valorS.equals("")){
              throw new ValorNaoInformadoException();
           }
           float valor = Float.parseFloat(valorS);
           
           
           LinkedList <Categoria> categoriasp = Despesas.getCategorias();
 
           if(categoriasp.isEmpty()){
               throw new CategoriaNaoInformadaException();
           }
           
           LinkedList <Categoria> categorias = new LinkedList<>();
           
           for(Categoria tmp : categoriasp){
               if((!tmp.getSubCats().isEmpty())){
                    categorias.addAll(tmp.getSubCats());
               }else{categorias.add(tmp);}
            }         
            
           LinkedList <String> descCategorias = new LinkedList<>();
                    
            for(Categoria tmp : categorias){
               descCategorias.add(tmp.getDesc());
            }
    
           String[] opcoesPossiveis = descCategorias.toArray(new String[descCategorias.size()]);
                  
          
           Object resposta = JOptionPane.showInputDialog(null,               
					"Selecione a categoria a qual ela pertence:", 
					"Categoria", 
					JOptionPane.QUESTION_MESSAGE, 
					null, 
					opcoesPossiveis, 
                                        opcoesPossiveis[0]);
           
           
           Categoria dcat = Despesas.procurarCategoria((String)resposta);
           Despesas desp =  new Despesas(desc,valor,dcat);
           despesas.add(desp);
           
           String[] options = new String[2];
           options[0] = "Sim";
           options[1] = "Não";  
            if (JOptionPane.showOptionDialog(null,"Deseja cadastrar outras despesas ?","Confirmação", 0,JOptionPane.INFORMATION_MESSAGE,null,options,null) == JOptionPane.YES_OPTION) {
            cadastrarDespesa();
            }
    
    }
    
    public void calcularIgualitaria(){
          
          Igualitaria calculo = new Igualitaria(estudantes, despesas, data);
          calculo.calcular();
    
    }
    public void calcularProporcional(){
          
          Proporcional calculo = new Proporcional(estudantes, despesas, data);
          calculo.calcular();
    
    }
    private int totalGasto(Categoria cat){
        int resposta = 0; 
        for(Despesas tmp: despesas){
             if(tmp.getCat().getDesc().equals(cat.getDesc())){
                 resposta += tmp.getValor();
             }
         }
        return resposta;
    }
    public void mostrarResumo(){
      
      float valor;
      DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
      String message = String.format("Resumo das despesas na data %s :\n\n",dateFormat.format(data));
      for(Categoria tmp: Despesas.getCategorias()){
          if(tmp.getSubCats().isEmpty()){
             valor = totalGasto(tmp);
             message += String.format("%s: R$%.2f\n",tmp.getDesc(),valor);
          }else{
             message += String.format("%s\n",tmp.getDesc());
             for(Categoria sub: tmp.getSubCats()){
                  valor = totalGasto(sub);
                  message += String.format("%-9s %s: R$%.2f\n","",sub.getDesc(),valor);
             }
             
          }

      }
       JOptionPane.showMessageDialog(null,message);
    
    }
    public void removerDespesas() throws DescricaoNaoInformadaException{
        
       String descD = JOptionPane.showInputDialog(null,"Digite a descricao da despesa a ser removida");
       if(descD.equals("")){
          throw new DescricaoNaoInformadaException();
       }
       
       Despesas resposta = null;
       for(Despesas tmp : despesas){
          if(tmp.getDesc().equals(descD)){resposta=tmp;}       
       }
       if(resposta==null){
          JOptionPane.showMessageDialog(null,"Despesa não encontrada, por favor tente novamente");
       }else{
          despesas.remove(resposta);
          JOptionPane.showMessageDialog(null,"Despesa removida");
       
       }
     
       String[] options = new String[2];
       options[0] = "Sim";
       options[1] = "Não";  
       if (JOptionPane.showOptionDialog(null,"Deseja remover outras despesas nessa mesma republica ?","Confirmação", 0,JOptionPane.INFORMATION_MESSAGE,null,options,null) == JOptionPane.YES_OPTION) {
         removerDespesas();
       }
    
    
    }

}
