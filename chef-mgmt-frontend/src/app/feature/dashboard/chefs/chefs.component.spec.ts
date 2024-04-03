import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChefsComponent } from './chefs.component';

describe('ChefsComponent', () => {
  let component: ChefsComponent;
  let fixture: ComponentFixture<ChefsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ChefsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ChefsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
