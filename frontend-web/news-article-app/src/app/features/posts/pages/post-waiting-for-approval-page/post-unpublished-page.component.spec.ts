import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostUnpublishedPageComponent } from './post-unpublished-page.component';

describe('PostUnpublishedPageComponent', () => {
  let component: PostUnpublishedPageComponent;
  let fixture: ComponentFixture<PostUnpublishedPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PostUnpublishedPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PostUnpublishedPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
