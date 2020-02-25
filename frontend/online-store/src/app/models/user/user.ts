import { Role } from './role.enum';
import { Address } from './address';



export class User {
    id: number;
    username: string = '';
    password: string = '';
    name: string = '';
    role: Role;
    address: Address;
    phoneNumber: string = '';
    token: string = '';
    email: string = '';

}
