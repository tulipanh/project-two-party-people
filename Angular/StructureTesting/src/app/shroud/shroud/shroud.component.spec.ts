import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShroudComponent } from './shroud.component';

describe('ShroudComponent', () => {
  let component: ShroudComponent;
  let fixture: ComponentFixture<ShroudComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShroudComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShroudComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
