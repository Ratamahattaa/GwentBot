import { CommonModule } from "@angular/common";
import { Component, EventEmitter, OnInit, Output } from "@angular/core";
import { FormGroup, ReactiveFormsModule } from "@angular/forms";
import { finalize, tap } from "rxjs";
import {
    EnemyStatusControls,
    GameService,
} from "../../../../../../shared/services/game.service";
import { BoardStatusComponent } from "./components/board-status/board-status.component";

@Component({
    selector: "app-enemy-status",
    standalone: true,
    imports: [CommonModule, ReactiveFormsModule, BoardStatusComponent],
    templateUrl: "./enemy-status.component.html",
    styleUrls: ["./enemy-status.component.scss"],
})
export class EnemyStatusComponent implements OnInit {
    @Output() protected statusSubmitted = new EventEmitter<void>();

    protected enemyForm: FormGroup<EnemyStatusControls>;

    protected isDisabled = false;

    constructor(private readonly root: GameService) {}

    ngOnInit(): void {
        this.enemyForm = this.root.enemyStatusForm;
    }

    protected sendEnemyStatus(): void {
        if (!this.enemyForm.valid) return;
        this.isDisabled = true;
        this.root
            .sendGameState$()
            .pipe(
                tap(() => {
                    this.statusSubmitted.emit();
                }),
                finalize(() => {
                    this.isDisabled = false;
                })
            )
            .subscribe();
    }
}
