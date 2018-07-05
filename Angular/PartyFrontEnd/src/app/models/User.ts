export class User {
    personId: number;
    age: number;
    email: string;
    username: string;
    password: string;
    address: {};
    eventsRSVP;

    constructor(values: Object = {}) {
        Object.assign(this, values);
    }
}