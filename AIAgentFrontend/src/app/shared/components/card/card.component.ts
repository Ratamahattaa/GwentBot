import { Component, Input } from "@angular/core";
import { Card } from "../../models/game-model";
import { CommonModule } from "@angular/common";

@Component({
    selector: "app-card[card]",
    templateUrl: "./card.component.html",
    standalone: true,
    imports: [CommonModule],
    styleUrls: ["./card.component.scss"],
})
export class CardComponent {
    @Input() public card: Card;

    protected getCombatClass(): string {
        switch (this.card.combat) {
            case "Close":
                return "card-container__card-row--close";
            case "Ranged":
                return "card-container__card-row--ranged";
            case "Siege":
                return "card-container__card-row--siege";
            default:
                return "";
        }
    }
}
