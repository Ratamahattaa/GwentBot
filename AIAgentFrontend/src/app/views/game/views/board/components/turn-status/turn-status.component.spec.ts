import { ComponentFixture, TestBed } from "@angular/core/testing";

import { TurnStatusComponent } from "./turn-status.component";

describe("TurnStatusComponent", () => {
    let component: TurnStatusComponent;
    let fixture: ComponentFixture<TurnStatusComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [TurnStatusComponent],
        }).compileComponents();

        fixture = TestBed.createComponent(TurnStatusComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it("should create", () => {
        expect(component).toBeTruthy();
    });
});
