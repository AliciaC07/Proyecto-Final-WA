import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListJobsComponent } from './list-jobs.component';

describe('ListJobsComponent', () => {
  let component: ListJobsComponent;
  let fixture: ComponentFixture<ListJobsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListJobsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListJobsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
