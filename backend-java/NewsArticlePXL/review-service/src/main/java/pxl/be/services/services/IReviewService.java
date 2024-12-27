package pxl.be.services.services;

import pxl.be.services.domain.dto.ReviewRequest;

public interface IReviewService {

    Long addReviewForPost(ReviewRequest reviewRequest);
}
