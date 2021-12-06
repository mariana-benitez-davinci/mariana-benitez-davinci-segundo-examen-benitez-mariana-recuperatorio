package jdbc;

import java.sql.*;

public class MyJDBC01 {

    public static void main(String[] args) {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{

            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jdbc", "root", "root");

            statement = connection.createStatement();

            String sqlString = "SELECT * FROM estudiantes";

            resultSet = statement.executeQuery(sqlString);

            while(resultSet.next()){
                System.out.println(resultSet.getString(1) + " " + resultSet.getString(3) + " " + resultSet.getString(4));
            }

        }catch (SQLException sqlException1){

            System.out.println(sqlException1);

        }catch (Exception exception1){

            System.out.println(exception1);

        }finally {

            try {
                if ( resultSet != null){
                    resultSet.close();
                }
                if ( statement != null){
                    statement.close();
                }
                if (connection != null){
                    connection.close();
                }
            } catch (SQLException sqlException3){
                System.out.println(sqlException3);
            }

        }

    }

}