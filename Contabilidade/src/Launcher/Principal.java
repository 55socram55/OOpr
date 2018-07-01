/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Launcher;

import Contabilidade.Despesas;
import Excecoes.DadosCategoriaIncompletosException;
import Excecoes.DadosDespesaIncompletosException;
import Excecoes.DadosPessoaisIncompletosException;
import Excecoes.DescricaoNaoInformadaException;
import Excecoes.NenhumaRepublicaEncontradaException;
import Excecoes.NomeRepublicaNaoInformadoException;
import Republica.Republica;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author 9afff
 */
public class Principal {
    
    static LinkedList<Republica> republicas = new LinkedList<>();
    
    public static void main(String args[]){
        
     Object[] opcoesPossiveis = {"--- Selecione uma opcao ---",
				 "Cadastrar Republica",
                                 "Carregar dados externos",
				 "Cadastrar Ocupantes",
                                 "Cadastrar Categorias",
				 "Cadastrar Despesas",
                                 "Mostrar resumo das despesas",
				 "Calcular resultados via regra igualitaria",
				 "Calcular resultados via regra proporcional",
                                 "Remover Republica",
                                 "Remover Ocupantes",
                                 "Remover Categorias",
                                 "Remover Despesas",
                                 "Alterar nome de republicas",
                                 "Alterar dados de ocupantes",
				 "Sair"};
		Object opcaoDefault = opcoesPossiveis[0];
		Object opcaoSelecionada = null;
		do {
			opcaoSelecionada = JOptionPane.showInputDialog(null, 
											"Selecione uma das opcoes a seguir:", 
											"Contabilidade", 
											JOptionPane.QUESTION_MESSAGE, 
											null, 
											opcoesPossiveis, 
											opcaoDefault);
			switch ((String) opcaoSelecionada) {
			case "Cadastrar Republica":
			       cadastrarRepublica();
            		       break;
                        case "Carregar dados externos":
                               carregarDados();
                               break;
			case "Cadastrar Ocupantes":
			      cadastrarOcupantes();
                              break;
                        case "Cadastrar Categorias":
                              cadastrarCategoria();
                              break;
                        case "Cadastrar Despesas":
                              cadastrarDespesas();
                              break;
                        case "Mostrar resumo das despesas":
                              mostrarResumo();  
                              break;
                        case "Calcular resultados via regra igualitaria":
                              calcular(0);
                              break;
                        case "Calcular resultados via regra proporcional":
                              calcular(1);
                              break;
                        case "Remover Republica":
                              removerRepublica();
                              break;
                        case "Remover Ocupantes":
                              removerOcupantes();
                              break;
                        case "Remover Categorias":
                              removerCategorias();
                              break;
                        case "Remover Despesas":
                              removerDespesas();
                              break;
                        case "Alterar nome de republicas":
                              alterarRep();
                              break;
                        case "Alterar dados de ocupantes":
                              alterarOcp();
                              break;
			default:
			      break;
		}
		} while (!((String)opcaoSelecionada).equals((String)opcoesPossiveis[15]));
	}
      
