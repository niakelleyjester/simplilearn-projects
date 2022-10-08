//Define an interface to represent a product
//An interface is a simple class that contains properties
//The Product Interface will enforce the structure of the Product object
export default interface Product{
  /* --------- Properties ---------  */
    productId: number;
    imageUrl: string;
    productName: string;
    price: number;
    brand: string;
    rating: number;
    numOfReviews: number;
    description: string;
    //favorite:boolean; //this is a customer specific attribute
    organic:boolean;
    tags:string[];

}

