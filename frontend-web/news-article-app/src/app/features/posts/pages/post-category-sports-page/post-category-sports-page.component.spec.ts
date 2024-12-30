import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostCategorySportsPageComponent } from './post-category-sports-page.component';

describe('PostCategorySportsPageComponent', () => {
  let component: PostCategorySportsPageComponent;
  let fixture: ComponentFixture<PostCategorySportsPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PostCategorySportsPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PostCategorySportsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
