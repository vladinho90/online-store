import { MaterialModule } from './../shared/material/material.module';

import { NgModule } from '@angular/core';
import { UserComponent } from './user/user.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
    declarations: [
        UserComponent
    ],
    imports: [
        MaterialModule,
        FormsModule,
        ReactiveFormsModule,
        HttpClientModule
    ],
    exports: [
        UserComponent
    ]
})
export class FeaturesModule {
    
}