import { ComponentFixture, TestBed } from "@angular/core/testing";

import { EnemyInfoComponent } from "./enemy-info.component";

describe("EnemyInfoComponent", () => {
    let component: EnemyInfoComponent;
    let fixture: ComponentFixture<EnemyInfoComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [EnemyInfoComponent],
        }).compileComponents();

        fixture = TestBed.createComponent(EnemyInfoComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it("should create", () => {
        expect(component).toBeTruthy();
    });
});
