package duoc.cl.WalletService.repository;

import duoc.cl.WalletService.model.Billetera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BilleteraRepository extends JpaRepository<Billetera, Integer> {
    Optional<Billetera> findByUsuarioEmail(String usuarioEmail);
}