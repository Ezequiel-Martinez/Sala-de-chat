
public class Usuario {
	
	private String id;
	private Sala sala;
	
	public Usuario(String id, Sala sala) {
		this.id = id;
		this.sala = sala;
	}
	
	public void setId(String id){
        this.id = id;
    }
	
	public String getId(){
        return this.id = id;
    }

    public Sala getSala(){
        return sala;
    }

    public void setSala(Sala sala){
        this.sala = sala;
    }
}
