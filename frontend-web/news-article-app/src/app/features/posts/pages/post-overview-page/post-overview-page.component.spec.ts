import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostOverviewPageComponent } from './post-overview-page.component';

describe('PostOverviewPageComponent', () => {
  let component: PostOverviewPageComponent;
  let fixture: ComponentFixture<PostOverviewPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PostOverviewPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PostOverviewPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
