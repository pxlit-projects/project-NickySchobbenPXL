package pxl.be.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pxl.be.services.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
