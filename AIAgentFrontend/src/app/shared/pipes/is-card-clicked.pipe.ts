import { Pipe, PipeTransform } from "@angular/core";
import { Card } from "../models/game-model";

@Pipe({
    name: "isCardClicked",
    standalone: true,
})
export class IsCardClickedPipe implements PipeTransform {
    transform(card: Card, cards: Card[] | null): boolean {
        if (cards === null) return false;
        return cards.includes(card);
    }
}
