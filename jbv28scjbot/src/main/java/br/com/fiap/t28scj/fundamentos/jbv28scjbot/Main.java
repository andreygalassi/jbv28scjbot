package br.com.fiap.t28scj.fundamentos.jbv28scjbot;

import static br.com.fiap.t28scj.fundamentos.jbv28scjbot.utils.MyUtils.Configuracao.TOKEN;

import java.math.BigDecimal;
import java.util.List;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;

public class Main {

	public static void main(String[] args) {
		
		BigDecimal val1 = BigDecimal.ZERO;
		String str = "Ok, você está solicitando um emprestimo. \n"
				+ "O prazo máximo é de 3 anos e o valor máximo é %s.\n"
				+ "Para prosseguirmos digite o valor a ser contratado ou 'sair' para cancelar a operação. \n"
				+ "(Custo da operação R$15,00 e juros de 5%% a.m.)";
		System.out.println(String.format(str, val1));

//		//Criação  do  objeto bot  com as  informações   de acesso
//		TelegramBot  bot = TelegramBotAdapter.build(TOKEN.getValor());
//		//objeto  responsável   por  receber as  mensagens
//		GetUpdatesResponse  updatesResponse;
//		//objeto  responsável   por  gerenciar  o  envio de  respostas
//		SendResponse  sendResponse;
//		//objeto  responsável   por  gerenciar  o  envio de  ações   do chat
//		BaseResponse baseResponse;
//		//controle de  off-set, isto  é, a partir  deste ID  será   lido  as  mensagens  pendentes   na  fila
//		int  m=0;
//		//loop  infinito  pode ser  alterado  por algum  timer  de intervalo  curto
//		while (true) {
//			//executa   comando no Telegram  para   obter as  mensagens pendentes   a partir  de  um off-set (limite  inicial)
//			updatesResponse =  bot.execute(new  GetUpdates().limit(100).offset(m));
//			//lista   de  mensagens
//			List<Update>  updates   =  updatesResponse.updates();
//			//análise  de cada   ação  da  mensagem
//			for (Update  update  : updates ) {
//				//atualização  do off-set
//				m  = update.updateId()+1;
//				System.out.println("Recebendo  mensagem:"+  update.message().text());
//				if ("/start".equals(update.message().text())){
//					sendResponse =  bot.execute(new SendMessage(update.message().chat().id(),"Bem vindo ao gerenciador Banco Virtual."));
//				}else{
//					//envio de  "Escrevendo"  antes  de enviar  a  resposta
//					baseResponse  =  bot.execute(new SendChatAction(update.message().chat().id(),  ChatAction.typing.name()));
//					//verificação  de  ação  de chat  foi   enviada   com sucesso
//					System.out.println("Resposta de Chat Action Enviada?"  + baseResponse.isOk());
//					//envio da mensagem  de  resposta 
//					sendResponse =  bot.execute(new SendMessage(update.message().chat().id(),"Não  entendi..."));
//					//verificação  de  mensagem  enviada   com sucesso
//					System.out.println("Mensagem Enviada?"  +sendResponse.isOk());					
//				}
//			}
//		
//		}
	}
}
