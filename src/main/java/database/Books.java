package database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Books {
    private Integer passengerName;
    private Integer seat;
    private Integer departingTrainId;
}
