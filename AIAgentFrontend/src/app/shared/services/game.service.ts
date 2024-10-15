import { Injectable } from "@angular/core";
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { BehaviorSubject, Observable, tap } from "rxjs";
import { Card, PlayFromBackend, TurnStatus } from "../models/game-model";
import { ApiService } from "./api.service";

export interface EnemyStatusControls {
    enemyAllPoints: FormControl<number>;
    enemySpyPoints: FormControl<number>;
    isFrost: FormControl<boolean>;
    isRain: FormControl<boolean>;
    isFog: FormControl<boolean>;
    enemyPassed: FormControl<boolean>;
}

@Injectable({ providedIn: "root" })
export class GameService {
    public readonly hand$ = new BehaviorSubject<Card[]>([]);
    public readonly turnStatusStore$ = new BehaviorSubject<TurnStatus[]>([]);
    public readonly isLastCardSpy$ = new BehaviorSubject<boolean>(false);

    public enemyStatusForm: FormGroup<EnemyStatusControls>;

    constructor(private readonly api: ApiService) {}

    public reset(): void {
        this.hand$.next([]);
        this.turnStatusStore$.next([]);
        this.isLastCardSpy$.next(false);
        this.buildStatusForm();
    }

    private buildStatusForm(): void {
        this.enemyStatusForm = new FormGroup<EnemyStatusControls>({
            enemyAllPoints: new FormControl<number>(0, {
                nonNullable: true,
                validators: [Validators.min(0)],
            }),
            enemySpyPoints: new FormControl<number>(0, {
                nonNullable: true,
                validators: [Validators.min(0)],
            }),
            isFrost: new FormControl<boolean>(false, { nonNullable: true }),
            isRain: new FormControl<boolean>(false, { nonNullable: true }),
            isFog: new FormControl<boolean>(false, { nonNullable: true }),
            enemyPassed: new FormControl<boolean>(false, { nonNullable: true }),
        });
    }

    public sendGameState$(): Observable<PlayFromBackend> {
        return this.api.sendGameState$(this.enemyStatusForm.getRawValue()).pipe(
            tap((play: PlayFromBackend) => {
                this.isLastCardSpy$.next(play.spy);
                this.addTurn({
                    enemiesSpyPoints:
                        this.enemyStatusForm.controls.enemyAllPoints.getRawValue(),
                    turn: this.turnStatusStore$.value.length,
                    player: play,
                });
            }),
            tap((data) => {
                console.log("status send:", this.enemyStatusForm.getRawValue());
                console.log("status get:", data);
            })
            // switchMap(() => this.getHand$())
        );
    }

    private addTurn(turn: TurnStatus): void {
        this.turnStatusStore$.next([...this.turnStatusStore$.value, turn]);
    }
}
