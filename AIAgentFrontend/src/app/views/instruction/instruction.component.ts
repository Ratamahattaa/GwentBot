import { CommonModule } from "@angular/common";
import { Component } from "@angular/core";
import { Router } from "@angular/router";

@Component({
    selector: "app-instruction",
    templateUrl: "./instruction.component.html",
    standalone: true,
    imports: [CommonModule],
    styleUrls: ["./instruction.component.scss"],
})
export class InstructionComponent {
    protected instructionIndex = 1;

    constructor(private readonly router: Router) {}

    protected next(): void {
        if (this.instructionIndex === 9) {
            this.router.navigateByUrl("/start");
        }
        this.instructionIndex++;
    }

    protected getContainerClasses(): string {
        switch (this.instructionIndex) {
            case 1:
                return "container1";
            case 2:
                return "container2";
            case 3:
                return "container3";
            case 4:
                return "container4";
            case 5:
                return "container5";
            case 6:
                return "container6";
            case 7:
                return "container7";
            case 8:
                return "container8";
            case 9:
                return "container9";
            default:
                return "";
        }
    }
}
