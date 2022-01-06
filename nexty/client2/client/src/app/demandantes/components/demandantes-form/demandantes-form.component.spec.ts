import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DemandantesFormComponent } from './demandantes-form.component';

describe('DemandantesFormComponent', () => {
  let component: DemandantesFormComponent;
  let fixture: ComponentFixture<DemandantesFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DemandantesFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DemandantesFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
