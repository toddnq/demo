import { Team } from "./team";

export class Member {
    id: number;
    firstName: string;
    lastName: string;
    gender: string;
    email: string;
    teamID: Team[];
}