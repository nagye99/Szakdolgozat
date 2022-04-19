package database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartingTrain {
    private LocalDateTime departTime;
    private String trainName;
    private Integer trainTypeId;
}
