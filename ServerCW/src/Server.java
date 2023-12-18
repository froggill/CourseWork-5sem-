import sample.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server implements Runnable {

    private Socket connection;

    public Server(Socket connection) {
        this.connection = connection;
    }

    public static void main(String[] args) {
        System.out.println ("Сервер работает..");
        int i = 1;

        ServerSocket serverSocket = null;

        try {
            String word;
            System.out.println ("Изменить настройки сервера?(y/n)");
            try (Scanner in = new Scanner (System.in)) {
                word = in.next ();
                if (word.equals ("y")){
                    System.out.println ("Введите новый порт: ");
                    int port = Integer.parseInt (in.next ());
                    serverSocket = new ServerSocket (port);
                }
                else  serverSocket = new ServerSocket (3000);
            }
        } catch (IOException e) {
            System.out.println ("Невозможно подключиться к порту");
            System.exit (-1);
        }
        //Ждем подключения клиента, запускаем поток на каждое подключение
        while (true) {
            try {
                System.out.println ("Ждем подключения...");
                Socket connection = serverSocket.accept ();
                Runnable runnable = new Server (connection);
                Thread thread = new Thread (runnable);
                thread.start ();
                LocalTime time = LocalTime.now ();
                System.out.println ("Клиент подключился: IP - " + connection.getInetAddress () + " Порт - " + connection.getLocalPort () + " Дата и время -  " + LocalDate.now () + " " + time.getHour () + ":" + time.getMinute () + ":" + time.getSecond ());
            } catch (IOException e) {
                System.out.println ("");
                System.exit (-1);
            }
        }
    }

    @Override
    public void run() {
        int code;
        try {
            InputStream inputStream = connection.getInputStream ();
            OutputStream outputStream = connection.getOutputStream ();
            DatabaseHandler databaseHandler = new DatabaseHandler ();
            ObjectOutputStream soos = new ObjectOutputStream (outputStream);
            ObjectInputStream sois = new ObjectInputStream (inputStream);
            while (true) {
                code = sois.readInt ();
                switch (code) {
                    case 1: {
                        System.out.println ("Регистрация");
                        User rg = (User) sois.readObject ();
                        System.out.println (rg.getLogin ());
                        databaseHandler.SignInUser (rg);
                        System.out.println ("Создан пользователь: " + rg.getLogin ());
                        break;
                    }
                    case 2: {
                        System.out.println ("Авторизация");
                        soos.writeObject (databaseHandler.getUser ((User) sois.readObject ()));
                        soos.flush ();
                        break;
                    }
                    case 3: {
                        System.out.println ("Выборка всех пользователей");
                        List<User> set = databaseHandler.ViewUser ();
                        soos.writeObject (set);
                        soos.flush ();
                        break;
                    }
                    case 4: {
                        System.out.println ("Удаление пользoвателя");
                        databaseHandler.DeleteUser ((String) sois.readObject ());
                        sois.reset ();
                        break;
                    }
                    case 5: {
                        System.out.println ("Редактирование пользователя");
                        databaseHandler.EditUser ((User) sois.readObject ());
                        sois.reset ();
                        break;
                    }
                    case 6: {
                        System.out.println ("Просмотр всех книг.");
                        List<Book> list = databaseHandler.ViewBooks ();
                        soos.writeObject (list);
                        soos.flush ();
                        break;
                    }
                    case 7: {
                        System.out.println ("Просмотр всех пользователей");
                        soos.writeObject (databaseHandler.ViewUser ());
                        break;
                    }
                    case 8: {
                        String books = (String) sois.readObject ();
                        String query = "SELECT idbooks FROM books WHERE booktitle='" + books + "';";
                        PreparedStatement st = databaseHandler.getDbConnection ().prepareStatement (query);
                        ResultSet set = st.executeQuery ();
                        set.last ();
                        Book book1 = new Book ();
                        book1.setID (set.getInt (1));
                        soos.writeInt (book1.getID ());
                        soos.flush ();
                        break;
                    }
                    case 9: {
                        Loan loan = (Loan) sois.readObject ();
                        databaseHandler.AddLoan (loan);
                        String query = "SELECT * FROM books WHERE idbooks=" + loan.getIDbook () + ";";
                        PreparedStatement st = databaseHandler.getDbConnection ().prepareStatement (query);
                        ResultSet set = st.executeQuery ();
                        set.last ();
                        Book book = new Book ();
                        book.setNumber (set.getInt (10));
                        int num = book.getNumber () - 1;
                        System.out.println (num);
                        if (book.getNumber () == 1) {
                            query = "UPDATE books SET number=0 WHERE idbooks=" + loan.getIDbook ();
                            st = databaseHandler.getDbConnection ().prepareStatement (query);
                            st.executeUpdate ();
                            query = "UPDATE books SET status ='недоступна' WHERE idbooks=" + loan.getIDbook ();
                            st = databaseHandler.getDbConnection ().prepareStatement (query);
                            st.executeUpdate ();
                        } else {
                            query = "UPDATE books SET number=" + num + " WHERE idbooks=" + loan.getIDbook ();
                            st = databaseHandler.getDbConnection ().prepareStatement (query);
                            st.executeUpdate ();
                        }
                        break;
                    }
                    case 10: {
                        System.out.println ("Добавление пользователя");
                        User user = (User) sois.readObject ();
                        databaseHandler.SignInUser (user);
                        break;
                    }
                    case 11:{
                        System.out.println ("Добавление в архив");
                        Archive archive = (Archive) sois.readObject ();
                        databaseHandler.AddArchive (archive);
                        break;
                    }
                    case 12:{
                        List<Archive> ArchiveData = new ArrayList<> ();
                        String query = "SELECT * FROM archive";
                        PreparedStatement statement = databaseHandler.getDbConnection ().prepareStatement (query);
                        ResultSet resultSet = statement.executeQuery ();
                        int i=0;
                        while (resultSet.next ()){i++;}
                        System.out.println (i);
                        soos.writeInt (i);
                        while (resultSet.next ()){
                            Archive archive = new Archive ();

                            archive.setID (resultSet.getInt (1));
                            archive.setIDbook (resultSet.getInt (2));
                            archive.setDate (resultSet.getString (3));
                            archive.setIDadmin (resultSet.getInt (4));
                            archive.setAuthor (resultSet.getString (5));
                            archive.setTitle (resultSet.getString (6));
                            soos.writeObject (archive);
                        }
                        break;
                    }
                    case 13:{
                        System.out.println ("Добавление книги");
                        Book book = (Book) sois.readObject ();
                        databaseHandler.AddBook (book);
                        break;
                    }
                    case 14: {
                        System.out.println ("Удаление книги");
                        int i = sois.readInt ();
                        databaseHandler.DeleteBook (i);
                        break;
                    }
                    case 15:{
                        System.out.println ("Редактирование книги");
                        databaseHandler.EditBook ((Book) sois.readObject ());
                        break;
                    }
                    case 16:{
                        int idbooks = sois.readInt ();
                        int idusers = sois.readInt ();
                        String query = "DELETE FROM loans WHERE idbook=" + idbooks + " AND iduser=" + idusers + ";";
                        System.out.println (query);
                        PreparedStatement st = databaseHandler.getDbConnection ().prepareStatement (query);
                        st.executeUpdate ();
                        query = "SELECT * FROM books WHERE idbooks=" + idbooks + ";";
                        st = databaseHandler.getDbConnection ().prepareStatement (query);
                        ResultSet resultSet = st.executeQuery ();
                        resultSet.last ();
                        Book book = new Book ();
                        book.setNumber (resultSet.getInt (10));
                        int num = book.getNumber () + 1;
                        System.out.println (num);
                        if (book.getNumber () == 0) {
                            query = "UPDATE books SET status ='доступна' WHERE idbooks=" + idbooks;
                            st = databaseHandler.getDbConnection ().prepareStatement (query);
                            st.executeUpdate ();
                            query = "UPDATE books SET number=1 WHERE idbooks=" + idbooks;
                            st = databaseHandler.getDbConnection ().prepareStatement (query);
                            st.executeUpdate ();
                        }
                        else {
                            query = "UPDATE books SET number="+num+" WHERE idbooks=" + idbooks;
                            st = databaseHandler.getDbConnection ().prepareStatement (query);
                            st.executeUpdate ();
                        }
                        break;
                    }
                    case 17:{
                        System.out.println ("Добавление заказа");
                        Order order = (Order) sois.readObject ();
                        databaseHandler.AddOrder (order);
                        break;
                    }
                    case 18:{
                        System.out.println ("Удалить архив");
                        String archive = (String) sois.readObject ();
                        databaseHandler.DelArchive (archive);
                        break;
                    }
                    case 19:{
                        Float f = sois.readFloat ();
                        databaseHandler.UpdateLastOrder (f);
                        break;
                    }
                    case 20:{
                        databaseHandler.DelOrder ();
                        break;
                    }
                    case 21:{
                        String s = (String) sois.readObject ();
                        databaseHandler.UpdateOrder (s);
                        break;
                    }
                    default:
                        throw new IllegalStateException ("Unexpected value: " + code);
                }
            }
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace ();
        }
    }
}