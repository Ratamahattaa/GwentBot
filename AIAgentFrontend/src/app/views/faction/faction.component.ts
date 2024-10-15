import { CommonModule } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { PregameService } from "../../shared/services/pregame.service";
import { Faction } from "../../shared/models/game-model";

@Component({
    selector: "app-faction",
    templateUrl: "./faction.component.html",
    standalone: true,
    imports: [CommonModule],
    styleUrls: ["./faction.component.scss"],
})
export class FactionComponent implements OnInit {
    constructor(
        private readonly pregame: PregameService,
        private readonly router: Router
    ) {}

    ngOnInit(): void {
        this.pregame.resetGame$().subscribe();
    }

    protected setFaction(faction: Faction): void {
        this.pregame.faction = faction;
        this.router.navigate(["/choose"]);
    }
}
