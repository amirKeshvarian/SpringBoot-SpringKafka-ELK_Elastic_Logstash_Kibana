package org.company.project.model.repository;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
@Repository
@Scope("singleton")
public class SequenceGenerator implements AutoCloseable {
    private Connection connection;
    private PreparedStatement preparedStatement;
    public SequenceGenerator () throws Exception{
        Class.forName("oracle.jdbc.driver.OracleDriver");
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orclpdb1","amir_seq","myjava123");
    }
    public Long nextVal () throws SQLException {
        return SequenceGeneratorWithPooled.generateId(connection);
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
