import org.jsoup.Jsoup;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class teams {

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


        void insert(String name, String league) {

            String sql = "insert into teams(name,league) values(?,?)";

            System.err.println(name);
            System.err.println(league);


            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, name);
                pstmt.setString(2, league);
                ;
                pstmt.executeUpdate();
            } catch (SQLException | ClassNotFoundException e) {
                System.out.println("Error in insert: " + e.getMessage());
            }
        }
    }

    public static class ReadWebPage {



        public static void main(String[] args) throws MalformedURLException, IOException {

            BufferedReader br = null;
            Scanner sc = new Scanner(System.in);
            InsertApp app = new InsertApp();

            try {

                URL url = new URL("https://basketball.realgm.com/international/teams");
                br = new BufferedReader(new InputStreamReader(url.openStream()));

                String line;

                StringBuilder sb = new StringBuilder();

                Document doc;





                while ((line = br.readLine()) != null) {

                    Jsoup.clean(line, Whitelist.none());
                    Jsoup.clean(line, Whitelist.simpleText());

                    doc = Jsoup.parse(line);
                    line = doc.text();



                        if (line.contains("Team Name Super League National League Links")) {

                            break;
                        }

                }

                String[] lines = new String[3];
                List<String> list = new ArrayList<String>(lines.length);
                int counter = 0;
                while ((line = br.readLine()) != null) {

                            if(line.equals("</td>") || line.equals("</tr") || line.equals("</div>") || line.contains("Super League(s)")){

                                continue;
                            }



                        Jsoup.clean(line, Whitelist.none());
                        Jsoup.clean(line, Whitelist.simpleText());

                        doc = Jsoup.parse(line);
                        line = doc.text();



                        if (line.contains("Roster")) {


                        } else {

                            if (line.equals("NBA")) {

                                break;

                            }

                            if(line.isBlank()){

                                continue;
                            }

//****************************************************************************************************************************************************************************
//Hard code
//Doing this to remove all teams that are not in a national league
// as well as teams that are in a super league but not national
//This makes parsing the website easier to create uniformity in the DB

                            if(line.equals("ALBA Berlin Junior Team")){

                                continue;
                            }
                            if(line.equals("Asseco Arka Gdynia Junior Team")){
                                continue;
                            }
                            if(line.equals("Astana Tigers")){
                                continue;
                            }
                            if(line.equals("Avtodor Saratov")){
                                continue;
                            }
                            if(line.equals("Avtodor-2")){
                                continue;
                            }
                            if(line.equals("Barking Abbey London")){
                                continue;
                            }
                            if(line.equals("BBA Ludwigsburg")){
                                continue;
                            }
                            if(line.equals("Buducnost Voli Podgorica Junior Team")){
                                continue;
                            }
                            if(line.equals("Caciques de Valledupar")){
                                continue;
                            }
                            if(line.equals("CD Las Animas")){
                                continue;
                            }
                            if(line.equals("CSKA Moscow")){
                                continue;
                            }
                            if(line.equals("CSKA Moscow Junior Team")){
                                continue;
                            }
                            if(line.equals("Defensor Sporting")){
                                continue;
                            }
                            if(line.equals("Deportivo San Jose")){
                                continue;
                            }
                            if(line.equals("Enisey Krasnoyarsk")){
                                continue;
                            }
                            if(line.equals("Fenerbahce Ulker Junior Team")){
                                continue;
                            }
                            if(line.equals("FIATC Mutua Joventut Junior Team")){
                                continue;
                            }
                            if(line.equals("Igokea U19")){
                                continue;
                            }
                            if(line.equals("Khimki")){
                                continue;
                            }
                            if(line.equals("Khimki-2")){
                                continue;
                            }
                            if(line.equals("KK Cedevita Junior Team")){
                                continue;
                            }
                            if(line.equals("KK Cibona Junior Team")){
                                continue;
                            }
                            if(line.equals("KK Crvena Zvezda Junior Team")){
                                continue;
                            }
                            if(line.equals("KK Mega Leks Junior Team")){
                                continue;
                            }
                            if(line.equals("KK Partizan Junior Team")){
                                continue;
                            }
                            if(line.equals("Krka U19")){
                                continue;
                            }
                            if(line.equals("Leones de Quilpue")){
                                continue;
                            }
                            if(line.equals("Lokomotiv Kuban")){
                                continue;
                            }
                            if(line.equals("Lokomotiv Kuban-2")){
                                continue;
                            }
                            if(line.equals("Maccabi Tel Aviv Junior Team")){
                                continue;
                            }
                            if(line.equals("Malvin")){
                                continue;
                            }
                            if(line.equals("MBA-2 Moscow")){
                                continue;
                            }
                            if(line.equals("Mornar U19")){
                                continue;
                            }
                            if(line.equals("Nacional de Montevideo")){
                                continue;
                            }
                            if(line.equals("NBA G League Elite")){
                                continue;
                            }
                            if(line.equals("NBL1 All-Stars")){
                                continue;
                            }
                            if(line.equals("Nizhny Novgorod")){
                                continue;
                            }
                            if(line.equals("Nizhny Novgorod-2")){
                                continue;
                            }
                            if(line.equals("Olympiacos Junior Team")){
                                continue;
                            }
                            if(line.equals("Panathinaikos Superfoods Athens")){
                                continue;
                            }
                            if(line.equals("Parma Basket Perm")){
                                continue;
                            }
                            if(line.equals("Parma-2")){
                                continue;
                            }
                            if(line.equals("Pichincha de Potosi")){
                                continue;
                            }
                            if(line.equals("Piratas de Los Lagos")){
                                continue;
                            }
                            if(line.equals("Promitheas Patras Junior Team")){
                                continue;
                            }
                            if(line.equals("Real Madrid Junior Team")){
                                continue;
                            }
                            if(line.equals("Salta Basket")){
                                continue;
                            }
                            if(line.equals("Samara-2")){
                                continue;
                            }
                            if(line.equals("San Andres Warriors")){
                                continue;
                            }
                            if(line.equals("Spartak-2")){
                                continue;
                            }
                            if(line.equals("Stella Azzurra Junior Team")){
                                continue;
                            }
                            if(line.equals("Tofas Junior Team")){
                                continue;
                            }
                            if(line.equals("Umana Reyer Venice Junior Team")){
                                continue;
                            }
                            if(line.equals("Unicaja Malaga Junior Team")){
                                continue;
                            }
                            if(line.equals("UNICS Kazan")){
                                continue;
                            }
                            if(line.equals("UNICS-2")){
                                continue;
                            }
                            if(line.equals("Union Olimpija Junior Team")){
                                continue;
                            }
                            if(line.equals("Uruguay National Team")){
                                continue;
                            }
                            if(line.equals("Valencia Junior Team")){
                                continue;
                            }
                            if(line.equals("Vllaznia Shkodra")){
                                continue;
                            }
                            if(line.equals("Yenisei-2")){
                                continue;
                            }
                            if(line.equals("Zadar U19")){
                                continue;
                            }
                            if(line.equals("Zaragoza Junior Team")){
                                continue;
                            }
                            if(line.equals("Zenit Saint Petersburg")){
                                continue;
                            }
                            if(line.equals("Zenit-2")){
                                continue;
                            }



//****************************************************************************************************************************************************************************
                                counter++;

                                list.add(line);

                                if (counter == 2) {


                                    //System.out.println(list.get(0));
                                    //System.out.println(list.get(1));
                                    app.insert(list.get(0), list.get(1));
                                    lines[0] = "";
                                    lines[1] = "";
                                    list.remove(0);
                                    list.remove(0);
                                    counter = 0;

                                    sb.append(line);
                                    sb.append(System.lineSeparator());
                                } else {



                                    sb.append(line);
                                    sb.append(System.lineSeparator());
                                }
                            }






                }

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
