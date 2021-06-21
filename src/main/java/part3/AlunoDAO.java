package part3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

	private List<Aluno> alunos = new ArrayList<>();
	private Aluno aluno;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public List<Aluno> listarAlunos() {
		try {
			String sql = obterListaDeAlunos();

			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				aluno = new Aluno(rs.getInt("ID"), rs.getString("NOME"), rs.getInt("IDADE"), rs.getString("ESTADO"));
				alunos.add(aluno);
			}

			return alunos;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new IllegalArgumentException(ex.getMessage());
		} finally {
			ConnectionFactory.closeConnection(con, ps, rs);
		}
	}

	public Aluno getById(Integer id) {
		try {
			String sql = buscarAlunoPorId();
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				aluno = new Aluno();
				aluno.setId(rs.getInt("id"));
				aluno.setNome(rs.getString("nome"));
				aluno.setIdade(rs.getInt("IDADE"));
				aluno.setEstado(rs.getString("estado"));

			}

			return aluno;

		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(con, ps, rs);
		}
	}

	public Aluno create(Aluno aluno) {
		try {
			String sql = inserirAluno();
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, aluno.getNome());
			ps.setInt(2, aluno.getIdade());
			ps.setString(3, aluno.getEstado());
			int rowsAffected = ps.executeUpdate();

			System.out.println("Inserção bem sucedida " + " " + "Foi adicionada" + " " + rowsAffected + " " + "linhas");
			return aluno;

		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(con);
		}
	}
	
	public Aluno delete(Integer id) {
		try {
			String sql =apagarAluno();
			con = ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			int rowsAffected = ps.executeUpdate();
			System.out.println("Operação bem sucedida " + " " + "foram apagadas " + " " + rowsAffected + " " + "linhas");
			return aluno;

		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(con);
		}
	}
	
	
	public Aluno update(Aluno aluno) {
		try {
			String sql =atualizarAluno();
			con = ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setString(1, aluno.getNome());
			ps.setInt(2, aluno.getIdade());
			ps.setString(3,aluno.getEstado());
			ps.setInt(4,aluno.getId());
			int rowsAffected = ps.executeUpdate();
			System.out.println("Operação bem sucedida " + " " + "foram atualizadas " + " " + rowsAffected + " " + "linhas");
			return aluno;

		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(con);
		}
	}
	
	
	private String atualizarAluno() {
		StringBuilder sql = new StringBuilder()
					.append(" UPDATE  ").append(" aluno ")
					.append(" SET  ").append(" NOME =?,  ")
					.append(" idade=?, ").append(" estado=? ")
					.append(" where  ").append(" id=? ")
					;
		return sql.toString();
	}

	private String apagarAluno() {
		StringBuilder sql = new StringBuilder().append(" DELETE FROM  ").append(" aluno ")
				.append(" WHERE   ").append(" ID =?  ");
		return sql.toString();
	}

	private String inserirAluno() {
		StringBuilder sql = new StringBuilder().append(" INSERT INTO ").append(" aluno ")
				.append(" (nome,idade,estado)  ").append(" values  ").append(" (?,?,?); ");
		return sql.toString();

	}

	private String buscarAlunoPorId() {
		String sql = "Select * from aluno where id =?";
		return sql;
	}

	private String obterListaDeAlunos() {
		String sql = new StringBuilder().append(" SELECT DISTINCT ").append("   A.ID, ").append(" A.NOME, ")
				.append(" A.IDADE, ").append(" A.ESTADO ").append(" FROM ALUNO a ").toString();
		return sql;
	}

}
