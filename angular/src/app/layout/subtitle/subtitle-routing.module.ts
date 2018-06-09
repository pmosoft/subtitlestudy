import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SubtitleComponent } from './subtitle.component';

const routes: Routes = [
  {
      path: '', component: SubtitleComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class SubtitleRoutingModule { }
