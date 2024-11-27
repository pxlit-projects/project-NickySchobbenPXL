import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostCreateorupdatePageComponent } from './post-createorupdate-page.component';

describe('PostCreateorupdatePageComponent', () => {
  let component: PostCreateorupdatePageComponent;
  let fixture: ComponentFixture<PostCreateorupdatePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PostCreateorupdatePageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PostCreateorupdatePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
