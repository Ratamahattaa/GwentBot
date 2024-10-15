export interface MenuItem {
    name: string;
    icon: string;
    route: string;
    target?: string;
    isSpy?: boolean;
    isNewGame?: boolean;
}
