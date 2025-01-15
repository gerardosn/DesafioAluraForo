package gerardo.sn.desafioAluraForo.repository;

import gerardo.sn.desafioAluraForo.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    Optional<Curso> findByNombre(String nombre);
}
