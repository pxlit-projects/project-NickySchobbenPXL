import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostConceptPageComponent } from './post-concept-page.component';

describe('PostConceptPageComponent', () => {
  let component: PostConceptPageComponent;
  let fixture: ComponentFixture<PostConceptPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PostConceptPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PostConceptPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
