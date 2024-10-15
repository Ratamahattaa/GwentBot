import { Component, Input } from "@angular/core";
import { CommonModule } from "@angular/common";

@Component({
    selector: "app-number-description",
    standalone: true,
    imports: [CommonModule],
    templateUrl: "./number-description.component.html",
    styleUrls: ["./number-description.component.scss"],
})
export class NumberDescriptionComponent {
    @Input() public description: string;
    @Input() public number: number | string;
}
