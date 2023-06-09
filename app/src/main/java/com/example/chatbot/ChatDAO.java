package com.example.chatbot;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ChatDAO {

    /*
    Orders the chats by the most recent message
     */
    @Query("SELECT * FROM chat ORDER BY lastMessageDate DESC")
    List<Chat> getAll();


    @Query("SELECT chatName FROM Chat, message WHERE message.chatID = :id AND message.chatId = chat.chatID")
    String getNameByID(int id);

    @Query("SELECT * FROM chat WHERE chatID = :chatId")
    Chat getChatById(int chatId);

    @Query("DELETE FROM message WHERE chatId = :chatId")
    void clearChat(int chatId);

    @Query("UPDATE chat SET lastMessageDate = :date WHERE chatID = :id ")
    void updateLastMessageDate(String date, int id);

    @Query("UPDATE chat SET lastMessage = :lastMessage WHERE chatID = :id ")
    void updateLastMessage(String lastMessage, int id);

    @Insert
    void insert(Chat chat);

    @Delete
    void delete(Chat chat);


}
