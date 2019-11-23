package swd.SWDProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swd.SWDProject.entity.ReceiptDetail;

import java.util.List;

@Repository
public interface ReceiptDetailRepository extends JpaRepository<ReceiptDetail, Integer> {
    List<ReceiptDetail> findReceiptDetailsByReceiptId(int receiptId);
}
