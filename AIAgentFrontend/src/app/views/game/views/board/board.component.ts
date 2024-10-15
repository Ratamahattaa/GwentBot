import { CommonModule } from "@angular/common";
import { Component } from "@angular/core";
import { Observable } from "rxjs";
import { TurnStatus } from "../../../../shared/models/game-model";
import { GameService } from "../../../../shared/services/game.service";
import { EnemyStatusComponent } from "./components/enemy-status/enemy-status.component";
import { TurnStatusComponent } from "./components/turn-status/turn-status.component";

@Component({
    selector: "app-board",
    standalone: true,
    imports: [CommonModule, EnemyStatusComponent, TurnStatusComponent],
    templateUrl: "./board.component.html",
    styleUrls: ["./board.component.scss"],
})
export class BoardComponent {
    protected currentTurn = 0;
    protected readonly turnStatuses$: Observable<TurnStatus[]>;

    constructor(private readonly root: GameService) {
        this.turnStatuses$ = this.root.turnStatusStore$.asObservable();
    }

    protected onStatusAdd(): void {
        this.currentTurn = this.root.turnStatusStore$.value.length;
    }
}
