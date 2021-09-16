export interface ClientResponse{
    idClient:number;
    businessId:string;
    email:string;
    phone:string;
    sharedKey:string;
    dataAdded:string;
}

export interface ClientRequest{
    idClient?:number;
    name:string;
    email:string;
    phone:string;
    startDate:string;
    endDate:string;
}