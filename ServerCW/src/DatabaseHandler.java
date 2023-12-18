import sample.*;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.mysql.cj.jdbc.Driver;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?serverTimezone=UTC";

        Class.forName ("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection (connectionString, dbUser, dbPass);
        return dbConnection;
    }

    public void SignInUser(User user) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + Consts.USER_TABLE + "(" +
                Consts.USERS_LASTNAME + "," + Consts.USERS_NAME + "," +
                Consts.USERS_SURNAME + "," + Consts.USERS_PASSWORD + "," +
                Consts.USERS_LOGIN + "," + Consts.USERS_COUNTRY + "," +
                Consts.USERS_CITY + "," + Consts.USERS_STREET + "," +
                Consts.USERS_GENDER + "," + Consts.USERS_STATUS + ")" +
                " VALUES(?,?,?,?,?,?,?,?,?,?)";

        System.out.println (insert);
        PreparedStatement prt = getDbConnection ().prepareStatement (insert);
        prt.setString (1, user.getLastname ());
        prt.setString (2, user.getName ());
        prt.setString (3, user.getSurname ());
        prt.setString (4, user.getPassword ());
        prt.setString (5, user.getLogin ());
        prt.setString (6, user.getCountry ());
        prt.setString (7, user.getCity ());
        prt.setString (8, user.getStreet ());
        prt.setString (9, user.getGender ());
        prt.setString (10, user.getStatus ());

        prt.executeUpdate ();

        try {
            FileOutputStream fis = new FileOutputStream ("F:\\Учёба\\5 семестр\\Курсовая ПСП\\users.txt");
            ObjectOutputStream ois = new ObjectOutputStream (fis);
            ois.writeObject (user.toString ()+"\n");
            ois.close();
            fis.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public User getUser(User user) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = null;

        String select = "SELECT * FROM " + Consts.USER_TABLE + " WHERE " +
                Consts.USERS_LOGIN + "=? AND " + Consts.USERS_PASSWORD + "=?";

        PreparedStatement prt = getDbConnection ().prepareStatement (select);
        prt.setString (1, user.getLogin ());
        prt.setString (2, user.getPassword ());
        resultSet = prt.executeQuery ();

        if (resultSet.next ()) {
          //  resultSet.last ();
            User user1 = new User ();
            user1.setID (resultSet.getInt (1));
            user1.setLastname (resultSet.getString (2));
            user1.setName (resultSet.getString (3));
            user1.setSurname (resultSet.getString (4));
            user1.setPassword (resultSet.getString (5));
            user1.setLogin (resultSet.getString (6));
            user1.setCountry (resultSet.getString (7));
            user1.setCity (resultSet.getString (8));
            user1.setStreet (resultSet.getString (9));
            user1.setGender (resultSet.getString (10));
            user1.setStatus (resultSet.getString (11));
            return user1;
        }
        else return null;
    }

    public void AddBook(Book book) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + Consts.BOOKS_TABLE + "(" +
                Consts.BOOKS_AUTHOR + "," + Consts.BOOKS_NAME + "," +
                Consts.BOOKS_YEAR + "," + Consts.BOOKS_PAGES + "," +
                Consts.BOOKS_FORMAT + "," + Consts.BOOKS_MATERIAL + "," +
                Consts.BOOKS_COEFF + "," + Consts.BOOKS_STATUS + "," + Consts.BOOKS_NUMBER + ")" +
                " VALUES(?,?,?,?,?,?,?,?,?)";


        PreparedStatement prt = getDbConnection ().prepareStatement (insert);
        prt.setString (1, book.getAuthor ());
        prt.setString (2, book.getNameb ());
        prt.setString (3, String.valueOf (book.getYear ()));
        prt.setString (4, String.valueOf (book.getPages ()));
        prt.setString (5, book.getFormat ());
        prt.setString (6, book.getMaterial ());
        prt.setString (7, String.valueOf (book.getCoefficient ()));
        prt.setString (8, book.getStatus ());
        prt.setString (9, String.valueOf (book.getNumber ()));

        prt.executeUpdate ();

        try {
            FileOutputStream fis = new FileOutputStream ("F:\\Учёба\\5 семестр\\Курсовая ПСП\\books.txt");
            ObjectOutputStream ois = new ObjectOutputStream (fis);
            ois.writeObject (book.toString ()+"\n");
            ois.close();
            fis.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void AddArchive(Archive archive) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + Consts.ARCHIVE_TABLE + "(" +
                Consts.ARCHIVE_IDBOOK + "," + Consts.ARCHIVE_DATE + "," +
                Consts.ARCHIVE_IDADMIN + "," + Consts.ARCHIVE_AUTHOR + "," +
                Consts.ARCHIVE_TITLE + ")" + " VALUES(?,?,?,?,?)";


        PreparedStatement prt = getDbConnection ().prepareStatement (insert);
        prt.setString (1, String.valueOf (archive.getIDbook ()));
        prt.setString (2, String.valueOf (archive.getDate ()));
        prt.setString (3, String.valueOf (archive.getIDadmin ()));
        prt.setString (4, archive.getAuthor ());
        prt.setString (5, archive.getTitle ());

        prt.executeUpdate ();

        DeleteBook (archive.getIDbook ());

        try {
            FileOutputStream fis = new FileOutputStream ("F:\\Учёба\\5 семестр\\Курсовая ПСП\\archives.txt");
            ObjectOutputStream ois = new ObjectOutputStream (fis);
            ois.writeObject (archive.toString ()+"\n");
            ois.close();
            fis.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void AddOrder(Order order) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO orders (`title`, `author`, `year`, `pages`, `material`, `format`, `price`, `admin`, `date`,`status`,`number`)" +
                " VALUES ('" + order.getName () + "','" + order.getAuthor () + "','" + order.getYear () + "','" + order.getPages () +
                "','" + order.getMaterial () + "','" + order.getFormat () + "','" + order.getPrice () + "','" + order.getadmin () + "','" + order.getDate () +
                "','оформлен" + "','" + order.getNumber () + "');";
        PreparedStatement prt = getDbConnection ().prepareStatement (insert);
        prt.execute ();

        try {
            FileOutputStream fis = new FileOutputStream ("F:\\Учёба\\5 семестр\\Курсовая ПСП\\orders.txt");
            ObjectOutputStream ois = new ObjectOutputStream (fis);
            ois.writeObject (order.toString ()+"\n");
            ois.close();
            fis.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void DeleteBook(int idd) throws SQLException, ClassNotFoundException {
        String insert = "DELETE FROM " + Consts.BOOKS_TABLE + " WHERE " + Consts.BOOKS_ID + "=" + idd;
        PreparedStatement prt = getDbConnection ().prepareStatement (insert);
        prt.executeUpdate ();
    }

    public Book FindBook(int admins) throws SQLException, ClassNotFoundException {
        String insert = "SELECT * FROM books WHERE idbooks=" + admins;
        System.out.println (insert);
        List<Book> books = null;
        PreparedStatement prt = getDbConnection ().prepareStatement (insert);
        ResultSet resultSet = prt.executeQuery ();
//        while (resultSet.next ()) {
        resultSet.last ();
        Book book = new Book ();

        book.setID (resultSet.getInt (1));
        book.setAuthor (resultSet.getString (2));
        book.setNameb (resultSet.getString (3));
        book.setYear (resultSet.getInt (4));
        book.setPages (resultSet.getInt (5));
        book.setFormat (resultSet.getString (6));
        book.setMaterial (resultSet.getString (7));
        book.setCoefficient (resultSet.getFloat (8));
        book.setStatus (resultSet.getString (9));
        book.setNumber (resultSet.getInt (10));
        //books.add (book);

        return book;
    }

    public void DelArchive(String id) throws SQLException, ClassNotFoundException {
        String insert = "DELETE FROM " + Consts.ARCHIVE_TABLE + " WHERE idbook=" + id;


        PreparedStatement prt = getDbConnection ().prepareStatement (insert);
        prt.executeUpdate ();
    }

    public void DeleteUser(String id) throws SQLException, ClassNotFoundException {
        System.out.println (id);
        String insert = "DELETE FROM " + Consts.USER_TABLE + " WHERE " + Consts.USERS_LOGIN + " LIKE '" + id + "'";

        PreparedStatement prt = getDbConnection ().prepareStatement (insert);
        prt.executeUpdate ();
    }

    public void AddLoan(Loan loan) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO loans(iduser, idbook, term, date)" +
                " VALUES(?,?,?,?)";


        PreparedStatement prt = getDbConnection ().prepareStatement (insert);
        prt.setString (1, String.valueOf (loan.getIDuser ()));
        prt.setString (2, String.valueOf (loan.getIDbook ()));
        prt.setString (3, String.valueOf (loan.getTerm ()));
        prt.setString (4, String.valueOf (loan.getDate ()));

        prt.executeUpdate ();

        try {
            FileOutputStream fis = new FileOutputStream ("F:\\Учёба\\5 семестр\\Курсовая ПСП\\loans.txt");
            ObjectOutputStream ois = new ObjectOutputStream (fis);
            ois.writeObject (loan.toString ()+"\n");
            ois.close();
            fis.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public ResultSet MaxOrder() throws SQLException, ClassNotFoundException {
        String query = "select * from library.orders;";
        PreparedStatement p = getDbConnection ().prepareStatement (query);
        p.execute ();
        String insert = "select number from library.orders order by idorder desc limit 1;";
        PreparedStatement prt = getDbConnection ().prepareStatement (insert);
        Order order = new Order ();
        ResultSet set = prt.executeQuery ();
        return set;
    }

    public void UpdateLastOrder(float d) throws SQLException, ClassNotFoundException {
        String insert = "update orders set price = " + d + " order by idorder desc limit 1;;";
        PreparedStatement prt = getDbConnection ().prepareStatement (insert);
        prt.executeUpdate ();
    }

    public void DelOrder() throws SQLException, ClassNotFoundException {
        String insert = "DELETE FROM orders WHERE idorder ORDER BY idorder DESC LIMIT 1;";
        PreparedStatement prt = getDbConnection ().prepareStatement (insert);
        prt.executeUpdate ();
    }

    public void UpdateOrder(String id) throws SQLException, ClassNotFoundException {
        String insert = "update orders set status = 'получен' where idorder = " + id + ";";
        PreparedStatement prt = getDbConnection ().prepareStatement (insert);
        prt.executeUpdate ();
    }

    public User FindUser(int id) throws SQLException, ClassNotFoundException {
        String insert = "SELECT * FROM users WHERE idusers=" + id;
        System.out.println (insert);
        User user = new User ();
        PreparedStatement prt = getDbConnection ().prepareStatement (insert);
        ResultSet resultSet = prt.executeQuery ();
        resultSet.last ();
        user.setID (resultSet.getInt (1));
        user.setLastname (resultSet.getString (2));
        user.setName (resultSet.getString (3));
        user.setSurname (resultSet.getString (4));
        user.setPassword (resultSet.getString (5));
        user.setLogin (resultSet.getString (6));
        user.setCountry (resultSet.getString (7));
        user.setCity (resultSet.getString (8));
        user.setStreet (resultSet.getString (9));
        user.setGender (resultSet.getString (10));
        user.setStatus (resultSet.getString (11));

        return user;
    }

    public List<Book> ViewBooks() throws SQLException, ClassNotFoundException {
        List<Book> books = new ArrayList<> ();
        DatabaseHandler db = new DatabaseHandler ();
        ResultSet Set = null;

        String select = "SELECT * FROM books";

        PreparedStatement prt = null;
        prt = db.getDbConnection ().prepareStatement (select);
        Book book;
        Set = prt.executeQuery ();
        while (true) {
            if (!Set.next ()) break;
            book = new Book ();
            book.setID (Set.getInt (1));
            book.setAuthor (Set.getString (2));
            book.setNameb (Set.getString (3));
            book.setYear (Set.getInt (4));
            book.setPages (Set.getInt (5));
            book.setFormat (Set.getString (6));
            book.setMaterial (Set.getString (7));
            book.setCoefficient (Set.getFloat (8));
            book.setStatus (Set.getString (9));
            book.setNumber (Set.getInt (10));
            books.add (book);
        }
        return books;
    }

    public List<User> ViewUser() throws SQLException, ClassNotFoundException {
        String insert = "SELECT * FROM users";
        System.out.println (insert);
        List<User> users = new ArrayList<> ();
        PreparedStatement prt = getDbConnection ().prepareStatement (insert);
        ResultSet resultSet = prt.executeQuery ();
        while (resultSet.next ()) {
            User user = new User ();
            user.setID (resultSet.getInt (1));
            user.setLastname (resultSet.getString (2));
            user.setName (resultSet.getString (3));
            user.setSurname (resultSet.getString (4));
            user.setPassword (resultSet.getString (5));
            user.setLogin (resultSet.getString (6));
            user.setCountry (resultSet.getString (7));
            user.setCity (resultSet.getString (8));
            user.setStreet (resultSet.getString (9));
            user.setGender (resultSet.getString (10));
            user.setStatus (resultSet.getString (11));
            users.add (user);
        }
        return users;
    }

    void EditUser(User user) throws SQLException, ClassNotFoundException {

        String insert = "UPDATE users SET lastname = ?, name = ?, surname = ?, password = ?, login = ?, country = ?, city = ?, street = ?, gender = ? WHERE idusers= " + user.getID ();

        System.out.println (insert);
        PreparedStatement prt = getDbConnection ().prepareStatement (insert);
        prt.setString (1, user.getLastname ());
        prt.setString (2, user.getName ());
        prt.setString (3, user.getSurname ());
        prt.setString (4, user.getPassword ());
        prt.setString (5, user.getLogin ());
        prt.setString (6, user.getCountry ());
        prt.setString (7, user.getCity ());
        prt.setString (8, user.getStreet ());
        prt.setString (9, user.getGender ());

        prt.executeUpdate ();

        System.out.println (user.getID ());
    }

    public void EditBook(Book book) throws SQLException, ClassNotFoundException {
        String insert = "UPDATE books SET author = ?, booktitle = ?, yearofpublication = ?, numberofpages = ?," +
                " format = ?, material = ?, coefficient = ?, status = ?, number = ? WHERE idbooks= "+book.getID ();

        PreparedStatement prt = getDbConnection ().prepareStatement (insert);
        prt.setString (1, book.getAuthor ());
        prt.setString (2, book.getNameb ());
        prt.setString (3, String.valueOf (book.getYear ()));
        prt.setString (4, String.valueOf (book.getPages ()));
        prt.setString (5, book.getFormat ());
        prt.setString (6, book.getMaterial ());
        prt.setString (7, String.valueOf (book.getCoefficient ()));
        prt.setString (8, book.getStatus ());
        prt.setString (9, String.valueOf (book.getNumber ()));

        prt.executeUpdate ();
    }
}

