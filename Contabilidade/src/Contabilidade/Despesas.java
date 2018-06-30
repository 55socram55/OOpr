
package Contabilidade;

import java.util.LinkedList;
import javax.swing.JOptionPane;


public class Despesas {
    
    String desc;
    float valor;
    Categoria cat;
    
    LinkedList <Categoria> categorias= new LinkedList<>();
    
    public Despesas(String desc, float valor, Categoria cat){
         this.desc = desc;
         this.valor = valor;
         this.cat = cat;
    }
    
    public void cadastrarCategoria(){
        
          Categoria[] categoriasA = categorias.toArray(new Categoria[categorias.size()]);     
        
          String desc = JOptionPane.showInputDialog("Informe a descricao da categoria: ");
          
          Categoria tmp;
          
          Object resposta = JOptionPane.showInputDialog(null,               
					"Selecione a categoria a qual ela pertence:", 
					"Categoria", 
					JOptionPane.QUESTION_MESSAGE, 
					null, 
					categoriasA, 
                                        "NENHUMA");
          
          Categoria cat =  new Categoria(desc);
          categorias.add(cat);
          
          if(!("NENHUMA".equals((String)resposta))){
                                
              tmp = (Categoria)resposta;
              tmp.subC = cat;

            
          }
//          
//        Object[] opcoesPossiveis = {"--- Selecione uma opcao ---",
//				"Cadastrar contribuinte", 
//				                    "Cadastrar Rendimento", 
//				                    "Cadastrar Deducao",
//				                    "Cadastrar Dependente",
//				                    "Calcular imposto", 
//				                    "Sair"};
//		Object opcaoDefault = opcoesPossiveis[0];
//		Object opcaoSelecionada = null;
//		do {
			/*opcaoSelecionada =*/ 
//			switch ((String) opcaoSelecionada) {
//			case "Cadastrar contribuinte":
//				cadastrarContribuinte();
//			    break;
//			
//			case "Cadastrar Rendimento":
//				cadastrarRendimento();
//				break;
//
//			default:
//				break;
//			}
//		} while (!((String)opcaoSelecionada).equals((String)opcoesPossiveis[6]));
//	}

        
        
//        
//        if("".equals(nome)||"".equals(email)||"".equals(rendstr)){
//                  
//        }
//        float rend = Float.parseFloat(rendstr);
//        Categoria cat = new Categoria();
//        ocupantes.add(ocupante);
//        if (JOptionPane.showConfirmDialog(null, "Deseja cadastrar outro ocupante ?", "Confirmação",
//        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
//            cadastrarOcupante();
//        }
    }
    
    
}
