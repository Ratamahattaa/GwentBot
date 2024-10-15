import { Component, Input, forwardRef } from "@angular/core";
import { CommonModule } from "@angular/common";
import { Weather } from "../../../../../../../../shared/models/game-model";
import { ControlValueAccessor, NG_VALUE_ACCESSOR } from "@angular/forms";

@Component({
    selector: "app-board-status",
    standalone: true,
    imports: [CommonModule],
    templateUrl: "./board-status.component.html",
    styleUrls: ["./board-status.component.scss"],
    providers: [
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => BoardStatusComponent),
            multi: true,
        },
    ],
})
export class BoardStatusComponent implements ControlValueAccessor {
    @Input() public type: Weather | "Pass";

    private _value = false;
    private onChange: (value: boolean) => void = () => {};
    private onTouched: () => void = () => {};

    get value(): boolean {
        return this._value;
    }

    set value(val: boolean) {
        if (val !== this._value) {
            this._value = val;
            this.onChange(val);
            this.onTouched();
        }
    }

    writeValue(value: boolean): void {
        this.value = value;
    }

    registerOnChange(fn: (value: boolean) => void): void {
        this.onChange = fn;
    }

    registerOnTouched(fn: () => void): void {
        this.onTouched = fn;
    }

    toggleValue(): void {
        this.value = !this.value;
    }

    protected get weatherImage(): string {
        switch (this.type) {
            case "Fog":
                return "../../../../../../../../../assets/images/weather/fog.png";
            case "Frost":
                return "../../../../../../../../../assets/images/weather/frost.png";
            case "Rain":
                return "../../../../../../../../../assets/images/weather/rain.png";
            case "Pass":
                return "../../../../../../../../../assets/images/board-elements/coin.png";
            default:
                return "../../../../../../../../../assets/images/board-elements/coin.png";
        }
    }
}
