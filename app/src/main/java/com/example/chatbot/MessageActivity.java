package com.example.chatbot;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class MessageActivity extends AppCompatActivity {

    private String chatKey = "chatIDkey";
    private MessageAdapter adapter;
    private EditText messageEditText;
    private FloatingActionButton sendMessageFAB;

    private FloatingActionButton backFB;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_activity);

        messageEditText = findViewById(R.id.messageEditText);
        sendMessageFAB = findViewById(R.id.sendMessageActionButton);
        backFB = findViewById(R.id.backfloatingActionButton);

        backFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToChatMenu(MessageActivity.this);
            }
        });

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        int chatId = bundle.getInt("chatIDKey");

        // obter uma referência para a RecyclerView que existe no layout da chatActivity
        RecyclerView recyclerView = findViewById(R.id.messagesRecyclerView);

        // obter uma instância do messageDAO
        AppDatabase db = AppDatabase.getInstance(this);
        ChatDAO chatDao = db.getChatDao();
        MessageDAO messageDAO = db.getMessageDao();

        // criar um objeto do tipo MessageAdapter (que extende Adapter)
        this.adapter = new MessageAdapter(messageDAO.getAll(chatId));

        // criar um objecto do tipo LinearLayoutManager para ser utilizado na RecyclerView
        // o LinearLayoutManager tem como orientação default a orientação Vertical
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        // Definir que a RecyclerView utiliza como Adapter o objeto que criámos anteriormente
        recyclerView.setAdapter(this.adapter);
        // Definir que a RecyclerView utiliza como LayoutManager o objeto que criámos anteriormente
        recyclerView.setLayoutManager(layoutManager);

        sendMessageFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int personSenderId = 0;


                String message = messageEditText.getText().toString();

                // Learned the method trim() in
                // https://stackoverflow.com/questions/3247067/how-do-i-check-that-a-java-string-is-not-all-whitespaces

                if (message.trim().length() > 0){
                    messageEditText.setText("");

                    Calendar calendar = Calendar.getInstance();
                    String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

                    int currentHour = calendar.get(Calendar.HOUR_OF_DAY);

                    String hour = currentHour + ":" + minuteFormater(calendar);

                    currentDate += " " + hour;

                    Message newMessage = new Message(0,chatId, personSenderId ,message,currentDate);

                    messageDAO.insert(newMessage);

                    db.getChatDao().updateLastMessageDate(currentDate,chatId);

                    List<Message> newMessageList = db.getMessageDao().getAll(chatId);
                    MessageActivity.this.adapter.refreshList(newMessageList);

                    botEco(chatId, currentDate, message, messageDAO, db);

                }
            }
        });
    }

    public static String minuteFormater(Calendar calendar){
        int currentMinute = calendar.get(Calendar.MINUTE);
        String minutes = Integer.toString(currentMinute);


        if(currentMinute < 10){
            minutes = "0" + currentMinute;
        }

        return minutes;
    }

    public void botEco(int chatId, String currentDate, String message, MessageDAO messageDAO, AppDatabase db){

        int botSenderId = 1;

        Message newMessage = new Message(0,chatId, botSenderId ,message,currentDate);

        messageDAO.insert(newMessage);

        db.getChatDao().updateLastMessageDate(currentDate,chatId);

        List<Message> newMessageList = db.getMessageDao().getAll(chatId);
        MessageActivity.this.adapter.refreshList(newMessageList);
    }


    public static void backToChatMenu(Context context){

        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);

    }
}
