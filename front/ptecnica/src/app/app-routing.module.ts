import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListComponent } from './components/clients/list/list.component';

const routes: Routes =
    [
        {
            path: 'clients', component: ListComponent
        },
        {
            path: '', redirectTo: '/clients', pathMatch: 'full'
        },

    ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
