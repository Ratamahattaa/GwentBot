import { Component, Input } from "@angular/core";
import { CommonModule } from "@angular/common";
import { YourInfoComponent } from "./components/your-info/your-info.component";
import { EnemyInfoComponent } from "./components/enemy-info/enemy-info.component";
import { TurnStatus } from "../../../../../../shared/models/game-model";

@Component({
    selector: "app-turn-status",
    standalone: true,
    imports: [CommonModule, YourInfoComponent, EnemyInfoComponent],
    templateUrl: "./turn-status.component.html",
    styleUrls: ["./turn-status.component.scss"],
})
export class TurnStatusComponent {
    @Input() public turnStatus: TurnStatus;
}
