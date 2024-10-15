import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import {
    Card,
    EnemyStatus,
    Faction,
    PlayFromBackend,
} from "../models/game-model";

@Injectable({
    providedIn: "root",
})
export class ApiService {
    private readonly apiUrl = environment.apiUrl;

    constructor(private readonly http: HttpClient) {}

    public getAllCardsByFaction$(faction: Faction): Observable<Card[]> {
        return this.http.get<Card[]>(
            `${this.apiUrl}/gwent/init-deck?abbreviation=${faction}`
        );
    }

    public sendCardsToApi$(ids: number[]): Observable<number[]> {
        return this.http.post<number[]>(`${this.apiUrl}/gwent/hand`, {
            cardsId: ids,
        });
    }

    public sendGameState$(gameState: EnemyStatus): Observable<PlayFromBackend> {
        return this.http.post<PlayFromBackend>(
            `${this.apiUrl}/gwent/board`,
            gameState
        );
    }

    public getHand$(): Observable<Card[]> {
        return this.http.get<Card[]>(`${this.apiUrl}/gwent/hand`);
    }

    public getDeck$(): Observable<Card[]> {
        return this.http.get<Card[]>(`${this.apiUrl}/gwent/deck`);
    }

    public sendChoosenCardsAfterSpy$(ids: number[]): Observable<number[]> {
        return this.http.post<number[]>(`${this.apiUrl}/gwent/spy`, ids);
    }

    public newGame$(): Observable<unknown> {
        return this.http.post<number[]>(`${this.apiUrl}/gwent/newGame`, {});
    }

    public addCards$(ids: number[]): Observable<number[]> {
        return this.http.post<number[]>(`${this.apiUrl}/gwent/addCards`, ids);
    }
}
