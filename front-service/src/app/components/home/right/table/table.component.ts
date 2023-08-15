import {Component, inject, OnInit} from "@angular/core";
import {MatIconModule} from "@angular/material/icon";
import {MatPaginatorModule, PageEvent} from "@angular/material/paginator";
import {PartService} from "../../../../services/part.service";
import {PartResponsePaginated} from "../../../../utility/response/partResponsePaginated";
import {NgForOf} from "@angular/common";

@Component({
  selector: 'right-component',
  templateUrl: './table.html',
  styleUrls: ['./table.css'],
  imports: [
    MatIconModule,
    MatPaginatorModule,
    NgForOf
  ],
  standalone: true
})
export class TableComponent implements OnInit{
  length = 0;
  pageSize = 0;
  pageIndex = 0;
  pageSizeOptions = [5, 10];

  hidePageSize = false;
  showPageSizeOptions = true;
  showFirstLastButtons = true;
  disabled = false;

  pageEvent: any;


  private partService = inject(PartService);

  //https://stackoverflow.com/questions/50260404/typescript-custom-interface-variable-declaration
  parts = <PartResponsePaginated>{};


  ngOnInit(): void {
    this.partService.getPartsWithPagination(0,5)
      .subscribe( part => {
        this.length = part.nrOfParts;
        this.parts = part;
      });
  }

  handlePageEvent(e: PageEvent) {
    this.partService.getPartsWithPagination(e.pageIndex, e.pageSize)
      .subscribe(part => {
      this.length = part.nrOfParts;
      this.parts = part;
        console.log(part)
    })


    console.log(e.length + '++++')
    this.pageEvent = e;
    this.length = e.length;
    this.pageSize = e.pageSize;
    this.pageIndex = e.pageIndex;
  }






}
