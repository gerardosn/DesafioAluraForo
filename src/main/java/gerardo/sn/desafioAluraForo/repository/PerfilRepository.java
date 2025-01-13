package gerardo.sn.desafioAluraForo.repository;

import gerardo.sn.desafioAluraForo.entity.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
}
