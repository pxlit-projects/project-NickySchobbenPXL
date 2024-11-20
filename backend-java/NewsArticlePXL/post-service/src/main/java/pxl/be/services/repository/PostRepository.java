package pxl.be.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pxl.be.services.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
