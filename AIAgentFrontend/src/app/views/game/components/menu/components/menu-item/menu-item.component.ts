import { CommonModule } from "@angular/common";
import { Component, Input } from "@angular/core";
import { MatIconModule } from "@angular/material/icon";
import { Router, RouterModule } from "@angular/router";
import { Observable } from "rxjs";
import { ConfirmActionDirective } from "../../../../../../shared/directive/confirmAction.directive";
import { MenuItem } from "../../../../../../shared/models/misc-model";
import { GameService } from "../../../../../../shared/services/game.service";

@Component({
    selector: "app-menu-item[item]",
    standalone: true,
    imports: [
        CommonModule,
        MatIconModule,
        RouterModule,
        ConfirmActionDirective,
    ],
    templateUrl: "./menu-item.component.html",
    styleUrls: ["./menu-item.component.scss"],
})
export class MenuItemComponent {
    @Input() public item: MenuItem;

    protected isLastCardSpy$: Observable<boolean>;

    constructor(
        private readonly game: GameService,
        private readonly router: Router
    ) {
        this.isLastCardSpy$ = this.game.isLastCardSpy$;
    }

    protected goToNewGame(): void {
        this.router.navigate(["faction"]);
    }
}
