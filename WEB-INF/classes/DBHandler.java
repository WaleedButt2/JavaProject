import java.sql.*;
public class DBHandler{
    profile Recieve_Message(String receiver){
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            String url = "jdbc:mysql://127.0.0.1/Project?characterEncoding=utf8";
            java.sql.Connection con = DriverManager.getConnection(url, "root", "root"); 
            Statement st = con.createStatement();
            String Query="select * from project.message where Receiver='" + receiver + "'";
            ResultSet rs=st.executeQuery(Query);
            String mesg ="None";
            String sender="None";
            int id=-1;
            while(rs.next()){
                mesg=rs.getString("Message");
                sender=rs.getString("Sender");
                id=rs.getInt("id");
            }
            Query="delete from project.message where id='"+id+"'";
            st.executeUpdate(Query);
            return new profile(mesg, sender);
            }
        catch(Exception e){
            System.out.println(e);
        }  
        return null;     
    }
    void Send_Message(String sender,String receiver,String msg){
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            String url = "jdbc:mysql://127.0.0.1/Project?characterEncoding=utf8";
            java.sql.Connection con = DriverManager.getConnection(url, "root", "root"); 
            Statement st = con.createStatement();
            String Query="insert into message(Sender,Receiver,Message)values('"+sender+"','"+receiver+"','"+msg+"')";
            st.executeUpdate(Query);
            }
        catch(Exception e){
            System.out.println(e);
        }       
    }
    public void InsertGroup(groups person){
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            String url = "jdbc:mysql://127.0.0.1/Project?characterEncoding=utf8";
            java.sql.Connection con = DriverManager.getConnection(url, "root", "root"); 
            Statement st = con.createStatement();
            String Query="insert into project.groups(Email1,Email2,Email3,Email)values('"+person.Email1+"','"
            +person.Email2+"','" + person.Email3 + "','" + person.Email + "')";
            st.executeUpdate(Query);
            }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public boolean SearchGroup(int id){
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            String url = "jdbc:mysql://127.0.0.1/Project?characterEncoding=utf8";
            java.sql.Connection con = DriverManager.getConnection(url, "root", "root"); 
            Statement st = con.createStatement();
            String Query="select id from project.groups where id='" + id + "'";
            ResultSet rs=st.executeQuery(Query);
            if(rs.next()){
                return true;
            }
            }
            catch(Exception e){
                System.out.println(e);
            }
        return false;
        }
    public ResultSet ShowBlock_Users(String mail){
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            String url = "jdbc:mysql://127.0.0.1/Project?characterEncoding=utf8";
            java.sql.Connection con = DriverManager.getConnection(url, "root", "root"); 
            Statement st = con.createStatement();
            String Query="select Blocked from blocked where Email='" + mail + "'";
            ResultSet rs=st.executeQuery(Query);
            return rs;
            }
            catch(Exception e){
                System.out.println(e);
            }
        return null;
        }
        public ResultSet ShowGroups(String mail){
            try{
                Class.forName("com.mysql.jdbc.Driver"); 
                String url = "jdbc:mysql://127.0.0.1/Project?characterEncoding=utf8";
                java.sql.Connection con = DriverManager.getConnection(url, "root", "root"); 
                Statement st = con.createStatement();
                String Query="select * from project.groups where Email='" + mail + "'";
                ResultSet rs=st.executeQuery(Query);
                return rs;
                }
                catch(Exception e){
                    System.out.println(e);
                }
            return null;
            }
