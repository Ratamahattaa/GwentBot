import { Component, Input } from "@angular/core";
import { CommonModule } from "@angular/common";
import { NumberDescriptionComponent } from "../number-description/number-description.component";

@Component({
    selector: "app-enemy-info",
    standalone: true,
    imports: [CommonModule, NumberDescriptionComponent],
    templateUrl: "./enemy-info.component.html",
    styleUrls: ["./enemy-info.component.scss"],
})
export class EnemyInfoComponent {
    @Input() public counter: number;
    @Input() public enemyPoints: number;
}
