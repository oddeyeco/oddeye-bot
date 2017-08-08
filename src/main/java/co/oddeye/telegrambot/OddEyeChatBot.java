/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.oddeye.telegrambot;

import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendMessage;

/**
 *
 * @author vahan
 */
public class OddEyeChatBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().isCommand()) {
            String[] Commandparts = update.getMessage().getText().split("@");
            String Command = Commandparts[0];
            String bot = "";
            if (Commandparts.length > 1 && Commandparts[1] != null) {
                bot = Commandparts[1];
            }
            if (Command.equals("/getchatid")) //@oddeye_bot
            {
                SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields                        
                        .setChatId(update.getMessage().getChatId())
                        .setText("This Chat id: " + update.getMessage().getChatId());

                try {
                    if ((update.getMessage().isGroupMessage() || update.getMessage().isSuperGroupMessage()) && (!bot.isEmpty())&& (bot.equals("oddeye_bot"))) {                        
                        sendApiMethod(message);
//                        sendMessage(message); // Call method to send the message
                    }
                    if (update.getMessage().isUserMessage() && ((bot.isEmpty())||(bot.equals("oddeye_bot"))) ) {
                        sendApiMethod(message);
//                        sendMessage(message); // Call method to send the message
                    }
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @Override
    public String getBotUsername() {
        // TODO
        return "oddeye";
    }

    @Override
    public String getBotToken() {
        // TODO
        return "317219245:AAFqFjcddeXfrpIZ-V4ENeve87oxg0ZGGYs";
    }

    @Override
    public void onClosing() {
        super.onClosing(); //To change body of generated methods, choose Tools | Templates.
    }

}
