export interface Card {
    id: number;
    image: string;
    combat: CombatStyle;
}

export interface EnemyStatus extends EnemyStatusBase {
    isFrost: boolean;
    isRain: boolean;
    isFog: boolean;
}

export interface EnemyStatusBase {
    enemyPassed: boolean;
    enemyAllPoints: number;
}

export const weather = {
    Fog: "Fog",
    Rain: "Rain",
    Frost: "Frost",
} as const;

export type Weather = (typeof weather)[keyof typeof weather];

export interface PlayFromBackend {
    id: number;
    image: string;
    pointsOnBoard: number;
    spy: boolean;
}

export interface TurnStatus {
    turn: number;
    enemiesSpyPoints: number;
    player: PlayFromBackend;
}

export const factions = {
    Nilfgaard: "NG",
    Northern_Realms: "NR",
} as const;

export type Faction = (typeof factions)[keyof typeof factions];

export const combat = {
    Close: "Close",
    Ranged: "Ranged",
    Siege: "Siege",
} as const;

export type CombatStyle = (typeof combat)[keyof typeof combat];
