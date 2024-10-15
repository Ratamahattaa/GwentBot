import { Component, Input } from "@angular/core";
import { CommonModule } from "@angular/common";
import { PlayFromBackend } from "../../../../../../../../shared/models/game-model";
import { NumberDescriptionComponent } from "../number-description/number-description.component";
import { CardComponent } from "../../../../../../../../shared/components/card/card.component";

@Component({
    selector: "app-your-info",
    standalone: true,
    imports: [CommonModule, NumberDescriptionComponent, CardComponent],
    templateUrl: "./your-info.component.html",
    styleUrls: ["./your-info.component.scss"],
})
export class YourInfoComponent {
    @Input() public play: PlayFromBackend;
}
