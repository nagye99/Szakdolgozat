package database;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@RegisterBeanMapper(TrainType.class)
public interface TrainTypeDao {
    @SqlUpdate("""
        CREATE TABLE IF NOT EXISTS TrainType (
            id IDENTITY PRIMARY KEY,
            numberOfSeats INTEGER NOT NULL,
            disposition VARCHAR NOT NULL
        );
        """
    )
    void createTable();

    @SqlUpdate("INSERT INTO TrainType (numberOfSeats, disposition) VALUES (:numberOfSeats, :disposition)")
    @GetGeneratedKeys
    long insertTrainType(@BindBean TrainType trainType);

    @SqlUpdate("DELETE FROM TrainType")
    void deleteTable();

    @SqlQuery("SELECT id FROM TrainType WHERE numberOfSeats = :numberOfSeats AND disposition = :disposition")
    Integer getTrainType(@Bind("numberOfSeats") Integer numberOfSeats, @Bind("disposition") String disposition);
}
