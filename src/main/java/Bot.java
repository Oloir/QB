// import com.sun.org.apache.xpath.internal.operations.Quo;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Bot extends TelegramLongPollingBot{
    public static void main(String[] args) {


        //НАСТРОЙКИ ПРОКСИ ПРИ ВКЛЮЧЕННОМ ТОРЕ
        System.getProperties().put("proxySet", "true");
        System.getProperties().put("socksProxyHost", "127.0.0.1");
        System.getProperties().put("socksProxyPort", "9150");
        // ЗАПИХНУТЬ В КОНФИГ

        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());
        }
        catch (TelegramApiException e){
            e.printStackTrace();

        }
    }




    public void sendMsg(Message message, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
       try{
           setButtons(sendMessage);
           execute(sendMessage);
       }catch (TelegramApiException e){
           e.printStackTrace();
        }
    }


    public void onUpdateReceived(Update update) {

// СТАРАЯ МОДЕЛЬ
//        if(update.hasMessage()){
//            if(update.getMessage().hasText()){
//                if(update.getMessage().getText().equals("Hello")){
//                    try {
//                        execute(sendInlineKeyBoardMessage(update.getMessage().getChatId()));
//                    } catch (TelegramApiException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }else if(update.hasCallbackQuery()){
//            try {
//                execute(new SendMessage().setText(
//                        update.getCallbackQuery().getData())
//                        .setChatId(update.getCallbackQuery().getMessage().getChatId()));
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }


        ArrayList<String> List1 = new ArrayList<String>();
        Message message = update.getMessage();
        if(message !=null && message.hasText()){
            switch (message.getText()){
                case "Дай цитату!":
                    Quote QQ = new Quote();
                    sendMsg(message, QQ.getQuote());
                    break;
                default:
                    sendMsg(message, "Записал!");
                    FileWriter FW = null;
                    try {
                        FW = new FileWriter("C:/Users/Public/test.txt", true);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        FW.write("\n" + message.getText());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        FW.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        FW.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    List1.add(message.getText());
                    break;

            }
            }
        }




//Старая клавиатура

//    public static SendMessage sendInlineKeyBoardMessage(long chatId) {
//
//
//
//        Quote QuoM = new Quote();



//        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
//        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
//
//        inlineKeyboardButton1.setText("дай цитату!");
//        inlineKeyboardButton1.setCallbackData(QuoM.GetQuote());
//
//        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
//
//        keyboardButtonsRow1.add(inlineKeyboardButton1);
//
//
//        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
//        rowList.add(keyboardButtonsRow1);
//        inlineKeyboardMarkup.setKeyboard(rowList);
//        return new SendMessage().setChatId(chatId).setText("!!!").setReplyMarkup(inlineKeyboardMarkup);
//    }

// Новая клавиатура

    public void setButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowsList = new ArrayList<>();

        KeyboardRow firstRow = new KeyboardRow();

        firstRow.add(new KeyboardButton("Дай цитату!"));

        keyboardRowsList.add(firstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowsList);
    }







    public String getBotUsername() {
        return "QueBotBot";
    }


    public String getBotToken() {
        return "943087428:AAEoLmSLXJfsTHbkA-wRIEmhoGKb-8SPrxI";
    }
}