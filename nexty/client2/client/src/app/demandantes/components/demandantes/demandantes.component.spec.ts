import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DemandantesComponent } from './demandantes.component';

describe('DemandantesComponent', () => {
  let component: DemandantesComponent;
  let fixture: ComponentFixture<DemandantesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DemandantesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DemandantesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
