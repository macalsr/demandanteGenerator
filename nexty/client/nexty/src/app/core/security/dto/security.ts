export interface UserDto {
    id: number;
    identifierTypeId: number;
    personIdentifier: string;
    personName: string;
    email: string;
    statusId: number;
    statusName: string;
    roles: string[];
  }

  export interface UserAuthenticatedDto {
    id: number;
    identifierTypeId: number;
    personIdentifier: string;
    email: string;
    personName: string;
    statusId: number;
    statusName: string;
    roles: string[];
  }
  