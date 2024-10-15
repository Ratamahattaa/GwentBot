import { MenuItem } from "../../../../shared/models/misc-model";

export const menuItems: MenuItem[] = [
    {
        name: "Board",
        icon: "event_available",
        route: "/game/board",
    },
    {
        name: "Add cards",
        icon: "event_available",
        route: "/game/spy",
        isSpy: true,
    },
    {
        name: "Instruction",
        icon: "event_available",
        route: "/instruction",
        target: "_blank",
    },
    {
        name: "Decks",
        icon: "event_available",
        route: "/decks",
        target: "_blank",
    },
    {
        name: "New Game",
        icon: "event_available",
        route: "/faction",
    },
];
