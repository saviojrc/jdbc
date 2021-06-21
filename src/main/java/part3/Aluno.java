package part3;

public class Aluno {

	private Integer id;
	private String nome;
	private Integer idade;
	private String estado;

	public Aluno(Integer id, String nome, Integer idade, String estado) {
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.estado = estado;
	}

	public Aluno(String nome, Integer idade, String estado) {
		this.nome = nome;
		this.idade = idade;
		this.estado = estado;
	}
	
	public Aluno() {
		
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEstado() {
		return estado;
	}

	@Override
	public String toString() {
		return "Aluno{" + "id=" + id + ", nome='" + nome + '\'' + ", idade=" + idade + ", estado='" + estado + '\''
				+ '}';
	}
}
