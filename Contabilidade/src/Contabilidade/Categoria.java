
package Contabilidade;

import java.util.LinkedList;


public class Categoria {
    
    private String desc;
    private  LinkedList<Categoria> subCats = new LinkedList<>();
    
    protected Categoria(String desc){
      this.desc = desc;
    }

    protected String getDesc() {
        return desc;
    }

    protected  LinkedList<Categoria> getSubCats() {
        return subCats;
    }
    

    protected void setDesc(String desc) {
        this.desc = desc;
    }
    
    protected void addSubCat(Categoria cat){
         subCats.add(cat);
    }
         
    protected  Categoria procurarSubCat(String desc){
       
        Categoria resposta = null;
        for (Categoria tmp : subCats) {
            if(tmp.getDesc().equals(desc)){
                resposta = tmp;
            }
        }
        return resposta;
    }
    
}