export class User {
    personId: number;
    age: number;
    email: string;
    username: string;
    password: string;
    address: {};

    constructor(values: Object = {}) {
        Object.assign(this, values);
    }
}