package com.cice.maven.test.java;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectToDatabaseServlet extends HttpServlet {


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    //lo primero que necesito es una cade de conexión. Esta cadena será un String con la url
        // maquina localhost
        //puerto el que indicaba ,mamp
        String url="jdbc:mysql://localhost:8889";
        //si no existe la clase debe darme error.Esto se consigue cn el cassforname
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //si continuo en el try es xk existe la clase por lo q ahora necesito
            //conecar cn la bbdd para ello necesito un conector:
            Connection connection= DriverManager.getConnection(url,"root","root");
            //ahora necesito poder mandar la info a la bbdd
            //necesito poder hacer llamdas para ello necesito el objeto Statement
            Statement statement = connection.createStatement();

            //para mandarle info se crea un string
            String sentencia="CREATE DATABASE cice;USE cice;CREATE TABLE test (id int AUTO_INCREMENT,titulo VARCHAR(255) NOT NULL,PRIMARY KEY (id))";

            statement.execute(sentencia);

            statement.close();

            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {//excepcion de Connection
            e.printStackTrace();
        }


    }
}
