package database;

import model.train.StateTrain;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RegisterBeanMapper(DepartingTrain.class)
public interface DepartingTrainDao {
    @SqlUpdate("""
        CREATE TABLE IF NOT EXISTS DepartingTrain (
            id IDENTITY PRIMARY KEY,
            departTime TIMESTAMP NOT NULL,
            trainName VARCHAR NOT NULL,
            trainTypeId INTEGER,
             FOREIGN KEY (trainTypeId) REFERENCES TrainType(id)
        );
        """
    )
    void createTable();

    @SqlUpdate("INSERT INTO DepartingTrain (departTime, trainName, trainTypeId) VALUES (:departTime, :trainName, :trainTypeId)")
    @GetGeneratedKeys
    long insertDepartingTrain(@BindBean DepartingTrain departingTrain);

    @SqlQuery("SELECT * FROM DepartingTrain ORDER BY departTime")
    List<DepartingTrain> listDepartingTrains();

    @SqlQuery("SELECT  numberOfSeats FROM DEPARTINGTRAIN JOIN TRAINTYPE ON DEPARTINGTRAIN.trainTypeId = TRAINTYPE.id WHERE DEPARTINGTRAIN.departTime = :departTime AND DEPARTINGTRAIN.trainName = :trainName")
    Integer getNumberOfSeats(@Bind("departTime")LocalDateTime departTime, @Bind("trainName") String trainName);

    @SqlQuery("SELECT  disposition FROM DEPARTINGTRAIN JOIN TRAINTYPE ON DEPARTINGTRAIN.trainTypeId = TRAINTYPE.id WHERE DEPARTINGTRAIN.departTime = :departTime AND DEPARTINGTRAIN.trainName = :trainName")
    String getDisposition(@Bind("departTime")LocalDateTime departTime, @Bind("trainName") String trainName);

    @SqlUpdate("DELETE FROM DepartingTrain")
    void deleteTable();
}
