import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ControlDetailsPanelComponent } from './control-details-panel.component';

describe('ControlDetailsPanelComponent', () => {
  let component: ControlDetailsPanelComponent;
  let fixture: ComponentFixture<ControlDetailsPanelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ControlDetailsPanelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ControlDetailsPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
