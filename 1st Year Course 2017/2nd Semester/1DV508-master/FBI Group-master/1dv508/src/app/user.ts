

export interface Roles {
    customer?: boolean;
    admin?: boolean;
 }

 export interface Address {
     name?: string;
     address?: string;
     zip?: string;
     city?: string;
 }

export interface User {
    uid: string;
    email: string;
    roles: Roles;
    address: Address;
}
