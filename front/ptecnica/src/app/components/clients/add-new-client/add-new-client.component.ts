import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import {MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { ClientRequest } from '../../common/model';
import { ListComponent } from '../list/list.component';

@Component({
  selector: 'app-add-new-client',
  templateUrl: './add-new-client.component.html',
  styleUrls: ['./add-new-client.component.scss']
})
export class AddNewClientComponent implements OnInit {
  clientForm = this.fb.group({
    nameInput: ['', [Validators.required]],
    phoneInput: ['', [Validators.required]],
    emailInput: ['', [Validators.required, Validators.email]],
    startDateInput: ['', [Validators.required]],
    endDateInput: ['', [Validators.required]],
  });

  constructor(public dialogRef: MatDialogRef<ListComponent>,@Inject(MAT_DIALOG_DATA) public data: ClientRequest, private fb: FormBuilder) {}
  
  ngOnInit(): void {}

  onNoClick(): void {
    if(this.clientForm.valid){
      console.log('Valid Form');
      this.dialogRef.close({data:this.data});
    }else{
      console.log('InValid Form');
    }
  }
}
