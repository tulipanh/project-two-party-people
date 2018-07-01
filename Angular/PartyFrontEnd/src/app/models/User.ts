export class User {
    id: number;
    age: number;
    email: string;
    username: string;
    password: string;
    address: string;

    constructor(values: Object = {}) {
        Object.assign(this, values);
    }
}