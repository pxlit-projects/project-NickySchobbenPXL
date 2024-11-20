package pxl.be.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pxl.be.services.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
