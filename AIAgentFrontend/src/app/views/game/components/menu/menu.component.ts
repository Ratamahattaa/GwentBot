import { Component } from "@angular/core";
import { CommonModule } from "@angular/common";
import { MenuItemComponent } from "./components/menu-item/menu-item.component";
import { menuItems } from "./menu.data";
import { MenuItem } from "../../../../shared/models/misc-model";

@Component({
    selector: "app-menu",
    standalone: true,
    imports: [CommonModule, MenuItemComponent],
    templateUrl: "./menu.component.html",
    styleUrls: ["./menu.component.scss"],
})
export class MenuComponent {
    protected menuItems: MenuItem[];

    constructor() {
        this.menuItems = menuItems;
    }
}
