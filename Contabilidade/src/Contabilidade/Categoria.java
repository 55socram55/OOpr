
package Contabilidade;


public class Categoria {
    
    String desc;
    Categoria subC;
    
    public Categoria(String desc){
      this.desc = desc;
    }
    
    public Categoria(String desc, Categoria subC){
       this.desc = desc;
       this.subC = subC;
    }
}