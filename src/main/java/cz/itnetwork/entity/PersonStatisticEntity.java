package cz.itnetwork.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
public class PersonStatisticEntity {

    private Long personId;
    private String personName;
    private BigDecimal revenue;

}
