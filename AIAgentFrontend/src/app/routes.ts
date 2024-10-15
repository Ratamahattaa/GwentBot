import { Routes } from "@angular/router";

export const routes: Routes = [
    {
        path: "start",
        loadComponent: () =>
            import("./views/start/start.component").then(
                (c) => c.StartComponent
            ),
    },
    {
        path: "instruction",
        loadComponent: () =>
            import("./views/instruction/instruction.component").then(
                (c) => c.InstructionComponent
            ),
    },
    {
        path: "decks",
        loadComponent: () =>
            import("./views/decks/decks.component").then(
                (c) => c.DecksComponent
            ),
    },
    {
        path: "choose",
        loadComponent: () =>
            import("./views/choose-cards/choose-cards.component").then(
                (c) => c.ChooseCardsComponent
            ),
    },
    {
        path: "faction",
        loadComponent: () =>
            import("./views/faction/faction.component").then(
                (c) => c.FactionComponent
            ),
    },
    {
        path: "game",
        loadComponent: () =>
            import("./views/game/game.component").then((c) => c.GameComponent),
        children: [
            {
                path: "board",
                loadComponent: () =>
                    import("./views/game/views/board/board.component").then(
                        (c) => c.BoardComponent
                    ),
            },
            {
                path: "spy",
                loadComponent: () =>
                    import("./views/game/views/spy/spy.component").then(
                        (c) => c.SpyComponent
                    ),
            },
            { path: "**", redirectTo: "/game/board", pathMatch: "full" },
        ],
    },
    { path: "**", redirectTo: "/start", pathMatch: "full" },
];
