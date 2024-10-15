import { Component, EventEmitter, Input, Output } from "@angular/core";
import { CommonModule } from "@angular/common";
import { CardComponent } from "../card/card.component";
import { Card } from "../../models/game-model";

@Component({
    selector: "app-clickable-card[card]",
    standalone: true,
    imports: [CommonModule, CardComponent],
    templateUrl: "./clickable-card.component.html",
    styleUrls: ["./clickable-card.component.scss"],
})
export class ClickableCardComponent {
    @Input() public card: Card;
    @Input() public isClicked: boolean;
    @Output() protected wasClicked = new EventEmitter<boolean>();
}
