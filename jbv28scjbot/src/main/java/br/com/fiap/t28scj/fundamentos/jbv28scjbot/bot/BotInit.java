package br.com.fiap.t28scj.fundamentos.jbv28scjbot.bot;

import static br.com.fiap.t28scj.fundamentos.jbv28scjbot.utils.MyUtils.Configuracao.TOKEN;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetUpdatesResponse;

import br.com.fiap.t28scj.fundamentos.jbv28scjbot.utils.FileUtils;

public class BotInit {

	public static void main(String[] args) {
		FileUtils.escrever(
				"Serviço iniciado em " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + "\n");
		BotCommands comandos = new BotCommands();
		TelegramBot bot = TelegramBotAdapter.build(TOKEN.getValor());
		GetUpdatesResponse updatesResponse;
		int m = 0;
		String comandoAtual = null;
		while (true) {
			updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m));
			List<Update> updates = updatesResponse.updates();
			for (Update update : updates) {
				m = update.updateId() + 1;

				System.out.println(
						update.message().chat().firstName() + " " + "chamando comando " + update.message().text());

				switch (update.message().text().split(" ")[0]) {
				case "/start":
					comandoAtual = update.message().text();
					comandos.start(bot, update);
					break;

				case "/criarconta":
					comandoAtual = update.message().text();
					comandos.inicializarMsgsCriacaoConta();
					comandos.criarConta(bot, update);
					break;

				case "/modificarconta":
					comandoAtual = update.message().text();
					comandos.inicializarMsgsModificarConta();
					comandos.modificarConta(bot, update);
					break;

				case "/inclusaodependentes":
					comandoAtual = update.message().text();
					comandos.inicializarMsgsInclusaoDependentes();
					comandos.incluirDependente(bot, update);
					break;

				case "/exibicaodadosconta":
					comandoAtual = update.message().text();
					comandos.exibirDados(bot, update);
					break;

				case "/depositar":
					comandoAtual = update.message().text().split(" ")[0];
					comandos.depositar(bot, update);
					break;

				case "/saque":
					comandoAtual = update.message().text().split(" ")[0];
					comandos.saque(bot, update);
					break;

				case "/saldo":
					comandoAtual = update.message().text();
					comandos.saldo(bot, update);
					break;

				case "/extrato":
					comandoAtual = update.message().text();
					comandos.extrato(bot, update);
					break;

				case "/emprestimo":
					comandoAtual = update.message().text();
					comandos.inicializarMsgsEmprestimo();
					comandos.emprestimo(bot, update);
					break;

				case "/emprestimosaldodevedor":
					comandoAtual = update.message().text();
					comandos.emprestimoSaldoDevedor(bot, update);
					break;

				case "/retiradas":
					comandoAtual = update.message().text();
					comandos.listaretiradas(bot, update);
					break;

				case "/listatarifas":
					comandoAtual = update.message().text();
					comandos.listatarifas(bot, update);
					break;

				default:
					if ("/criarconta".equals(comandoAtual)) {
						comandos.criarConta(bot, update);
					} else if ("/modificarconta".equals(comandoAtual)) {
						comandos.modificarConta(bot, update);
					} else if ("/inclusaodependentes".equals(comandoAtual)) {
						comandos.incluirDependente(bot, update);
					} else if ("/emprestimo".equals(comandoAtual)) {
						comandos.emprestimo(bot, update);
					} else if ("/sair".equals(comandoAtual)) {
						comandoAtual = null;
					} else {
						bot.execute(new SendMessage(update.message().chat().id(),
								"Comando não reconhecido. O que você quis dizer?"));
					}
					break;
				}

			}

		}

	}

}
