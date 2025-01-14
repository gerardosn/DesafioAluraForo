package gerardo.sn.desafioAluraForo.repository;

import gerardo.sn.desafioAluraForo.entity.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
}
