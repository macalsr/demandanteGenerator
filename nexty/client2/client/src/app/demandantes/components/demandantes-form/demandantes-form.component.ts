import { Component, OnInit } from '@angular/core';
import {Demandante, HttpClientService} from "../../../services/httpclient.service";

@Component({
  selector: 'app-demandantes-form',
  templateUrl: './demandantes-form.component.html',
  styleUrls: ['./demandantes-form.component.scss']
})
export class DemandantesFormComponent implements OnInit {

  user: Demandante = new Demandante("","","","", "", "", "");

  constructor(private httpClientService: HttpClientService) { }

  ngOnInit() {
  }
  createDemandante(): void{
    this.httpClientService.createDemandante()
      .subscribe(data => {
        alert("Demandante created successfully.")
      });
  }
}
