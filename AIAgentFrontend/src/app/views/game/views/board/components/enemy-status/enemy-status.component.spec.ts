import { ComponentFixture, TestBed } from "@angular/core/testing";

import { EnemyStatusComponent } from "./enemy-status.component";

describe("EnemyStatusComponent", () => {
    let component: EnemyStatusComponent;
    let fixture: ComponentFixture<EnemyStatusComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [EnemyStatusComponent],
        }).compileComponents();

        fixture = TestBed.createComponent(EnemyStatusComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it("should create", () => {
        expect(component).toBeTruthy();
    });
});
