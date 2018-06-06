import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SubtitleRoutingModule } from './subtitle-routing.module';
import { SubtitleComponent } from './subtitle.component';

@NgModule({
    imports: [CommonModule, SubtitleRoutingModule],
    declarations: [SubtitleComponent]
})
export class SubtitleModule {}
