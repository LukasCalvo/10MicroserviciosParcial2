package duoc.cl.ReviewService.repository;

import duoc.cl.ReviewService.model.Resena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResenaRepository extends JpaRepository<Resena, Integer> {

    List<Resena> findByJuegoId(int juegoId);

    @Query("SELECT AVG(r.puntaje) FROM Resena r WHERE r.juegoId = :juegoId")
    Double calcularPromedioPorJuego(int juegoId);
}