import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;


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
//        sendMessage.enableMarkdownV2(true); 
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
        Message message = update.getMessage();
        if(message !=null && message.hasText()){
            switch (message.getText()){
                case "Загрузить":
                    sendMsg(message, "ХОЧУ СКАЗАТЬ ЭТОМУ ОБЭМЕ");
                    break;
                default:
                    sendMsg(message, "УУУУУ СУКА");

            }
        }
    }


    public void setButtons(SendMessage sendMessage){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowsList = new ArrayList<>();

        KeyboardRow firstRow = new KeyboardRow();

        firstRow.add(new KeyboardButton("Загрузить"));
        firstRow.add(new KeyboardButton("Выгрузить"));

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