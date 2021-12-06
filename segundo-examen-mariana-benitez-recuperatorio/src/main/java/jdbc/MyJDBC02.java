package jdbc;

import java.sql.*;
import java.util.ArrayList;

public class MyJDBC02 {

    public static void main(String[] args) {

        Connection connection = null;
        PreparedStatement consultaPreparada = null;
        ResultSet resultSet = null;

        try{

            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jdbc", "root", "root");
            connection.setAutoCommit(false);

            consultaPreparada = connection.prepareStatement("INSERT INTO estudiantes (dni, nombre, apellido) VALUES (?, ?, ?);");


            consultaPreparada.setString(1,"43630339");
            consultaPreparada.setString(2,"Mariana");
            consultaPreparada.setString(3,"Benitez");

            consultaPreparada.executeUpdate();

            connection.commit();

            resultSet = consultaPreparada.executeQuery("SELECT * FROM estudiantes");

            while(resultSet.next()){
                System.out.println(resultSet.getString(1) + " " + resultSet.getString(3) + " " + resultSet.getString(4));
            }

        }catch (SQLException sqlException1){

            System.out.println(sqlException1);
            if(connection != null){
                try{
                    connection.rollback();
                } catch (SQLException sqlException2){
                    System.out.println(sqlException2);
                }
            }

        }finally{

            try{
                if ( resultSet != null){
                    resultSet.close();
                }
                if ( consultaPreparada != null){
                    consultaPreparada.close();
                }
                if(connection != null){
                    connection.close();
                }
            }catch (SQLException sqlException3){
                System.out.println(sqlException3);
            }

        }

    }

}