import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostCategorySciencePageComponent } from './post-category-science-page.component';

describe('PostCategorySciencePageComponent', () => {
  let component: PostCategorySciencePageComponent;
  let fixture: ComponentFixture<PostCategorySciencePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PostCategorySciencePageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PostCategorySciencePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
