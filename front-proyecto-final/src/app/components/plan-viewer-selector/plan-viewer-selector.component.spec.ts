import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlanViewerSelectorComponent } from './plan-viewer-selector.component';

describe('PlanViewerSelectorComponent', () => {
  let component: PlanViewerSelectorComponent;
  let fixture: ComponentFixture<PlanViewerSelectorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PlanViewerSelectorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PlanViewerSelectorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
