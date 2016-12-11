package br.com.fiap.t28scj.fundamentos.jbv28scjbot.bot;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

import br.com.fiap.t28scj.fundamentos.jbv28scjbot.entity.Movimentacao;
import br.com.fiap.t28scj.fundamentos.jbv28scjbot.entity.Pessoa;
import br.com.fiap.t28scj.fundamentos.jbv28scjbot.entity.TipoConta;
import br.com.fiap.t28scj.fundamentos.jbv28scjbot.entity.TipoServico;
import br.com.fiap.t28scj.fundamentos.jbv28scjbot.utils.ContaUtils;
import br.com.fiap.t28scj.fundamentos.jbv28scjbot.utils.PessoaUtils;

public class BotCommands {

	private ContaUtils contaUtils;
	private PessoaUtils pessoaUtils;
	
	private Iterator<String> itMsgCriacaoConta;
	private Map<Integer, String> criacaoConta;
	private int indexMsgCriacaoConta;
	List<String> mensagensCriacaoConta;

	private Iterator<String> itMsgEmprestimo;
	private Map<Integer, String> emprestimo;
	private int indexMsgEmprestimo;
	List<String> mensagensEmprestimo;

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

		mensagensEmprestimo = Arrays.asList(
				"Ok, você está solicitando um emprestimo. \n"
				+ "O prazo máximo é de 3 anos e o valor máximo é %s.\n"
				+ "Para prosseguirmos digite o valor a ser contratado ou 'sair' para cancelar a operação. \n"
				+ "(Custo da operação R$15,00 e juros de 5%%a.m.)",
				"Informe o prazo em meses.");
		emprestimo = new HashMap<>();
		inicializarMsgsEmprestimo();
	}

	public void inicializarMsgsEmprestimo() {
		itMsgEmprestimo = mensagensEmprestimo.iterator();
		indexMsgEmprestimo = 0;
	}

	public void inicializarMsgsCriacaoConta() {
		itMsgCriacaoConta = mensagensCriacaoConta.iterator();
		indexMsgCriacaoConta = 0;
	}

