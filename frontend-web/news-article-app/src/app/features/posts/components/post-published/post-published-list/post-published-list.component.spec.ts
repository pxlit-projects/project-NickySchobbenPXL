import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostPublishedListComponent } from './post-published-list.component';

describe('PostPublishedListComponent', () => {
  let component: PostPublishedListComponent;
  let fixture: ComponentFixture<PostPublishedListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PostPublishedListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PostPublishedListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
