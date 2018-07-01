
package Republica;


public class Estudante {
    
    public String nome;
    private String email;
    private float  totalRend;
    
    protected Estudante(String nome,String email, float totalRend){
        this.nome = nome;
        this.email = email;
        this.totalRend = totalRend;        
    }

    public String getNome() {
        return nome;
    }

    protected String getEmail() {
        return email;
    }

    public float getTotalRend() {
        return totalRend;
    }

    protected void setNome(String nome) {
        this.nome = nome;
    }

    protected void setEmail(String email) {
        this.email = email;
    }

    protected void setTotalRend(float totalRend) {
        this.totalRend = totalRend;
    }
    
    
    
}
