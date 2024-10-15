import {
    Directive,
    EventEmitter,
    HostListener,
    Input,
    Output,
} from "@angular/core";
import { MatDialog } from "@angular/material/dialog";
import { tap } from "rxjs";
import { ConfirmModalComponent } from "../components/confirm-modal/confirm-modal.component";

@Directive({
    selector: "[appConfirmAction]",
    standalone: true,
})
export class ConfirmActionDirective {
    @Input() public confirmMessage = "Are you sure?"; // Message to display in the modal
    @Output() protected confirmed: EventEmitter<void> =
        new EventEmitter<void>(); // Event to emit when the action is confirmed

    constructor(private readonly dialog: MatDialog) {}

    @HostListener("click", ["$event"])
    onClick(event: Event): void {
        event.preventDefault(); // Prevent the default action

        const dialogRef = this.dialog.open(ConfirmModalComponent, {
            data: { message: this.confirmMessage },
        });

        dialogRef
            .afterClosed()
            .pipe(
                tap((value: boolean) => {
                    value && this.confirmed.emit();
                })
            )
            .subscribe();
    }
}
