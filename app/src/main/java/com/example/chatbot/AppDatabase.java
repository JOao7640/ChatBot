package com.example.chatbot;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Chat.class, Message.class, Exercise.class, ExerciseAnswers.class, Output.class, KnownInput.class }, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ChatDAO getChatDao();
    public abstract MessageDAO getMessageDao();
    public abstract ExerciseDao getExerciseDao();
    public abstract ExerciseAnswersDao getExerciseAnswersDao();
    public abstract OutputDao getOutputDao();
    public abstract KnownInputDao getKnowninputDao();
    private static AppDatabase INSTANCE;


    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "AppDatabase").allowMainThreadQueries()
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);

                            // KnownInput
                            db.execSQL("INSERT INTO KnownInput('inputId', 'input', 'returnExercise') VALUES(0, 'hello', 'false')");
                            db.execSQL("INSERT INTO KnownInput('inputId', 'input', 'returnExercise') VALUES(1, 'hi', 'false')");
                            db.execSQL("INSERT INTO KnownInput('inputId', 'input', 'returnExercise') VALUES(2, 'hey', 'false')");
                            db.execSQL("INSERT INTO KnownInput('inputId', 'input', 'returnExercise') VALUES(3, 'how are you?', 'false')");
                            db.execSQL("INSERT INTO KnownInput('inputId', 'input', 'returnExercise') VALUES(4, 'how have you been?', 'false')");
                            db.execSQL("INSERT INTO KnownInput('inputId', 'input', 'returnExercise') VALUES(5, 'good', 'false')");
                            db.execSQL("INSERT INTO KnownInput('inputId', 'input', 'returnExercise') VALUES(6, 'i am not feeling good', 'false')");
                            db.execSQL("INSERT INTO KnownInput('inputId', 'input', 'returnExercise') VALUES(7, 'i am feeling bad', 'false')");
                            db.execSQL("INSERT INTO KnownInput('inputId', 'input', 'returnExercise') VALUES(8, 'i am sad', 'false')");
                            db.execSQL("INSERT INTO KnownInput('inputId', 'input', 'returnExercise') VALUES(9, 'bad', 'false')");
                            db.execSQL("INSERT INTO KnownInput('inputId', 'input', 'returnExercise') VALUES(10, 'give me a exercise', 'true')");
                            db.execSQL("INSERT INTO KnownInput('inputId', 'input', 'returnExercise') VALUES(11, 'ask me something', 'true')");
                            db.execSQL("INSERT INTO KnownInput('inputId', 'input', 'returnExercise') VALUES(12, 'ask me about java', 'true')");


                            // Output

                            db.execSQL("INSERT INTO Output VALUES(0, 0 ,'hi')");
                            db.execSQL("INSERT INTO Output VALUES(1, 0 ,'Hey')");
                            db.execSQL("INSERT INTO Output VALUES(2, 0 ,'hello, how are you?')");
                            db.execSQL("INSERT INTO Output VALUES(3, 1 ,'hi')");
                            db.execSQL("INSERT INTO Output VALUES(4, 1 ,'Hey')");
                            db.execSQL("INSERT INTO Output VALUES(5, 1 ,'hello, how are you?')");
                            db.execSQL("INSERT INTO Output VALUES(6, 2 ,'hi')");
                            db.execSQL("INSERT INTO Output VALUES(7, 2 ,'Hey')");
                            db.execSQL("INSERT INTO Output VALUES(8, 2 ,'hello, how are you?')");
                            db.execSQL("INSERT INTO Output VALUES(9, 3 ,'Good and you?')");
                            db.execSQL("INSERT INTO Output VALUES(10, 3 ,'I need some coffee...oh wait')");
                            db.execSQL("INSERT INTO Output VALUES(11, 3 ,'Im feeling good, can i help you with something?')");
                            db.execSQL("INSERT INTO Output VALUES(12, 4 ,'Im good :)')");
                            db.execSQL("INSERT INTO Output VALUES(13, 4 ,'Good and you?')");
                            db.execSQL("INSERT INTO Output VALUES(14, 4 ,'Great, can i help you?')");
                            db.execSQL("INSERT INTO Output VALUES(15, 5 ,'Great')");
                            db.execSQL("INSERT INTO Output VALUES(16, 5 ,'Nice')");
                            db.execSQL("INSERT INTO Output VALUES(17, 5 ,'Good, can i help you with something?')");
                            db.execSQL("INSERT INTO Output VALUES(18, 6 ,'Im sorry to hear that')");
                            db.execSQL("INSERT INTO Output VALUES(19, 6 ,'Thats sad, can i help you with something?')");
                            db.execSQL("INSERT INTO Output VALUES(20, 6 ,'Im really sorry')");
                            db.execSQL("INSERT INTO Output VALUES(21, 7 ,'Im sorry to hear that')");
                            db.execSQL("INSERT INTO Output VALUES(22, 7 ,'Thats sad, can i help you with something?')");
                            db.execSQL("INSERT INTO Output VALUES(23, 7 ,'Im really sorry')");
                            db.execSQL("INSERT INTO Output VALUES(24, 8 ,'Im sorry to hear that')");
                            db.execSQL("INSERT INTO Output VALUES(25, 8 ,'Thats sad, can i help you with something?')");
                            db.execSQL("INSERT INTO Output VALUES(26, 8 ,'Im really sorry')");
                            db.execSQL("INSERT INTO Output VALUES(27, 9 ,'Im sorry to hear that')");
                            db.execSQL("INSERT INTO Output VALUES(28, 9 ,'Thats sad, can i help you with something?')");
                            db.execSQL("INSERT INTO Output VALUES(29, 9 ,'Im really sorry')");

                            // Exercises

                            db.execSQL("INSERT INTO Exercise VALUES (1 , 'What does the following java code returns?\n " +
                                    "String a = \"Java is cool\";" +
                                    "System.out.println(\"hello world\");', 'A')");

                            db.execSQL("INSERT INTO Exercise VALUES (2 ,'What does the \"++\" in a for loop do?\n', 'A')");

                            db.execSQL("INSERT INTO Exercise VALUES (3 ,'Which option successfully uses a method named \"hello\" from a class called \"myClass\"?\n', 'B')");

                            db.execSQL("INSERT INTO Exercise VALUES (4, 'Is String a primitive type in Java?\n', 'B')");


                            // Exercise Answers

                            // Ex1 - answers
                            db.execSQL("INSERT INTO ExerciseAnswers VALUES(1, 1, 'A', '\"Hello world\"')");
                            db.execSQL("INSERT INTO ExerciseAnswers VALUES(2, 1, 'B', \"Java is cool\")");
                            db.execSQL("INSERT INTO ExerciseAnswers VALUES(3, 1, 'C', '\"Syntax error\"')");

                            // Ex2 - Answers
                            db.execSQL("INSERT INTO ExerciseAnswers VALUES(4, 2, 'A', 'Increment the value of a certain variable')");
                            db.execSQL("INSERT INTO ExerciseAnswers VALUES(5, 2, 'B', 'Does not no anything')");
                            db.execSQL("INSERT INTO ExerciseAnswers VALUES(6, 2, 'C', 'Decrement the value of a variable')");

                            // Ex3 - Answers
                            db.execSQL("INSERT INTO ExerciseAnswers VALUES(7, 3, 'A', 'hello()')");
                            db.execSQL("INSERT INTO ExerciseAnswers VALUES(8, 3, 'B', 'myClass.hello()')");
                            db.execSQL("INSERT INTO ExerciseAnswers VALUES(9, 3, 'C', 'hello.myClass()')");

                            // Ex4 - Answers
                            db.execSQL("INSERT INTO ExerciseAnswers VALUES(10, 4, 'A', 'Yes')");
                            db.execSQL("INSERT INTO ExerciseAnswers VALUES(11, 4, 'B', 'No')");

                        }
                    })
                    .build();
        }
        return INSTANCE;
    }
}
