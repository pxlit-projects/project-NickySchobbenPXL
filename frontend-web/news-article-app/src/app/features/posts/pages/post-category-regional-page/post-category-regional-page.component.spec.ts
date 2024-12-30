import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostCategoryRegionalPageComponent } from './post-category-regional-page.component';

describe('PostCategoryRegionalPageComponent', () => {
  let component: PostCategoryRegionalPageComponent;
  let fixture: ComponentFixture<PostCategoryRegionalPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PostCategoryRegionalPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PostCategoryRegionalPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
