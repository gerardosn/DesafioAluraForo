package sn.gerardo.DesafioAluraForo.repository;

//public class UsuarioRepository {
//}

import org.springframework.data.jpa.repository.JpaRepository;
import sn.gerardo.DesafioAluraForo.entity.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByCorreoElectronico(String correoElectronico);
}