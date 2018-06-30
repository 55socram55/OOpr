
package Republica;

import Excecoes.DadosPessoaisIncompletos;
import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Republica {
    
    LinkedList<Estudante> ocupantes  = new LinkedList<>();
        
    public void print(){
      
        Iterator it = ocupantes.iterator();
        Estudante aux;
        while(it.hasNext()){
            aux = (Estudante) it.next();
            System.out.println(aux.nome);
        
        }
        File file = new File("registros.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String tmp;
            try {
                while((tmp = br.readLine())!= null){
                    System.out.println(tmp);
                }
            } catch (IOException ex) {
                Logger.getLogger(Republica.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Republica.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    public void cadastrarOcupante(){
      
        String nome = JOptionPane.showInputDialog("Informe o nome do ocupante: ");
        String email = JOptionPane.showInputDialog("Informe o email do ocupante");
        String rendstr = JOptionPane.showInputDialog("Informe o total de rendimentos do ocupante: ");
        if("".equals(nome)||"".equals(email)||"".equals(rendstr)){
            try {
                throw new DadosPessoaisIncompletos();
            } catch (DadosPessoaisIncompletos ex) {
                Logger.getLogger(Republica.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        if (JOptionPane.showConfirmDialog(null, "Deseja cadastrar outro ocupante ?", "Confirmação",
        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            cadastrarOcupante();
        }
    }
    
    public static void main(String args[]){
        
        Republica r = new Republica();   
        r.cadastrarOcupante();
        //r.print();
    
    }
    
}