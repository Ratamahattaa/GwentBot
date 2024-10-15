import { Injectable } from "@angular/core";
import { ApiService } from "./api.service";
import { Card, Faction } from "../models/game-model";
import { BehaviorSubject, Observable, tap } from "rxjs";
import { Router } from "@angular/router";

@Injectable({
    providedIn: "root",
})
export class PregameService {
    private readonly allCardsStore$ = new BehaviorSubject<Card[]>([]);
    private readonly choosenCardsStore$ = new BehaviorSubject<Card[]>([]);
    private readonly factionStore$ = new BehaviorSubject<null | Faction>(null);

    public get allCards$(): Observable<Card[]> {
        return this.allCardsStore$.asObservable();
    }

    private get allCards(): Card[] {
        return this.allCardsStore$.value;
    }

    private set allCards(cards: Card[]) {
        this.allCardsStore$.next(cards);
    }

    public get choosenCards$(): Observable<Card[]> {
        return this.choosenCardsStore$.asObservable();
    }

    private get choosenCards(): Card[] {
        return this.choosenCardsStore$.value;
    }

    private set choosenCards(cards: Card[]) {
        this.choosenCardsStore$.next(cards);
    }

    public get faction(): Faction | null {
        return this.factionStore$.value;
    }

    public set faction(faction: Faction | null) {
        this.choosenCards = [];
        this.factionStore$.next(faction);
    }

    constructor(
        private readonly api: ApiService,
        private readonly router: Router
    ) {}

    public cardStateWasChanged(card: Card): void {
        if (this.choosenCards.includes(card)) {
            this.choosenCards = this.choosenCards.filter((c) => c !== card);
        } else {
            if (this.choosenCards.length < 10) {
                this.choosenCards = [card, ...this.choosenCards];
            }
        }
    }

    public sendStartingCardsToApi(): void {
        const ids = this.choosenCards.map((card) => card.id);
        this.api
            .sendCardsToApi$(ids)
            .pipe(
                tap(() => {
                    this.router.navigate(["/game"]);
                })
            )
            .subscribe();
    }

    public fetchStartingCardsFromApi(): void {
        if (this.faction === null) {
            this.router.navigate(["/faction"]);
            return;
        }
        this.api
            .getAllCardsByFaction$(this.faction)
            .pipe(
                tap((cards) => {
                    this.allCardsStore$.next(cards);
                })
            )
            .subscribe();
    }

    public resetGame$(): Observable<unknown> {
        this.allCardsStore$.next([]);
        this.choosenCardsStore$.next([]);
        this.factionStore$.next(null);
        return this.api.newGame$();
    }
}
