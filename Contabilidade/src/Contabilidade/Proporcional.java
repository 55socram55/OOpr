/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Contabilidade;

import Republica.Estudante;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author 9afff
 */
public class Proporcional extends Calculo{

    public Proporcional(LinkedList<Estudante> estudantes, LinkedList<Despesas> despesas, Date data) {
        super(estudantes, despesas, data);
    }

    @Override
    protected void calcular() {
        
        totalRendimentos = 0;
        totalDespesas = 0;
        
        for(Estudante tmp : estudantes){
            totalRendimentos += tmp.getTotalRend();
        }
        for(Despesas tmp : despesas){
            totalDespesas += tmp.getValor();
        }
        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String message = String.format("Data: %s\nPela regra Proporcional cada ocupante ira pagar uma parte das despesas proporcional aos seus ganhos \n"
                                       + "Total a ser pago: R$%.2f \nTotal de rendimentos: R$%.2f\n"
                                       + "Total a ser pago por cada ocupante:\n\n",dateFormat.format(data),totalDespesas,totalRendimentos);
        
        String parcelasS = "";
        float proporcao,parcela;
        
        for(Estudante tmp : estudantes){
            
            proporcao = tmp.getTotalRend()/totalRendimentos;
            parcela = totalDespesas*proporcao;
            parcelasS += String.format(tmp.getNome()+": R$%.2f (%.2f%%)\n",parcela,proporcao*100);
        }       
        
        JOptionPane.showMessageDialog(null,message+parcelasS);
        
    }
    
}
