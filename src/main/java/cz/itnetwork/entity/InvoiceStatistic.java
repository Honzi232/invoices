package cz.itnetwork.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class InvoiceStatistic {

        private BigDecimal currentYearSum;
        private BigDecimal allTimeSum;
        private BigDecimal invoicesCount;

}
