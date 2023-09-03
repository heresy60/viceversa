package ai.berry.viceversa.gallery.repository;

import ai.berry.viceversa.gallery.entity.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GalleryRepository extends JpaRepository<Gallery, Long>, JpaSpecificationExecutor<Gallery> {
}
