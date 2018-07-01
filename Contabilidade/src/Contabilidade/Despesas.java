
package Contabilidade;

import static Contabilidade.Despesas.categorias;
import Excecoes.DadosCategoriaIncompletosException;
import java.util.LinkedList;
import javax.swing.JOptionPane;


public class Despesas {
    
    private final String desc;
    private final float valor;
    private final Categoria cat;
    
    static LinkedList <Categoria> categorias= new LinkedList<>();
    
    protected Despesas(String desc, float valor, Categoria cat){
         this.desc = desc;
         this.valor = valor;
         this.cat = cat;
    }

    protected String getDesc() {
        return desc;
    }

    protected float getValor() {
        return valor;
    }

    protected Categoria getCat() {
        return cat;
    }
    
    protected static LinkedList<Categoria> getCategorias() {
        return categorias;
    }
    
    protected static Categoria procurarCategoria(String desc){   
        Categoria resposta = null;
        for (Categoria tmp : categorias) {
            if(tmp.getDesc().equals(desc)){
                resposta = tmp;
                return resposta;
            }
            if(!(tmp.getSubCats().isEmpty())){ 
              for(Categoria sub: tmp.getSubCats()){
                        if(sub.getDesc().equals(desc)){
                           resposta = sub;
                           return resposta;
                       }               
                }
            } 
            
        }
      
        return resposta;
    }
    
    public static void cadastrarCategoria() throws DadosCategoriaIncompletosException{
        

        
          LinkedList <String> descCategorias = new LinkedList<>();
                    
          for(Categoria tmp : categorias){
             descCategorias.add(tmp.getDesc());
          }
    
          descCategorias.addFirst("NENHUMA");
          String[] opcoesPossiveis = descCategorias.toArray(new String[descCategorias.size()]);
        
          String desc = JOptionPane.showInputDialog("Informe a descricao da categoria: ");
          if(desc.equals("")){
                throw new DadosCategoriaIncompletosException();
          }
          
          Object resposta = JOptionPane.showInputDialog(null,               
					"Selecione a categoria a qual ela pertence:", 
					"Categoria", 
					JOptionPane.QUESTION_MESSAGE, 
					null, 
					opcoesPossiveis, 
                                        opcoesPossiveis[0]);

          
          Categoria cat =  new Categoria(desc);
           
          Categoria tmp;
          if(!("NENHUMA".equals((String)resposta))){   
              tmp = procurarCategoria((String)resposta);
              tmp.addSubCat(cat);
            
          }else{categorias.add(cat);}
          
           String[] options = new String[2];
           options[0] = "Sim";
           options[1] = "Não";  
            if (JOptionPane.showOptionDialog(null,"Deseja cadastrar outra categoria ?","Confirmação", 0,JOptionPane.INFORMATION_MESSAGE,null,options,null) == JOptionPane.YES_OPTION) {
            cadastrarCategoria();
            }
    
    }
     public static void removerCategoria() throws DadosCategoriaIncompletosException{
    
       String descC = JOptionPane.showInputDialog(null,"Digite a descricao da categoria a ser removida");
       if(descC.equals("")){
           throw new DadosCategoriaIncompletosException();
       }
       Categoria resposta = null;
       for(Categoria tmp : categorias){
          if(tmp.getDesc().equals(descC)){resposta=tmp;}       
       }
       if(resposta==null){
          JOptionPane.showMessageDialog(null,"Categoria não encontrada, por favor tente novamente");
       }else{
          categorias.remove(resposta); 
          JOptionPane.showMessageDialog(null,"Categoria removida");
       }
     
       String[] options = new String[2];
       options[0] = "Sim";
       options[1] = "Não";  
       if (JOptionPane.showOptionDialog(null,"Deseja remover outras categorias ?","Confirmação", 0,JOptionPane.INFORMATION_MESSAGE,null,options,null) == JOptionPane.YES_OPTION) {
         removerCategoria();
       }
    
    
    
    }

    
}
