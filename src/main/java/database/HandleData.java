package database;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.tinylog.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class HandleData {

    private static String databaseDirectory = System.getProperty("user.home") + File.separator + ".train";

    private static void MakeDataDir() {
        try {
            Path databaseDirectoryPath = Path.of(databaseDirectory);
            if (Files.notExists(databaseDirectoryPath)) {
                Files.createDirectory(databaseDirectoryPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Jdbi CreateConnection() {
        MakeDataDir();
        String databaseURL = databaseDirectory + File.separator + "bookSeat";
        Jdbi jdbi = Jdbi.create("jdbc:h2:file:" + databaseURL);
        jdbi.installPlugin(new SqlObjectPlugin());
        return jdbi;
    }

    private static void CreatTables() {
        Jdbi jdbi = CreateConnection();
        try (Handle handle = jdbi.open()) {
            TrainTypeDao dao = handle.attach(TrainTypeDao.class);
            dao.createTable();

            DepartingTrainDao dao2 = handle.attach(DepartingTrainDao.class);
            dao2.createTable();

            BooksDao dao3 = handle.attach(BooksDao.class);
            dao3.createTable();
        }
    }

    private static Integer getTrainType(Integer numberOfSeats, String disposition) {
        Jdbi jdbi = CreateConnection();
        try (Handle handle = jdbi.open()) {
            TrainTypeDao dao = handle.attach(TrainTypeDao.class);
            return dao.getTrainType(numberOfSeats, disposition);
        }
    }

    public static void initializeDatabase() {
        HandleData.CreatTables();
        HandleData.saveTrainType(new TrainType(40, "SampleTrain"));
        HandleData.saveTrainType(new TrainType(60, "SampleTrain"));
        HandleData.saveTrainType(new TrainType(80, "SampleTrain"));
        HandleData.saveDepartingTrain(new DepartingTrain(LocalDateTime.of(2022, 05, 01, 15, 10), "Kócsag", HandleData.getTrainType(40, "SampleTrain")));
        HandleData.saveDepartingTrain(new DepartingTrain(LocalDateTime.of(2022, 05, 01, 15, 10), "Hortobágy", HandleData.getTrainType(60, "SampleTrain")));
        HandleData.saveDepartingTrain(new DepartingTrain(LocalDateTime.of(2022, 05, 01, 16, 10), "Hajdú", HandleData.getTrainType(40, "SampleTrain")));
        HandleData.saveDepartingTrain(new DepartingTrain(LocalDateTime.of(2022, 05, 01, 17, 10), "Rózsa", HandleData.getTrainType(80, "SampleTrain")));
    }

    public static void deleteDatabase() {
        HandleData.CreatTables();
        Jdbi jdbi = CreateConnection();
        try (Handle handle = jdbi.open()) {
            BooksDao dao3 = handle.attach(BooksDao.class);
            dao3.deleteTable();

            DepartingTrainDao dao2 = handle.attach(DepartingTrainDao.class);
            dao2.deleteTable();

            TrainTypeDao dao = handle.attach(TrainTypeDao.class);
            dao.deleteTable();
        }
    }

    public static void saveTrainType(TrainType trainType) {
        Jdbi jdbi = CreateConnection();
        try (Handle handle = jdbi.open()) {
            TrainTypeDao dao = handle.attach(TrainTypeDao.class);
            dao.insertTrainType(trainType);
        }
    }

    public static void saveDepartingTrain(DepartingTrain departingTrain) {
        Jdbi jdbi = CreateConnection();
        try (Handle handle = jdbi.open()) {
            DepartingTrainDao dao = handle.attach(DepartingTrainDao.class);
            dao.insertDepartingTrain(departingTrain);
        }
    }

    public static void saveBooks(Books books) {
        Jdbi jdbi = CreateConnection();
        try (Handle handle = jdbi.open()) {
            BooksDao dao = handle.attach(BooksDao.class);
            dao.insertBook(books);
        }
    }

    public static List<String> getDepartingTrains() {
        Jdbi jdbi = CreateConnection();
        try (Handle handle = jdbi.open()) {
            DepartingTrainDao dao = handle.attach(DepartingTrainDao.class);
            List<DepartingTrain> departingTrains = dao.listDepartingTrains();
            return departingTrains.stream().map(train -> train.getDepartTime() + " " + train.getTrainName()).toList();
        }
    }

    public static  Integer getSeatsNum(LocalDateTime departTime, String trainName){
        Jdbi jdbi = CreateConnection();
        try (Handle handle = jdbi.open()) {
            DepartingTrainDao dao = handle.attach(DepartingTrainDao.class);
            return dao.getNumberOfSeats(departTime, trainName);
        }
    }


    public static  String getDisposition(LocalDateTime departTime, String trainName){
        Jdbi jdbi = CreateConnection();
        try (Handle handle = jdbi.open()) {
            DepartingTrainDao dao = handle.attach(DepartingTrainDao.class);
            return dao.getDisposition(departTime, trainName);
        }
    }

    public static void main(String[] args) {
        HandleData.CreatTables();
        HandleData.saveTrainType(new TrainType(40, "SampleTrain"));
        HandleData.saveTrainType(new TrainType(80, "SampleTrain"));
        HandleData.saveDepartingTrain(new DepartingTrain(LocalDateTime.of(2022, 05, 01, 15, 10), "Kócsag", HandleData.getTrainType(40, "SampleTrain")));
        HandleData.saveDepartingTrain(new DepartingTrain(LocalDateTime.of(2022, 05, 01, 16, 10), "Hajdú", HandleData.getTrainType(40, "SampleTrain")));
        HandleData.saveDepartingTrain(new DepartingTrain(LocalDateTime.of(2022, 05, 01, 17, 10), "Rózsa", HandleData.getTrainType(80, "SampleTrain")));
    }
}