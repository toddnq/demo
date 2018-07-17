import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { MemberComponent } from './component/member/member.component';
import { FormsModule } from '@angular/forms';      
import { DataTableModule, MenuItem } from 'primeng/primeng';  
import {AccordionModule} from 'primeng/accordion';
import { MemberDetailComponent } from './component/member-detail/member-detail.component';
import { AppRoutingModule } from './/app-routing.module';
import { MemberSearchComponent } from './component/member-search/member-search.component';
import { HttpClientModule } from '../../node_modules/@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    MemberComponent,
    MemberDetailComponent,
    MemberSearchComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    DataTableModule,
    AccordionModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
