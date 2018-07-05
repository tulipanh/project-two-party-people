export class Event {
    partyId: number;
    creator: {};
    address: {};
    partyName: string;
    partyDate: Date;
    cost: number;
    attendees: {};
    tagList: any[];
    pictureurl: string;
    description: string;

    constructor(values: Object = {}) {
        Object.assign(this, values);
    }
}