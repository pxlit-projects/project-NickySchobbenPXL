package pxl.be.services.service;

import pxl.be.services.domain.dto.ReviewRequest;

public interface IReviewService {

    Long addReviewForPost(ReviewRequest reviewRequest);
}
