import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostPublishedItemBigComponent } from './post-published-item-big.component';

describe('PostPublishedItemBigComponent', () => {
  let component: PostPublishedItemBigComponent;
  let fixture: ComponentFixture<PostPublishedItemBigComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PostPublishedItemBigComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PostPublishedItemBigComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
