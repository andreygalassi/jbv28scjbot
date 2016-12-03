package br.com.fiap.t28scj.fundamentos.jbv28scjbot.bot;

import static br.com.fiap.t28scj.fundamentos.jbv28scjbot.utils.MyUtils.Configuracao.TOKEN;

import java.util.List;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetUpdatesResponse;

public class BotInit {

	public static void main(String[] args) {
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
				
				System.out.println(update.message().chat().firstName() + " " +
						"chamando comando " + update.message().text());
				
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

				case "/depositar":
					comandoAtual = update.message().text().split(" ")[0];
					comandos.depositar(bot, update);
					break;

				case "/saque":
					comandoAtual = update.message().text().split(" ")[0];
					comandos.saque(bot, update);
					break;

				default:
					if ("/criarconta".equals(comandoAtual)) {
						comandos.criarConta(bot, update);
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
