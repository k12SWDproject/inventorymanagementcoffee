package swd.SWDProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swd.SWDProject.entity.Receipt;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {
    Receipt findReceiptById(int id);
}
