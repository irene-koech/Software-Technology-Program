import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule, 
  MatMenuModule, MatIconModule, MatToolbarModule, MatCardModule, MatSelectModule,
  MatInputModule, MatTableModule, MatGridListModule,MatSnackBarModule,MatDialogModule,MatCheckboxModule} from '@angular/material';

@NgModule({
  imports: [MatButtonModule, MatMenuModule, MatIconModule, MatCardModule, 
    MatToolbarModule,MatTableModule, MatSelectModule,MatInputModule, MatGridListModule,MatSnackBarModule,MatDialogModule
    ,MatCheckboxModule],
  exports: [MatButtonModule, MatMenuModule, MatIconModule, MatCardModule, 
    MatToolbarModule, MatTableModule, MatSelectModule,MatInputModule, MatGridListModule,MatSnackBarModule,MatDialogModule
    ,MatCheckboxModule],
})
export class MaterialModule { }