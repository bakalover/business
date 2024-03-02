package business.application.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import business.application.demo.repo.entity.AlbumDao;

public interface AlbumRepository extends JpaRepository<AlbumDao ,Long> {
}
