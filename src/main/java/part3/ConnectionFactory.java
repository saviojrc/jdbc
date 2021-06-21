package part3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.Utilitarios;

public class ConnectionFactory {

	private static String url;
	private static Connection con = null;
	private static String server;
	private static String bd;
	private static String className;
	private static String typeDriverBD;
	private static String userBD;
	private static String passwordBD;

	public static Connection getConnection() {
		try {

			definirVariaveisDeAmbienteDoBancoDeDados();

			Class.forName(className);
			if (con == null) {
				con = DriverManager.getConnection(url, userBD, passwordBD);
			}else if(Utilitarios.VerificaObjetoValido(con) && con.isClosed()) {
				con = DriverManager.getConnection(url, userBD, passwordBD);
			}
			

			return con;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e.getMessage());
		} catch (ClassNotFoundException y) {
			y.printStackTrace();
			throw new IllegalArgumentException(y.getMessage());
		}
	}

	private static void definirVariaveisDeAmbienteDoBancoDeDados() {
		server = Utilitarios.getStringProp("db.address");
		bd = Utilitarios.getStringProp("db.name");
		className = Utilitarios.getStringProp("jdbc.className");
		typeDriverBD = Utilitarios.getStringProp("jdbc.driver");
		userBD = Utilitarios.getStringProp("db.user.login");
		passwordBD = Utilitarios.getStringProp("db.password.login");
		url = new StringBuilder().append(typeDriverBD).append("://").append(server).append("/").append(bd).toString()
				.trim();
	}

	public static void closeConnection(Connection con) {
		try {
			if (Utilitarios.VerificaObjetoValido(con)) {
				con.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	public static void closeConnection(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if (Utilitarios.VerificaObjetoValido(con)) {
				con.close();
			}
			if (Utilitarios.VerificaObjetoValido(ps)) {
				ps.close();
			}
			if (Utilitarios.VerificaObjetoValido(rs)) {
				rs.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e.getMessage());
		}
	}

}
