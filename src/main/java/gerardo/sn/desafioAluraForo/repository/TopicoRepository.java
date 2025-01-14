package gerardo.sn.desafioAluraForo.repository;

import gerardo.sn.desafioAluraForo.entity.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
}
