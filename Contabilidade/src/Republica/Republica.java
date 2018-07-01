
package Republica;

import Contabilidade.*;
import Excecoes.DadosPessoaisIncompletosException;
import Excecoes.NomeRepublicaNaoInformadoException;
import java.io.*;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.util.Date;

public class Republica {
    
    private LinkedList<Estudante> ocupantes  = new LinkedList<>();
    private Contabilidade cont = null;
 
    private String nome;
    
    public Republica(String nome){
        this.nome = nome;
    }
    public String getNome(){
       return this.nome;
    }
    public void setNome(String nome) throws NomeRepublicaNaoInformadoException{
        if(nome.equals("")){
          throw new NomeRepublicaNaoInformadoException();
        }
        this.nome = nome;
    }
    public LinkedList<Estudante> getOcupantes() {
        return ocupantes;
    }

    public void carregarDados(){
        
        String[] dados;
        File file = new File("alunos.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String tmp;
            try {
                while((tmp = br.readLine())!= null){
                     dados = tmp.split(";");
                     cadastrarOcupante(dados[0],dados[1],Float.parseFloat(dados[2]));
                }
            } catch (IOException ex) {
                Logger.getLogger(Republica.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Arquivo de dados nao encontrado");
            return;
        }
        
        JOptionPane.showMessageDialog(null,"Dados carregados com sucesso");
    }
    public Contabilidade getCont(){
        return this.cont;
    }
    
    public void cadastrarOcupante() throws DadosPessoaisIncompletosException{
      
        String nome = JOptionPane.showInputDialog("Informe o nome do ocupante: ");
        String email = JOptionPane.showInputDialog("Informe o email do ocupante");
        String rendstr = JOptionPane.showInputDialog("Informe o total de rendimentos do ocupante: ");
        if("".equals(nome)||"".equals(email)||"".equals(rendstr)){   
                throw new DadosPessoaisIncompletosException();
        }
        float rend = Float.parseFloat(rendstr);
        Estudante ocupante = new Estudante(nome,email,rend);
        ocupantes.add(ocupante);
        try(FileWriter fw = new FileWriter("alunos.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println(nome+";"+email+";"+rend);
            out.close();
            bw.close();
            fw.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Erro: "+e);
        }
           String[] options = new String[2];
           options[0] = "Sim";
           options[1] = "Não";  
            if (JOptionPane.showOptionDialog(null,"Deseja cadastrar outros ocupantes nessa mesma republica ?","Confirmação", 0,JOptionPane.INFORMATION_MESSAGE,null,options,null) == JOptionPane.YES_OPTION) {
            cadastrarOcupante();
            }
    }
    
    public void cadastrarOcupante(String nome, String email, float rendt){
        //Método de cadastro usado pela função de carregar dados de arquivo
        Estudante ocupante = new Estudante(nome,email,rendt);
        ocupantes.add(ocupante);

    }
    
    public void cadastrarContabilidade(){
        
	Date data = new Date();            
        if(cont==null){
            cont = new Contabilidade(ocupantes,data);
        }      
    }
    
    public void removerOcupantes() throws DadosPessoaisIncompletosException{
       String nomeE = JOptionPane.showInputDialog(null,"Digite o nome do ocupante a ser removido");
       if(nomeE.equals("")){
          throw new DadosPessoaisIncompletosException();
       }
       
       Estudante resposta = null;
       for(Estudante tmp : ocupantes){
          if(tmp.getNome().equals(nomeE)){resposta=tmp;}       
       }
       if(resposta==null){
          JOptionPane.showMessageDialog(null,"Pessoa não encontrada, por favor tente novamente");
       }else{
          ocupantes.remove(resposta);
          if(cont!=null){
               cont.setEstudantes(ocupantes);
          }
          JOptionPane.showMessageDialog(null,"Ocupante removido");
       
       }
     
       String[] options = new String[2];
       options[0] = "Sim";
       options[1] = "Não";  
       if (JOptionPane.showOptionDialog(null,"Deseja remover outros ocupantes nessa mesma republica ?","Confirmação", 0,JOptionPane.INFORMATION_MESSAGE,null,options,null) == JOptionPane.YES_OPTION) {
         removerOcupantes();
       }
  
    } 
    public void alterarOcp() throws DadosPessoaisIncompletosException{
        
       String nomeE = JOptionPane.showInputDialog(null,"Digite o nome do ocupante a ser alterado");
       if(nomeE.equals("")){
          throw new DadosPessoaisIncompletosException();
       }
       
       Estudante resposta = null;
       for(Estudante tmp : ocupantes){
          if(tmp.getNome().equals(nomeE)){resposta=tmp;}       
       }
       if(resposta==null){
          JOptionPane.showMessageDialog(null,"Pessoa não encontrada, por favor tente novamente");
       }else{
           
         String novoN = JOptionPane.showInputDialog("Digite o novo nome do ocupante");
         if(novoN.equals("")){
             throw new DadosPessoaisIncompletosException();
         }
         String novoE = JOptionPane.showInputDialog("Digite o novo email do ocupante");
         if(novoE.equals("")){
             throw new DadosPessoaisIncompletosException();
         }
         String novoRS = JOptionPane.showInputDialog("Digite o novo rendimento do ocupante");
         if(novoRS.equals("")){
             throw new DadosPessoaisIncompletosException();
         } 
         float novoR = Float.parseFloat(novoRS);
         resposta.setNome(novoN);
         resposta.setEmail(novoE);
         resposta.setTotalRend(novoR);

         JOptionPane.showMessageDialog(null,"Dados alterados com sucesso");
       }
     
       String[] options = new String[2];
       options[0] = "Sim";
       options[1] = "Não";  
       if (JOptionPane.showOptionDialog(null,"Deseja alterar outros ocupantes nessa mesma republica ?","Confirmação", 0,JOptionPane.INFORMATION_MESSAGE,null,options,null) == JOptionPane.YES_OPTION) {
         alterarOcp();
       } 
    
    
    }

}