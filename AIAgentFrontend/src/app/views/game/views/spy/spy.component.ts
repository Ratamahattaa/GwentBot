import { CommonModule } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { Observable } from "rxjs";
import { ClickableCardComponent } from "../../../../shared/components/clickable-card/clickable-card.component";
import { Card } from "../../../../shared/models/game-model";
import { IsCardClickedPipe } from "../../../../shared/pipes/is-card-clicked.pipe";
import { SpyRootService } from "./services/spy-root.service";
import { ConfirmActionDirective } from "../../../../shared/directive/confirmAction.directive";

@Component({
    selector: "app-spy",
    standalone: true,
    imports: [
        CommonModule,
        ClickableCardComponent,
        IsCardClickedPipe,
        ConfirmActionDirective,
    ],
    templateUrl: "./spy.component.html",
    styleUrls: ["./spy.component.scss"],
    providers: [SpyRootService],
})
export class SpyComponent implements OnInit {
    protected cards$: Observable<Card[]>;
    protected choosenCards$: Observable<Card[]>;

    constructor(private readonly root: SpyRootService) {
        this.cards$ = this.root.deckCards$;
        this.choosenCards$ = this.root.choosenSpyCards$;
    }

    ngOnInit(): void {
        this.root.fetchCards$().subscribe();
    }

    protected cardStateWasChanged(card: Card): void {
        this.root.cardStateWasChanged(card);
    }

    protected onSend(): void {
        this.root.sendStartingCardsToApi();
    }
}
