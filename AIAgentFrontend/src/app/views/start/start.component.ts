import { CommonModule } from "@angular/common";
import { Component } from "@angular/core";
import { RouterModule } from "@angular/router";

@Component({
    selector: "app-start",
    templateUrl: "./start.component.html",
    standalone: true,
    imports: [CommonModule, RouterModule],
    styleUrls: ["./start.component.scss"],
})
export class StartComponent {}
