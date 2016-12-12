package br.com.fiap.t28scj.fundamentos.jbv28scjbot.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {

	private final static String ARQUIVO;
	private static BufferedWriter bufferedWriter;

	static {
		ARQUIVO = "minha_conta_ " + System.currentTimeMillis() + ".txt";
		criarArquivo();
	}

	private static void criarArquivo() {
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(ARQUIVO));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void escrever(String conteudo) {

		try {
			bufferedWriter.append(conteudo);
			bufferedWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
