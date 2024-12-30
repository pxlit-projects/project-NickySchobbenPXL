import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostPublishedOverviewComponentComponent } from './post-published-overview-component.component';

describe('PostPublishedOverviewComponentComponent', () => {
  let component: PostPublishedOverviewComponentComponent;
  let fixture: ComponentFixture<PostPublishedOverviewComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PostPublishedOverviewComponentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PostPublishedOverviewComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
