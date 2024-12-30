import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostCategoryAllPageComponent } from './post-category-all-page.component';

describe('PostCategoryAllPageComponent', () => {
  let component: PostCategoryAllPageComponent;
  let fixture: ComponentFixture<PostCategoryAllPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PostCategoryAllPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PostCategoryAllPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
