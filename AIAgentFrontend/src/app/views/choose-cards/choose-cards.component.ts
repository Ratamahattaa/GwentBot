import { Component, OnInit } from "@angular/core";
import { Card } from "../../shared/models/game-model";
import { CommonModule } from "@angular/common";
import { PregameService } from "../../shared/services/pregame.service";
import { Observable } from "rxjs";
import { ClickableCardComponent } from "../../shared/components/clickable-card/clickable-card.component";
import { IsCardClickedPipe } from "../../shared/pipes/is-card-clicked.pipe";

@Component({
    selector: "app-choose-cards",
    templateUrl: "./choose-cards.component.html",
    standalone: true,
    imports: [CommonModule, ClickableCardComponent, IsCardClickedPipe],
    styleUrls: ["./choose-cards.component.scss"],
})
export class ChooseCardsComponent implements OnInit {
    protected cards$: Observable<Card[]>;
    protected choosenCards$: Observable<Card[]>;

    constructor(private readonly pregame: PregameService) {}

    ngOnInit(): void {
        this.pregame.fetchStartingCardsFromApi();
        this.cards$ = this.pregame.allCards$;
        this.choosenCards$ = this.pregame.choosenCards$;
    }

    protected cardStateWasChanged(card: Card): void {
        this.pregame.cardStateWasChanged(card);
    }

    protected onSend(): void {
        this.pregame.sendStartingCardsToApi();
    }
}
