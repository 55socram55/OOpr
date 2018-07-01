/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Contabilidade;

import Republica.Estudante;
import Republica.Republica;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author 9afff
 */
public class Igualitaria extends Calculo{
    
    private float parcela;

    protected Igualitaria(LinkedList<Estudante> estudantes, LinkedList<Despesas> despesas, Date data) {
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
        parcela = totalDespesas/estudantes.size();
        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String message = String.format("Data: %s\nPela regra Igualitaria cada ocupante ira pagar uma parte igual do total de despesas\n"
                                     + "Total a ser pago: R$%.2f \nTotal a ser pago por cada ocupante: R$%.2f\n"
                                     + "Total de rendimentos: R$%.2f",dateFormat.format(data),totalDespesas,parcela,totalRendimentos);
  
        JOptionPane.showMessageDialog(null,message);
        
    }
    
}