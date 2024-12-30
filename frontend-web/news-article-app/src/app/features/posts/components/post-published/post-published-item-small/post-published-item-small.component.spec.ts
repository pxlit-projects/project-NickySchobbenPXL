import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostPublishedItemSmallComponent } from './post-published-item-small.component';

describe('PostPublishedItemSmallComponent', () => {
  let component: PostPublishedItemSmallComponent;
  let fixture: ComponentFixture<PostPublishedItemSmallComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PostPublishedItemSmallComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PostPublishedItemSmallComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
