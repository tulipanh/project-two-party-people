export class Event {
    partyId: number;
    creator: {};
    address: {
        streetName: string;
        city: string;
        state: string;
        zipCode: string;
        coordinates: {
            latitude: number;
            longitude: number;
        }
    };
    partyName: string;
    partyDate: Date;
    cost: number;
    attendees: {};
    tagList: any[];
    pictureUrl: string;
    description: string;

    constructor(values: Object = {}) {
        Object.assign(this, values);
    }
}