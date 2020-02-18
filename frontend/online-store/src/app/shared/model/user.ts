export interface IUser {
username: string;
firstName: string;
lastName: string;
password: string;
}

export class User implements User {
    constructor(public username: string ='',
        public firstName: string = '',
        public lastName: string = '',
        public password: string =''
    ) {
    }

}