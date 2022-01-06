import { Component, OnInit } from '@angular/core';
import {Demandante, HttpClientService} from "../../../services/httpclient.service";

@Component({
  selector: 'app-demandantes',
  templateUrl: './demandantes.component.html',
  styleUrls: ['./demandantes.component.scss']
})
export class DemandantesComponent implements OnInit {

  // demandante: Demandante[],

  constructor(
    private httpClientService: HttpClientService
  ) { }

  ngOnInit() {
    this.httpClientService.getDemandantesById().subscribe(
      // response => this.handleSuccessfulResponse(response),
    );
  }
  // handleSuccessfulResponse(response){
  //   this.demandantes = response;
  // }

}
