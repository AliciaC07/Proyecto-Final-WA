import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewPurchasedComponent } from './view-purchased.component';

describe('ViewPurchasedComponent', () => {
  let component: ViewPurchasedComponent;
  let fixture: ComponentFixture<ViewPurchasedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewPurchasedComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewPurchasedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