//	public void definirComando(TelegramBot bot, Update update) {
//		if ("/start".equals(update.message().text())) {
//			this.start(bot, update);
//		}
//		if ("/criarconta".equals(update.message().text())) {
//			this.criarConta(bot, update);
//		} else {
//			bot.execute(
//					new SendMessage(update.message().chat().id(), "Comando não reconhecido. O que você quis dizer?"));
//		}
//	}
	
	public SendResponse start(TelegramBot bot, Update update) {
		SendResponse sendResponse = bot
				.execute(new SendMessage(update.message().chat().id(), "Bem vindo ao gerenciador Banco Virtual.\n \n"));
		sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "Serviços Disponíveis:\n"));
		sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "/criarconta - criar uma nova conta"));
		sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "/modificarconta - modificar conta"));
		sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "/inclusaodependentes - Incluir Dependente"));
		sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "/exibicaodadosconta - Exibição de Dados do titular e dos dependentes"));
		sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "/depositar - Depositar valor"));
		sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "/saque - Sacar valor. Custo R$2,50"));
		sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "/extrato - Mostra o extrato da conta. Custo R$1,00"));
		sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "/emprestimo - Opções para emprestimo"));
		return sendResponse;
	}

	public SendResponse criarConta(TelegramBot bot, Update update) {
		if (contaUtils.contaJaFoiCriada())
			return bot.execute(new SendMessage(update.message().chat().id(),
					"Você já criou uma conta, por favor solicite outro serviço"));
		SendResponse sendResponse = null;
		if (indexMsgCriacaoConta > 0) criacaoConta.put(indexMsgCriacaoConta, update.message().text());
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
	
	public SendResponse emprestimo(TelegramBot bot, Update update) {
		if (!contaUtils.contaJaFoiCriada())
			return bot.execute(new SendMessage(update.message().chat().id(),
					"Você ainda não criou uma conta, por favor solicite /criarconta"));
		
		SendResponse sendResponse = null;
		
		if (indexMsgEmprestimo > 0) emprestimo.put(indexMsgEmprestimo, String.format(update.message().text(),contaUtils.getConta().getSaldo()));
		indexMsgEmprestimo++;
		if (itMsgEmprestimo.hasNext()) {
			sendResponse = bot.execute(new SendMessage(update.message().chat().id(), String.format(itMsgEmprestimo.next(),
					new DecimalFormat("0.00").format(contaUtils.getConta().getSaldo().multiply(new BigDecimal("40"))))));
		} else {
			String valorStr = emprestimo.get(1);
			if (emprestimo.get(emprestimo.size()).equals("sair")){
//				inicializarMsgsEmprestimo();
				return bot.execute(new SendMessage(update.message().chat().id(),
						"Ok! Operação cancelada."));
			}
			BigDecimal valor = null;
			try {
				valor = new BigDecimal(valorStr);				
			} catch (Exception e) {
				return bot.execute(new SendMessage(update.message().chat().id(),
						"Não consegui entender o valor. Por Favor, repita a operação."));
			}
			String prazoStr = emprestimo.get(2);
			Integer prazo = 0;
			try {
				prazo = Integer.valueOf(prazoStr);
			} catch (Exception e) {
				return bot.execute(new SendMessage(update.message().chat().id(),
						"Não consegui entender o prazo. Por Favor, repita a operação."));
			}
			
			sendResponse = null;
			if (prazo > 36 || prazo < 1) {
				return bot.execute(new SendMessage(update.message().chat().id(),
						"O prazo deve estar entre 1 e 36 meses. Por Favor, repita a operação."));
			}
			if (valor.compareTo(contaUtils.getConta().getSaldo().multiply(new BigDecimal("40")))<=0){
				contaUtils.getConta().novoEmprestimo(valor,prazo);
				bot.execute(new SendMessage(update.message().chat().id(),
						"Parabens, valor contratado com sucesso."));
			}else{
				sendResponse = bot.execute(new SendMessage(update.message().chat().id(),
						"Essa quantia é maior que a quantida liberada para você. Defina outro valor."));
			}
			
		}
		
		return sendResponse;
	}
	
	public SendResponse extrato(TelegramBot bot, Update update) {
		if (!contaUtils.contaJaFoiCriada())
			return bot.execute(new SendMessage(update.message().chat().id(),
					"A conta ainda não foi criadao, digite /criarconta para criar uma nova conta."));
		SendResponse sendResponse = null;
		try {
			sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "EXTRATO:\n DATA - VALOR - TIPO - SERVICO - DESCRIÇÃO\n"+contaUtils.getConta().getMovimentacoes()));
			sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "SALDO TOTAL: "+contaUtils.getConta().getSaldo()));
		} catch (Exception e) {
			sendResponse = bot.execute(new SendMessage(update.message().chat().id(), e.getMessage()));
		}
		return sendResponse;
	}
	
	public SendResponse modificarconta(TelegramBot bot, Update update) {
		SendResponse sendResponse = null;
		return sendResponse;
	}
	
	public SendResponse inclusaodependentes(TelegramBot bot, Update update) {
		SendResponse sendResponse = null;
		return sendResponse;
	}
	
	public SendResponse depositar(TelegramBot bot, Update update) {
		if (!contaUtils.contaJaFoiCriada())
			return bot.execute(new SendMessage(update.message().chat().id(),
					"A conta ainda não foi criadao, digite /criarconta para criar uma nova conta."));
		SendResponse sendResponse = null;
		try {
			String valorStr = update.message().text().split(" ")[1];
			if (valorStr!=null && isNumeric(valorStr)){
				contaUtils.getConta().depositar(new BigDecimal(valorStr));
			}else{
				sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "Você deve passar um valor. ex: /depositar 100"));
				return sendResponse;
			}
			sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "Valor depositado."));
			sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "Saldo atual = "+contaUtils.getConta().getSaldo()));
			return sendResponse;			
		} catch (Exception e) {
			sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "Houve um erro ao processar o deposito. \nVocê deve passar um valor. \nex: /depositar 100"));
		}
		return sendResponse;
	}
	
	public SendResponse saque(TelegramBot bot, Update update) {
		if (!contaUtils.contaJaFoiCriada())
			return bot.execute(new SendMessage(update.message().chat().id(),
					"A conta ainda não foi criadao, digite /criarconta para criar uma nova conta."));
		String valorStr = update.message().text().split(" ")[1];
		SendResponse sendResponse = null;
		try {
			if (valorStr!=null && isNumeric(valorStr)){
				contaUtils.getConta().sacar(new BigDecimal(valorStr));
			}else{
				sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "Você deve passar um valor. ex: /sacar 100"));
				return sendResponse;
			}
			sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "Valor sacado."));
			sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "Saldo atual = "+contaUtils.getConta().getSaldo()));
		} catch (Exception e) {
			sendResponse = bot.execute(new SendMessage(update.message().chat().id(), e.getMessage()));
		}
		return sendResponse;
	}
	
	public SendResponse saldo(TelegramBot bot, Update update) {
		if (!contaUtils.contaJaFoiCriada())
			return bot.execute(new SendMessage(update.message().chat().id(),
					"A conta ainda não foi criadao, digite /criarconta para criar uma nova conta."));
		SendResponse sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "Saldo atual = "+contaUtils.getConta().getSaldo()));
		return sendResponse;
	}

	private static boolean isNumeric(String str)
	{
	  return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
	}

	public SendResponse emprestimoSaldoDevedor(TelegramBot bot, Update update) {
		if (!contaUtils.contaJaFoiCriada())
			return bot.execute(new SendMessage(update.message().chat().id(),
					"A conta ainda não foi criadao, digite /criarconta para criar uma nova conta."));
		SendResponse sendResponse = null;
		try {
			sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "SALDO DEVEDOR:\n EMPRESTIMO - VALOR - DT PAGAMENTO - SALDO DEVEDOR\n"+contaUtils.getConta().getEmprestimos()));
		} catch (Exception e) {
			sendResponse = bot.execute(new SendMessage(update.message().chat().id(), e.getMessage()));
		}
		return sendResponse;
	}

	public SendResponse listatarifas(TelegramBot bot, Update update) {
		if (!contaUtils.contaJaFoiCriada())
			return bot.execute(new SendMessage(update.message().chat().id(),
					"A conta ainda não foi criadao, digite /criarconta para criar uma nova conta."));
		
		List<Movimentacao> tarifas = contaUtils.getConta().getMovimentacoes().stream()
				.filter(m -> (m.getServico()==TipoServico.TARIFA_EMPRESTIMO || m.getServico()==TipoServico.TARIFA_SAQUE))
				.collect(Collectors.toList());
		
		BigDecimal total = BigDecimal.ZERO;
		for (Movimentacao t : tarifas) {
			total = total.add(t.getValor());
		}
		
		SendResponse sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "EXTRATO DE TARIFAS:\n DATA - VALOR - TIPO - SERVICO - DESCRIÇÃO\n"+tarifas));
		sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "TOTAL: "+total));
		return sendResponse;
	}
}
