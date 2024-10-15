import { ComponentFixture, TestBed } from "@angular/core/testing";

import { NumberDescriptionComponent } from "./number-description.component";

describe("NumberDescriptionComponent", () => {
    let component: NumberDescriptionComponent;
    let fixture: ComponentFixture<NumberDescriptionComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [NumberDescriptionComponent],
        }).compileComponents();

        fixture = TestBed.createComponent(NumberDescriptionComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it("should create", () => {
        expect(component).toBeTruthy();
    });
});
