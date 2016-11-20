package br.com.fiap.t28scj.fundamentos.jbv28scjbot.bot;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

import br.com.fiap.t28scj.fundamentos.jbv28scjbot.entity.Pessoa;
import br.com.fiap.t28scj.fundamentos.jbv28scjbot.entity.TipoConta;
import br.com.fiap.t28scj.fundamentos.jbv28scjbot.utils.ContaUtils;
import br.com.fiap.t28scj.fundamentos.jbv28scjbot.utils.PessoaUtils;

public class BotCommands {

	private ContaUtils contaUtils;
	private PessoaUtils pessoaUtils;
	private Iterator<String> itMsgCriacaoConta;
	private Map<Integer, String> criacaoConta;
	private int indexMsgCriacaoConta;

	List<String> mensagensCriacaoConta;

	public BotCommands() {
		contaUtils = new ContaUtils();
		pessoaUtils = new PessoaUtils();
		mensagensCriacaoConta = Arrays.asList(
				"Ok, você está criando uma nova conta. Para prosseguirmos necesitamos de algumas informações. Para começarmos digite seu nome.",
				"Nos diga seu endereço", "Digite seu CPF",
				"Calma, está terminando. Informe pra gente a data de nascimento no formato dd/mm/yyyy",
				"Para finalizar precisamos que você escolha seu tipo de conta\n Pessoa Física: Digite 1\n Pessoa Jurídica: Digite 2");
		criacaoConta = new HashMap<>();
		inicializarMsgsCriacaoConta();
	}

	public void inicializarMsgsCriacaoConta() {
		itMsgCriacaoConta = mensagensCriacaoConta.iterator();
		indexMsgCriacaoConta = 0;
	}

	public void definirComando(TelegramBot bot, Update update) {
		if ("/start".equals(update.message().text())) {
			this.start(bot, update);
		}
		if ("/criarconta".equals(update.message().text())) {
			this.criarConta(bot, update);
		} else {
			bot.execute(
					new SendMessage(update.message().chat().id(), "Comando não reconhecido. O que você quis dizer?"));
		}
	}

	public SendResponse start(TelegramBot bot, Update update) {
		SendResponse sendResponse = bot
				.execute(new SendMessage(update.message().chat().id(), "Bem vindo ao gerenciador Banco Virtual.\n \n"));
		sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "Serviços Disponíveis:\n"));
		sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "/criarconta - criar uma nova conta"));
		sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "/modificarconta - modificar conta"));
		sendResponse = bot
				.execute(new SendMessage(update.message().chat().id(), "/inclusaodependentes - Incluir Dependente"));
		sendResponse = bot.execute(new SendMessage(update.message().chat().id(),
				"/exibicaodadosconta - Exibição de Dados do titular e dos dependentes"));
		sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "/depositar - Depositar valor"));
		sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "/saque - Sacar valor"));
		return sendResponse;
	}

	public SendResponse criarConta(TelegramBot bot, Update update) {
		if (contaUtils.contaJaFoiCriada())
			return bot.execute(new SendMessage(update.message().chat().id(),
					"Você já criou uma conta, por favor solicite outro serviço"));
		SendResponse sendResponse = null;
		if (indexMsgCriacaoConta > 0)
			criacaoConta.put(indexMsgCriacaoConta, update.message().text());
		indexMsgCriacaoConta++;
		if (itMsgCriacaoConta.hasNext()) {
			sendResponse = bot.execute(new SendMessage(update.message().chat().id(), itMsgCriacaoConta.next()));
		} else {
			Pessoa pessoa = pessoaUtils.criarPessoa(criacaoConta.get(1), criacaoConta.get(2), criacaoConta.get(3),
					criacaoConta.get(4));
			contaUtils.criarConta(pessoa, TipoConta.getById(criacaoConta.get(5)));
			bot.execute(new SendMessage(update.message().chat().id(),
					"Parabéns " + criacaoConta.get(1) + ", sua conta foi criada com sucesso."));
		}
		return sendResponse;
	}

}
