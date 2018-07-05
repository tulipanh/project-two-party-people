export class EventOverview {
    id: number;
    name: string;
    date: Date;
    address: {
        streetName: string;
        city: string;
        state:string;
    };
    picture: string;
}