public void Unblock_User(String mail,String poop){
    try{
        Class.forName("com.mysql.jdbc.Driver"); 
        String url = "jdbc:mysql://127.0.0.1/Project?characterEncoding=utf8";
        java.sql.Connection con = DriverManager.getConnection(url, "root", "root"); 
        Statement st = con.createStatement();
        String Query="delete from Blocked where Email='" + mail + "'and Blocked = '"+poop+"'";
        st.executeUpdate(Query);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public Boolean SearchBlock_Users(String mail,String bloc){
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            String url = "jdbc:mysql://127.0.0.1/Project?characterEncoding=utf8";
            java.sql.Connection con = DriverManager.getConnection(url, "root", "root"); 
            Statement st = con.createStatement();
            String Query="select Blocked from blocked where Email='" + mail + "' and Blocked='"+bloc+"'";
            ResultSet rs=st.executeQuery(Query);
            if(rs.next()){
                return true;
            }
            }
            catch(Exception e){
                System.out.println(e);
            }
        return false;
        }
        public void InsertBlocked(blocked person){
            try{
                Class.forName("com.mysql.jdbc.Driver"); 
                String url = "jdbc:mysql://127.0.0.1/Project?characterEncoding=utf8";
                java.sql.Connection con = DriverManager.getConnection(url, "root", "root"); 
                Statement st = con.createStatement();
                String Query="insert into blocked(Email,Blocked)values('"+person.Email+"','"+  person.Blocked +"')";
                st.executeUpdate(Query);
                }
            catch(Exception e){
                System.out.println(e);
            }
        }
    public profile SearchProfile(String mail){
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            String url = "jdbc:mysql://127.0.0.1/Project?characterEncoding=utf8";
            java.sql.Connection con = DriverManager.getConnection(url, "root", "root"); 
            Statement st = con.createStatement();
            String Query = "select * from profile where Email='"+ mail +"'";
            ResultSet rs=st.executeQuery(Query);
            if(rs.next()){
               profile person=new profile(rs.getString("Name"),rs.getString("Email"));
               return person;
            }
            }
            catch(Exception e){
                System.out.println(e);
            }
        return null;
        }
    public void InsertProfile(profile person){
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            String url = "jdbc:mysql://127.0.0.1/Project?characterEncoding=utf8";
            java.sql.Connection con = DriverManager.getConnection(url, "root", "root"); 
            Statement st = con.createStatement();
            String Query="insert into profile(Name,Email)values('"+person.Name+"','"+  person.Email +"')";
            st.executeUpdate(Query);
            }
        catch(Exception e){
            System.out.println(e);
        }
    }   
    public void InsertFriends(friends person){
    try{
        Class.forName("com.mysql.jdbc.Driver"); 
        String url = "jdbc:mysql://127.0.0.1/Project?characterEncoding=utf8";
        java.sql.Connection con = DriverManager.getConnection(url, "root", "root"); 
        Statement st = con.createStatement();
        String Query="insert into friends(Email,Friend,Name)values('"+person.Email+"','"+ person.Friend +"','"+  person.Name +"')";
        st.executeUpdate(Query);
        Query="insert into friends(Email,Friend,Name)values('"+person.Friend+"','"+ person.Email +"','"+  person.Name +"')";
        st.executeUpdate(Query);
        }
    catch(Exception e){
        System.out.println(e);
        }
    }
    public ResultSet ShowFriends(String mail){
    try{
        Class.forName("com.mysql.jdbc.Driver"); 
        String url = "jdbc:mysql://127.0.0.1/Project?characterEncoding=utf8";
        java.sql.Connection con = DriverManager.getConnection(url, "root", "root"); 
        Statement st = con.createStatement();
        String Query = "select * from friends where Email='"+ mail +"'";
        ResultSet rs=st.executeQuery(Query);
        return rs;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    public void UnFriend(String mail,String friend){
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            String url = "jdbc:mysql://127.0.0.1/Project?characterEncoding=utf8";
            java.sql.Connection con = DriverManager.getConnection(url, "root", "root"); 
            Statement st = con.createStatement();
            String Query="delete from friends where Email='" + mail + "' and Friend = '"+friend+"'";
            st.executeUpdate(Query);
            Query="delete from friends where Email='" + friend + "' and Friend = '"+mail+"'";
            st.executeUpdate(Query);
            }
            catch(Exception e){
                System.out.println(e);
            }
            return ;
    }
    public String searchFriend(String mail,String Friend){
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            String url = "jdbc:mysql://127.0.0.1/Project?characterEncoding=utf8";
            java.sql.Connection con = DriverManager.getConnection(url, "root", "root"); 
            Statement st = con.createStatement();
            String Query = "select * from friends where Email='"+ mail +"' AND Friend = '"+Friend+"'";
            ResultSet rs=st.executeQuery(Query);
            if(rs.next()) return rs.getString("Name");
            }
            catch(Exception e){
                System.out.println(e);
            }
            return "";
        }
    public Boolean searchAuthentication(String mail,String password){
    try{
        Class.forName("com.mysql.jdbc.Driver"); 
        String url = "jdbc:mysql://127.0.0.1/Project?characterEncoding=utf8";
        java.sql.Connection con = DriverManager.getConnection(url, "root", "root"); 
        Statement st = con.createStatement();
        String Query = "select * from authentication where Email='"+ mail +"' and Password='"+ password + "'";
        ResultSet rs=st.executeQuery(Query);
        if(rs.next()){
           return true; 
        }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    public void InsertAuthentication(String mail,String password){
        try{
        Class.forName("com.mysql.jdbc.Driver"); 
        String url = "jdbc:mysql://127.0.0.1/Project?characterEncoding=utf8";
        java.sql.Connection con = DriverManager.getConnection(url, "root", "root"); 
        Statement st = con.createStatement();
        String Query="insert into authentication(Email, Password) values('" + mail + "', '" + password + "' )";
        st.executeUpdate(Query);
        }
        catch(Exception e){
            System.out.println(e);
        }
        return ;
    }
    public void DeleteAuthetnication(String mail){
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            String url = "jdbc:mysql://127.0.0.1/Project?characterEncoding=utf8";
            java.sql.Connection con = DriverManager.getConnection(url, "root", "root"); 
            Statement st = con.createStatement();
            String Query="delete from authentication where Email='" + mail + "'";
            st.executeUpdate(Query);
            }
            catch(Exception e){
                System.out.println(e);
            }
            return ;
    }
    public void DeleteFriends(String mail){
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            String url = "jdbc:mysql://127.0.0.1/Project?characterEncoding=utf8";
            java.sql.Connection con = DriverManager.getConnection(url, "root", "root"); 
            Statement st = con.createStatement();
            String Query="delete from friends where Email='" + mail + "'";
            st.executeUpdate(Query);
            Query="delete from friends where Friend='" + mail + "'";
            st.executeUpdate(Query);
            }
            catch(Exception e){
                System.out.println(e);
            }
            return ;
    }
    public void DeleteProfile(String mail){
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            String url = "jdbc:mysql://127.0.0.1/Project?characterEncoding=utf8";
            java.sql.Connection con = DriverManager.getConnection(url, "root", "root"); 
            Statement st = con.createStatement();
            String Query="delete from profile where Email='" + mail + "'";
            st.executeUpdate(Query);
            }
            catch(Exception e){
                System.out.println(e);
            }
            return ;
    }
    public void DeleteBlocked(String mail){
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            String url = "jdbc:mysql://127.0.0.1/Project?characterEncoding=utf8";
            java.sql.Connection con = DriverManager.getConnection(url, "root", "root"); 
            Statement st = con.createStatement();
            String Query="delete from Blocked where Email='" + mail + "'";
            st.executeUpdate(Query);
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
    public void DeleteGroup(String mail){
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            String url = "jdbc:mysql://127.0.0.1/Project?characterEncoding=utf8";
            java.sql.Connection con = DriverManager.getConnection(url, "root", "root"); 
            Statement st = con.createStatement();
            String Query="delete from project.groups where Email='" + mail + "'";
            st.executeUpdate(Query);
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
        public void DeleteGroup(int id){
            try{
                Class.forName("com.mysql.jdbc.Driver"); 
                String url = "jdbc:mysql://127.0.0.1/Project?characterEncoding=utf8";
                java.sql.Connection con = DriverManager.getConnection(url, "root", "root"); 
                Statement st = con.createStatement();
                String Query="delete from project.groups where id='" + id + "'";
                st.executeUpdate(Query);
                }
                catch(Exception e){
                    System.out.println(e);
                }
            }
}