      private static void cadastrarRepublica(){         
            String nome = JOptionPane.showInputDialog("Digite o nome da republica: ");
            Republica rep = new Republica(nome);
            republicas.add(rep);
            String[] options = new String[2];
            options[0] = "Sim";
            options[1] = "Não";  
            if (JOptionPane.showOptionDialog(null,"Deseja cadastrar outras republicas ?","Confirmação", 0,JOptionPane.INFORMATION_MESSAGE,null,options,null) == JOptionPane.YES_OPTION) {
            cadastrarRepublica();
            }
      }
      private static Republica procurarRepublica(String nome){
          
           Republica resposta = null;
           
           for(Republica tmp: republicas){
                
               if(tmp.getNome().equals(nome)){
                     resposta = tmp;
               }
               
           }
            
          return resposta;
      }
      private static Republica selecionarRepublica() throws NenhumaRepublicaEncontradaException{
          
          if(republicas.isEmpty()){
                throw new NenhumaRepublicaEncontradaException();
                     
          }
         
          LinkedList <String> nomeRepublicas = new LinkedList<>();
                    
          for(Republica tmp : republicas){
              nomeRepublicas.add(tmp.getNome());
          }
    
          String[] opcoesPossiveis = nomeRepublicas.toArray(new String[nomeRepublicas.size()]);
                  
          
          Object resposta = JOptionPane.showInputDialog(null,               
					"Selecione a republica a qual o mesmo pertence:", 
					"Republica", 
					JOptionPane.QUESTION_MESSAGE, 
					null, 
					opcoesPossiveis, 
                                        opcoesPossiveis[0]);
 
          Republica escolha = procurarRepublica((String)resposta);
          
          return escolha;
      }
      private static void cadastrarOcupantes(){
          
        Republica escolha;
        try {
            escolha = selecionarRepublica();
        } catch (NenhumaRepublicaEncontradaException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return;
        }
          try {
             escolha.cadastrarOcupante();
          } catch (DadosPessoaisIncompletosException ex) {
              JOptionPane.showMessageDialog(null,ex.getMessage());
              return;
          }
          
           String[] options = new String[2];
           options[0] = "Sim";
           options[1] = "Não";  
            if (JOptionPane.showOptionDialog(null,"Deseja cadastrar ocupantes em outras republicas ?","Confirmação", 0,JOptionPane.INFORMATION_MESSAGE,null,options,null) == JOptionPane.YES_OPTION) {
            cadastrarOcupantes();
            }
      
      }
      private static void cadastrarCategoria(){
          
          try{
          Despesas.cadastrarCategoria();
          }catch(DadosCategoriaIncompletosException ex){
              JOptionPane.showMessageDialog(null, ex.getMessage());
          }
      }
      private static void cadastrarDespesas(){
      
        Republica escolha;
        try {
            escolha = selecionarRepublica();
        } catch (NenhumaRepublicaEncontradaException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return;
        }
          escolha.cadastrarContabilidade();
          try {
            escolha.getCont().cadastrarDespesa();
          } catch (DadosDespesaIncompletosException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
          }
          
           String[] options = new String[2];
           options[0] = "Sim";
           options[1] = "Não";  
            if (JOptionPane.showOptionDialog(null,"Deseja cadastrar despesas em outras republicas ?","Confirmação", 0,JOptionPane.INFORMATION_MESSAGE,null,options,null) == JOptionPane.YES_OPTION) {
            cadastrarDespesas();
            }
                 
      }
      private static void calcular(int seletor){
          
        Republica escolha;
        try {
            escolha = selecionarRepublica();
        } catch (NenhumaRepublicaEncontradaException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return;
        }
          if(seletor ==0){
             //Regra igualitaria  
            escolha.getCont().calcularIgualitaria();   
          }else{escolha.getCont().calcularProporcional();}
      
      }
      private static void carregarDados(){
        Republica escolha;
        try {
            escolha = selecionarRepublica();
        } catch (NenhumaRepublicaEncontradaException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return;
        }
         escolha.carregarDados();
      }
      private static void mostrarResumo(){
          
        Republica escolha;
        try {
            escolha = selecionarRepublica();
        } catch (NenhumaRepublicaEncontradaException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return;
        }
         escolha.getCont().mostrarResumo();
      }
      private static void removerRepublica(){
        Republica escolha;
        try {
            escolha = selecionarRepublica();
        } catch (NenhumaRepublicaEncontradaException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return;
        }
          republicas.remove(escolha);
          JOptionPane.showMessageDialog(null,"Republica removida");
      }
      private static void removerOcupantes(){
        Republica escolha;
        try {
            escolha = selecionarRepublica();
        } catch (NenhumaRepublicaEncontradaException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return;
        }
        try {
            escolha.removerOcupantes();
        } catch (DadosPessoaisIncompletosException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
      }
      private static void removerCategorias(){
        try {
            Despesas.removerCategoria();
        } catch (DadosCategoriaIncompletosException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
          
      }
      private static void removerDespesas(){
        Republica escolha;
        try {
            escolha = selecionarRepublica();
        } catch (NenhumaRepublicaEncontradaException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return;
        }
        try {
            escolha.getCont().removerDespesas();
        } catch (DescricaoNaoInformadaException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
      }
      private static void alterarRep(){
        Republica escolha;
        try {
            escolha = selecionarRepublica();
        } catch (NenhumaRepublicaEncontradaException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return;
        }
        String nome = JOptionPane.showInputDialog("Informe o novo nome da republica");
        try {
            escolha.setNome(nome);
        } catch (NomeRepublicaNaoInformadoException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
            return;
        }
        JOptionPane.showMessageDialog(null,"Nome da republica alterado com sucesso");
        
      }
      private static void alterarOcp(){
        Republica escolha;
        try {
            escolha = selecionarRepublica();
        } catch (NenhumaRepublicaEncontradaException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return;
        }
        
        try {
            escolha.alterarOcp();
        } catch (DadosPessoaisIncompletosException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
      }
}