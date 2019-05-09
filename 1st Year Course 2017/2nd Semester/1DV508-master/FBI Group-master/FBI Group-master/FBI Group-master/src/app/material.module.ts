import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule, 
  MatMenuModule, MatIconModule, MatToolbarModule, MatCardModule, MatSelectModule,
  MatInputModule, MatGridListModule} from '@angular/material';

@NgModule({
  imports: [MatButtonModule, MatMenuModule, MatIconModule, MatCardModule, MatToolbarModule, MatSelectModule,MatInputModule, MatGridListModule],
  exports: [MatButtonModule, MatMenuModule, MatIconModule, MatCardModule, MatToolbarModule, MatSelectModule,MatInputModule, MatGridListModule],
})
export class MaterialModule { }