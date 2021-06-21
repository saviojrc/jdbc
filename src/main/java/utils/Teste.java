package utils;

import part3.AlunoDAO;

public class Teste {

	public static void main(String[] args) {

		AlunoDAO daoAluno = new AlunoDAO();
		daoAluno.listarAlunos().stream().forEach(System.out::println);

	}

}
