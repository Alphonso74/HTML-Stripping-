import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class leauges {


    public static class InsertApp {


        private Connection connect() throws ClassNotFoundException {
            Class.forName("org.sqlite.JDBC");


            Connection conn = null;
            Properties info = new Properties();
            try {
                String url = "jdbc:sqlite:/Users/alphonsomckenzie/Desktop/SQL/basketball.db";
                conn = DriverManager.getConnection(url,info);

                if (conn != null) {
                    System.out.println("Successfully connected to MySQL database test");
                }


            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return conn;
        }


        void insert(String league) {
//            String sql = "INSERT INTO player(name,currTeam) VALUES(?,?)";
            String sql = "insert into leagues(league) values(?)";

            System.err.println(league);


            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, league);
                pstmt.executeUpdate();
            } catch (SQLException | ClassNotFoundException e) {
                System.out.println("Error in insert: " + e.getMessage());
            }
        }
    }

    public static class ReadWeb {



        public static void main(String[] args) throws MalformedURLException, IOException {

            BufferedReader br = null;
            Scanner sc = new Scanner(System.in);
            InsertApp app = new InsertApp();

            try {

                URL url = new URL("https://basketball.realgm.com/international/league/125/ABA-Liga-Supercup/teams/836");
                br = new BufferedReader(new InputStreamReader(url.openStream()));

                String line;


                StringBuilder sb = new StringBuilder();


                Document doc;





                while ((line = br.readLine()) != null) {

                    Jsoup.clean(line, Whitelist.none());
                    Jsoup.clean(line, Whitelist.simpleText());

                    doc = Jsoup.parse(line);
                    line = doc.text();

//                    if(line.contains("Team")){
//
//
//                        break;
//                    }

                    if(line.equals("All Leagues")){
//                        System.out.println("test");
                        break;
//                    }else {
//
//                        sb.append(line);
//                        sb.append(System.lineSeparator());
//                    }
                    }
                }


                while ((line = br.readLine()) != null) {

                    Jsoup.clean(line, Whitelist.none());
                    Jsoup.clean(line, Whitelist.simpleText());

                    doc = Jsoup.parse(line);
                    line = doc.text();

                    String league = line;

                    if(line.equals("Team")){
//                        System.out.println("test team");
                        break;
                    }

                    if(line.equals("")){

                        break;
                    }
                    app.insert(league);

                    sb.append(line);
                    sb.append(System.lineSeparator());
                }

//                System.out.println("tester");
                    System.out.println("\n");
                    System.out.println(sb);


            } finally {

                if (br != null) {
                    br.close();
                }
            }
        }
    }
}
