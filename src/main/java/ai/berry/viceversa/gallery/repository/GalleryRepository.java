package ai.berry.viceversa.gallery.repository;

import ai.berry.viceversa.gallery.entity.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {
}
