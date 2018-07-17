import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '../../node_modules/@angular/router';
import { MemberComponent } from './component/member/member.component';
import { MemberDetailComponent } from './component/member-detail/member-detail.component';

const routes: Routes = [
  { path: 'members', component: MemberComponent },
  { path: 'detail/:id', component: MemberDetailComponent}
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
