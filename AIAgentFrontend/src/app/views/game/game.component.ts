import { CommonModule } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { RouterModule } from "@angular/router";
import { GameService } from "../../shared/services/game.service";
import { MenuComponent } from "./components/menu/menu.component";

@Component({
    selector: "app-game",
    standalone: true,
    imports: [CommonModule, MenuComponent, RouterModule],
    templateUrl: "./game.component.html",
    styleUrls: ["./game.component.scss"],
})
export class GameComponent implements OnInit {
    constructor(private readonly gameService: GameService) {}

    ngOnInit(): void {
        this.gameService.reset();
    }
}
