import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CommentSectionContainerComponent } from './comment-section-container.component';

describe('CommentSectionContainerComponent', () => {
  let component: CommentSectionContainerComponent;
  let fixture: ComponentFixture<CommentSectionContainerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CommentSectionContainerComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CommentSectionContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
