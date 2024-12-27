import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostUpdatePageComponent } from './post-update-page.component';

describe('PostUpdatePageComponent', () => {
  let component: PostUpdatePageComponent;
  let fixture: ComponentFixture<PostUpdatePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PostUpdatePageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PostUpdatePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
