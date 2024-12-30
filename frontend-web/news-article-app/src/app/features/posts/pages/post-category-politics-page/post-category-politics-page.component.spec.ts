import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostCategoryPoliticsPageComponent } from './post-category-politics-page.component';

describe('PostCategoryPoliticsPageComponent', () => {
  let component: PostCategoryPoliticsPageComponent;
  let fixture: ComponentFixture<PostCategoryPoliticsPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PostCategoryPoliticsPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PostCategoryPoliticsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
