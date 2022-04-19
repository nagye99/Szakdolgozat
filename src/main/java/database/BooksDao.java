package database;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@RegisterBeanMapper(Books.class)
public interface BooksDao {
    @SqlUpdate("""
        CREATE TABLE IF NOT EXISTS Books (
            id IDENTITY PRIMARY KEY,
            seat INTEGER NOT NULL,
            passengerName VARCHAR NOT NULL,
            departingTrainId INTEGER,
             FOREIGN KEY (departingTrainId) REFERENCES DepartingTrain(id)
        );
        """
    )
    void createTable();

    @SqlUpdate("INSERT INTO DepartingTrain (seat, passengerName, departingTrainId) VALUES (:seat, :passengerName, :departingTrainId) ")
    @GetGeneratedKeys
    long insertBook(@BindBean Books books);

    @SqlUpdate("DELETE FROM Books")
    void deleteTable();
}
