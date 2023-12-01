package org.company.project.model.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import org.company.project.common.exception.UserNotFoundException;
import org.company.project.model.domain.Person;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
@Scope("prototype")
public class Crud implements AutoCloseable {
    private Connection connection;
    private PreparedStatement preparedStatement;


    public Crud () throws Exception{
        Class.forName("oracle.jdbc.driver.OracleDriver");
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orclpdb1","amir_read","myjava123");
        connection.setAutoCommit(false);
    }
    public Person findPersonById (Person person) throws SQLException, UserNotFoundException {
        preparedStatement = connection.prepareStatement("select * from person where person_id=?");
        preparedStatement.setLong(1, person.getPersonId());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            return person.setName(resultSet.getString("name"))
                    .setFamily(resultSet.getString("family"))
                    .setDeletedDate(resultSet.getTimestamp("deleted_date"))
                    .setRecordVersion(resultSet.getLong("record_version"));
        }
        throw  new UserNotFoundException();
    }
    public void saveReceivedPerson (Person person) throws SQLException {
        preparedStatement = connection.prepareStatement("insert into person (person_id,name,family,record_version) values (?,?,?,?)");
        preparedStatement.setLong(1, person.getPersonId());
        preparedStatement.setString(2, person.getName());
        preparedStatement.setString(3, person.getFamily());
        preparedStatement.setLong(4, person.getRecordVersion());
        preparedStatement.execute();
    }

    public void commit () throws Exception {
        connection.commit();
    }
    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
