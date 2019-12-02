import org.jsoup.Jsoup;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class player {

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

        void insert1(String player, String team) {
//            String sql = "INSERT INTO player(name,currTeam) VALUES(?,?)";
            String sql = "insert into left(player,team) values(?,?)";

            System.err.println(player);
            System.err.println(team);

            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, player);
                pstmt.setString(2, team);
                pstmt.executeUpdate();
            } catch (SQLException | ClassNotFoundException e) {
                System.out.println("Error in insert: " + e.getMessage());
            }
        }


        void insert(String name, String team) {
//            String sql = "INSERT INTO player(name,currTeam) VALUES(?,?)";
            String sql = "insert into player(name,team) values(?,?)";

            System.err.println(name);
            System.err.println(team);

            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, name);
                pstmt.setString(2, team);
                pstmt.executeUpdate();
            } catch (SQLException | ClassNotFoundException e) {
                System.out.println("Error in insert: " + e.getMessage());
            }
        }
    }



    public static class ReadWebPageEx {



        public static void main(String[] args) throws MalformedURLException, IOException {

            BufferedReader br = null;
            Scanner sc = new Scanner(System.in);
            InsertApp app = new InsertApp();

            try {

                URL url = new URL("https://basketball.realgm.com/international/transactions/2020");
                br = new BufferedReader(new InputStreamReader(url.openStream()));

                String line;


                StringBuilder sb = new StringBuilder();


                Document doc;
                while ((line = br.readLine()) != null) {

                    Jsoup.clean(line, Whitelist.none());
                    Jsoup.clean(line, Whitelist.simpleText());

                    doc = Jsoup.parse(line);
                    line = doc.text();





                    if(line.contains("has signed with")){

                        line = line.trim();

                        line = line.replace(",", "");




                        String[] name = line.split(" ");
                        String[] teamname = line.split(" ");


                        String pname = name[0] + " " +  name[1];

                        if(!name[2].equals("has")){

                            pname = name[0] + " " +  name[1] + " " + name[2];
                        }


                        String team = teamname[teamname.length - 2] + " " + teamname[teamname.length - 1];

                        if(teamname[teamname.length - 2].equals("with")){

                            team = teamname[teamname.length - 1] ;

                        }

                        if(!teamname[teamname.length - 3].equals("with")){

                            team = teamname[teamname.length - 3] + " " + teamname[teamname.length - 2] + " " + teamname[teamname.length - 1];

                        }

                        if(teamname[teamname.length - 2 ].equals("with") && teamname[teamname.length - 3].equals("signed")){


                            team = teamname[teamname.length - 1];
                        }





                        if(line.contains("previously with")){



                            if(name[2].equals("previously")){

                                pname = name[0] + " " +  name[1];
                            }

                            team = teamname[teamname.length - 2] + " " + teamname[teamname.length - 1];

                            if(teamname[teamname.length - 2].equals("with")){

                                team = teamname[teamname.length - 1] ;

                            }

                            if(!teamname[teamname.length - 3].equals("with") ){

                                team = teamname[teamname.length - 3] + " " + teamname[teamname.length - 2] + " " + teamname[teamname.length - 1];

                            }

                            if(teamname[teamname.length - 2 ].equals("with") && teamname[teamname.length - 3].equals("signed")){


                                team = teamname[teamname.length - 1];
                            }

                            if(team.endsWith(".")){

                                team = team.replace(".","");
                            }


                            app.insert(pname,team);
                            sb.append(line);
                            sb.append(System.lineSeparator());
                        }else{

                            if(team.endsWith(".")){

                                team = team.replace(".","");
                            }

                            app.insert(pname,team);

                            sb.append(line);
                            sb.append(System.lineSeparator());

                        }
                    }

                    if(line.contains("has left")){

                        line = line.trim();

                        String[] name = line.split(" ");
                        String[] teamname = line.split(" ");



                        String pname = name[0] + " " +  name[1];

                        if(!name[2].equals("has")){

                            pname = name[0] + " " +  name[1] + " " + name[2];
                        }


                        //*********************************



                        String team = teamname[teamname.length - 2] + " " + teamname[teamname.length - 1 ]; //Some names like M.J. Rhett is being read as one word M.J.Rhett

                        if(teamname[teamname.length - 2].equals("left")){

                            team = teamname[teamname.length - 1] ;

                        }

                        if(teamname[teamname.length - 4].equals("left")){

                            team = teamname[teamname.length - 3] + " "+ teamname[teamname.length - 2] + " " + teamname[teamname.length - 1 ];

                        }

                        if(teamname[teamname.length - 5].equals("left")){

                            team = teamname[teamname.length - 4] + " " + teamname[teamname.length - 3] + " "+ teamname[teamname.length - 2] + " " + teamname[teamname.length - 1 ];

                        }

                        if(team.endsWith(".")){

                            team = team.replace(".","");
                        }

                        app.insert1(pname, team);


                        sb.append(line);
                        sb.append(System.lineSeparator());
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
