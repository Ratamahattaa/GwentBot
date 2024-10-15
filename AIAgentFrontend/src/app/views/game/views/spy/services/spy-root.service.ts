import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { BehaviorSubject, Observable, tap } from "rxjs";
import { Card } from "../../../../../shared/models/game-model";
import { ApiService } from "./../../../../../shared/services/api.service";

@Injectable()
export class SpyRootService {
    private readonly deckCardsStore$ = new BehaviorSubject<Card[]>([]);
    private readonly choosenSpyCardsStore$ = new BehaviorSubject<Card[]>([]);

    constructor(
        private readonly api: ApiService,
        private readonly router: Router
    ) {}

    public get deckCards$(): Observable<Card[]> {
        return this.deckCardsStore$.asObservable();
    }

    public get choosenSpyCards$(): Observable<Card[]> {
        return this.choosenSpyCardsStore$.asObservable();
    }

    public get deckCards(): Card[] {
        return this.deckCardsStore$.value;
    }

    public get choosenSpyCards(): Card[] {
        return this.choosenSpyCardsStore$.value;
    }

    public set choosenSpyCards(cards: Card[]) {
        this.choosenSpyCardsStore$.next(cards);
    }

    public fetchCards$(): Observable<Card[]> {
        return this.api.getDeck$().pipe(
            tap((cards) => {
                this.deckCardsStore$.next(cards);
            })
        );
    }

    public cardStateWasChanged(card: Card): void {
        if (this.choosenSpyCards.includes(card)) {
            this.choosenSpyCards = this.choosenSpyCards.filter(
                (c) => c !== card
            );
        } else {
            this.choosenSpyCards = [card, ...this.choosenSpyCards];
        }
    }

    public sendStartingCardsToApi(): void {
        const ids = this.choosenSpyCards.map((card) => card.id);
        this.api
            .addCards$(ids)
            .pipe(
                tap(() => {
                    console.log(ids);
                    this.router.navigate(["/game/board"]);
                })
            )
            .subscribe();
    }
}
