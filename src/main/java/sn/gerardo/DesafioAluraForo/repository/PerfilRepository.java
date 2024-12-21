package sn.gerardo.DesafioAluraForo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.gerardo.DesafioAluraForo.entity.Perfil;

//public interface PerfilRepository {
//}
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
}