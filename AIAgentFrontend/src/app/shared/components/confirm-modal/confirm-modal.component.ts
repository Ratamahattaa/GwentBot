import { CommonModule } from "@angular/common";
import { Component, Inject } from "@angular/core";
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material/dialog";

@Component({
    selector: "app-confirm-modal",
    standalone: true,
    imports: [CommonModule],
    templateUrl: "./confirm-modal.component.html",
    styleUrls: ["./confirm-modal.component.scss"],
})
export class ConfirmModalComponent {
    constructor(
        public dialogRef: MatDialogRef<ConfirmModalComponent>,
        @Inject(MAT_DIALOG_DATA) public data: { message: string }
    ) {}

    onNoClick(): void {
        this.dialogRef.close(false);
    }

    onYesClick(): void {
        this.dialogRef.close(true);
    }
}
