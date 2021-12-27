import { Role } from "./Role";

export class User{
    id!: number;
    name!: string;
    lastName!:string;
    username!: string;
    password!:string;
    email!: string;
    role: Role = new Role;
    type!: string;
    token!:string;
}