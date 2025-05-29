import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InicialComponent } from './inicial.component';

import { PostsComponent } from 'src/app/modules/posts/posts.component';
import { RouterModule } from '@angular/router';
import { FlexLayoutModule } from '@angular/flex-layout';
import { SharedModule } from 'src/app/shared/shared.module';


@NgModule({
  declarations: [InicialComponent],
  imports: [
    CommonModule,
    RouterModule,
    FlexLayoutModule,
    SharedModule
  ]
})
export class InicialModule { }
