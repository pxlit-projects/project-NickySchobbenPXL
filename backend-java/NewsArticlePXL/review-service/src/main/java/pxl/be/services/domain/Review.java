package pxl.be.services.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="reviews")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long postId;

    private String reviewerName;
    private String reviewContent;

    @Enumerated(EnumType.STRING)
    private ReviewStatus reviewStatus;

    private LocalDate reviewDate;
}
