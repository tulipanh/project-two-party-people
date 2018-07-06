import { Event } from './Event';

export class User {
    personId: number;
    age: number;
    email: string;
    username: string;
    password: string;
    address: {};
    eventsRSVP: Event[];
    creatorEvents: Event[];

    constructor(values: Object = {}) {
        Object.assign(this, values);
    }
}