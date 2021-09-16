import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ClientService } from '../../common/client.service';
import { ClientRequest } from '../../common/model';
import { CommonUtils } from '../../common/utils';
import { AddNewClientComponent } from '../add-new-client/add-new-client.component';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent extends CommonUtils implements OnInit {

  displayedColumns: string[] = ['sharedKey', 'businessId', 'email', 'phone', 'dataAdded'];

  dataSource: MatTableDataSource<any>;
  @ViewChild(MatPaginator)
  paginator!: MatPaginator;
  @ViewChild(MatSort)
  sort!: MatSort;

  clientFormSearch = this.fb.group({
    nameInput: [''],
    phoneInput: [''],
    emailInput: [''],
    startDateInput: [''],
    endDateInput: [''],
  });

  toggleAdvancedSearch:boolean = false;

  client: ClientRequest = {
    name:'',
    phone:'',
    email:'',
    startDate:'',
    endDate:'',
  }

  clientSearch: ClientRequest = {
    name:'',
    phone:'',
    email:'',
    startDate:'',
    endDate:'',
  }

  searchText:string='';

  constructor(private clientService:ClientService, public dialog: MatDialog, snack: MatSnackBar, private fb: FormBuilder)
  {
    super(snack)
    this.dataSource=new MatTableDataSource();
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  ngOnInit(): void {
    this.tableLoad();
  }

  tableLoad(){
    this.clientService.getList(this.searchText).subscribe(
      data =>this.tableUpdate(data)
    )
  }

  tableUpdate(data: any): void{
    this.dataSource.data = data;
    setTimeout(() => {
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  tableLoadAdvanced(){
    console.log(JSON.stringify(this.clientSearch));
    
    this.clientService.getListAdvanced(this.clientSearch).subscribe(
      data =>this.tableUpdate(data)
    )
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(AddNewClientComponent, {
      width: '350px',
      data: this.client
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result){
        this.clientService.addNew(result.data).subscribe(() =>
          {
              this.tableLoad();
              this.openSnackBar('Stored Correctly...', 'Ok');
          }
        )
      }
    });
  }

  toggleAdvanced(){
    this.toggleAdvancedSearch = !this.toggleAdvancedSearch;
  }

  exportFile():void {
    this.exportAsXLSX(this.dataSource.data, 'lista-clientes');
    this.openSnackBar('Generated Correctly...', 'Ok');
  }

}
