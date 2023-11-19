package cz.itnetwork.entity.repository;

import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.PersonStatisticEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long>, JpaSpecificationExecutor<InvoiceEntity> {

    List<InvoiceEntity> findById(long id);

    List<InvoiceEntity> getAllByBuyerId(long id);

    List<InvoiceEntity> getAllBySellerId(long id);

    @Query(value = "SELECT SUM(`price`) FROM `invoice` WHERE YEAR(issued) = YEAR(CURDATE())", nativeQuery = true)
    BigDecimal getCurrentYearSum();

    @Query(value = "SELECT SUM(`price`) FROM `invoice`", nativeQuery = true)
    BigDecimal getAllTimeSum();

    @Query(value = "SELECT COUNT(*) FROM `invoice`", nativeQuery = true)
    BigDecimal getInvoiceCount();

    @Query(value = """
            SELECT NEW cz.itnetwork.entity.PersonStatisticEntity(p.id,p.name,SUM(i.price))
            FROM person AS p
            JOIN p.sales i GROUP BY p.id
            """)
    List<PersonStatisticEntity> findAllRevenue();
}

