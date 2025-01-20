package gerardo.sn.desafioAluraForo.repository;

import gerardo.sn.desafioAluraForo.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByCorreoElectronico(String correoElectronico);

    boolean existsByCorreoElectronico(String correoElectronico);

    @Query("""
        SELECT u FROM Usuario u 
        WHERE u.correoElectronico = :correoElectronico 
        AND u.activo = true
    """)
    Optional<Usuario> findActiveByCorreoElectronico(String correoElectronico);
}
