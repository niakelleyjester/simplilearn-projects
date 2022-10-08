import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { User } from 'src/app/models/User';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  /* --------- Properties --------- */
  //define a Users[]
  users:User[] = [];
  userCount:number = 0;
  user:User;
  private currentUser:User = this.getUserFromLocalStorage();
                                  //we want the User data to be persistent. we can use the local storage to accomplish this for now
                                  //once we connect to the backend, we can use http methods and have them be persistent

  /* Observables provide support for passing messages between part of your application
   * The observer pattern is a software design pattern in which an object, called the subject, maintains a list of its dependents,
   * called observers, and notifies them automatically of state changes. Observables are declarative — that is, you define a function
   * for publishing values, but it is not executed until a consumer subscribes to it.
   * The subscribed consumer then receives notifications until the function completes, or until they unsubscribe.
   * Source: https://angular.io/guide/observables
   */
  /* A Subject is a special type of Observable which shares a single execution path among observers.
   * Source: https://www.learnrxjs.io/
   *
   * Behavior Subject: A variant of Subject that requires an initial value and emits its current value whenever it is subscribed to.
   * Source: https://rxjs-dev.firebaseapp.com/api/index/class/BehaviorSubject
   */
  private userSubject: BehaviorSubject<User> = new BehaviorSubject(this.currentUser);

  /* --------- Constructor --------- */
  //when we implement the backend, we will inject a private http:HttpClient dependency
  constructor() {
    this.users = this.setDefaultUserList();
    this.userCount = this.users.length;
  }


  /* --------- Methods --------- */

  /* *******************************************
   * Method Name: addUser()
   * Access Type: public
   * Input Parameters: User object
   * Return Type: void
   * Purpose: Add User object to the User[]
   * ******************************************* */
  addUser(user:User){
    this.users.push(user);
    this.userCount = this.users.length;
  }

  /* *******************************************
   * Method Name: getUserObservable()
   * Access Type: public
   * Input Parameters: none
   * Return Type: Observable<User> - will return a User that is an observable object (real-time fetch of the data)
   * Purpose: create a method to fetch the User object from the API (converts BehaviorSubject to an Observable object)
   *          any changes to the User should happen inside the User service
   * ******************************************* */
  getUserObservable():Observable<User>{
    return this.userSubject.asObservable();
  }

  /* *******************************************
   * Method Name: getUser()
   * Access Type: public
   * Input Parameters: none
   * Return Type: User object
   * Purpose: Returns the latest value of the User (since subject always keeps track of the lastest value of the subject)
   * ******************************************* */
  getUser():User{
    return this.userSubject.value;
  }

  getUser2():User{
    return this.user;
  }

  setUser(u:User){
    this.user = u;
  }

/* *******************************************
   * Method Name: setUserToLocalStorage()
   * Access Type: private
   * Input Parameters: none
   * Return Type: void
   * Purpose: Convert the User object to a JSON string
   * ******************************************* */
  private setUserToLocalStorage():void{

    //this.currentUser.name = from the registration form control
    //this.currentUser.username = from the login form control
    //this.user.password = from the registration form control

    /* The JSON.stringify() method converts a JavaScript value to a JSON string, optionally replacing values if a replacer function
     * is specified or optionally including only the specified properties if a replacer array is specified.
     * Source: https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/JSON/stringify
     */
    const userJson = JSON.stringify(this.currentUser); //represents the User as a JSON string

    /* The localStorage read-only property of the window interface allows you to access a Storage object for the Document's origin;
     * the stored data is saved across browser sessions.
     * localStorage is similar to sessionStorage, except that while localStorage data has no expiration time,
     * sessionStorage data gets cleared when the page session ends — that is, when the page is closed.
     * (localStorage data for a document loaded in a "private browsing" or "incognito" session is cleared when the last "private" tab is closed.)
     * Source: https://developer.mozilla.org/en-US/docs/Web/API/Window/localStorage
     */
    localStorage.setItem('User', userJson); //key: "User", value: user as a JSON string; when we set localStorage, that means we are persisting the user
    this.userSubject.next(this.currentUser); //notify any listeners of the User observable. To feed a new value to the Subject, just call next(theValue), and it will be multicasted to the Observers registered to listen to the Subject.

  }

/* *******************************************
   * Method Name: getUserFromLocalStorage()
   * Access Type: private
   * Input Parameters: none
   * Return Type: User object
   * Purpose: Get the User from Local Storage (to persist the User)
   * ******************************************* */
  private getUserFromLocalStorage():User{
    const userJson = localStorage.getItem('User'); //use the key that we defined in the corresponding set function
    return userJson ? JSON.parse(userJson) : new User();
    /* The JSON.parse() method parses a JSON string, constructing the JavaScript value or object described by the string.
     * An optional reviver function can be provided to perform a transformation on the resulting object before it is returned.
     * Source: https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/JSON/parse
     * */

  }

  /* *******************************************
   * Method Name: setUserList()
   * Access Type: public
   * Input Parameters: none
   * Return Type: User[] array
   * Purpose: Set the dummy list of users
   * ******************************************* */
  setDefaultUserList():User[]{
    return [
      {
        userId:1,
        name:"Nia Jester",
        username:"niakelley",
        password:"admin123",
        address:{
          streetNumber:123,
          streetName: "Predule Way",
          suite:"",
          city:"Dallas",
          state: "Texas",
          country: "USA",
          zipcode:"31932"},
        email: "iamtheadmin@home.com",
        isAdmin: true
      },
      {
        userId:2,
        name:"Leanne Graham",
        username:"Bret",
        password:"abc123",
        address:{
          streetNumber:643,
          streetName: "Kulas Light",
          suite:"Apt. 556",
          city:"Gwenborough",
          state: "",
          country: "England",
          zipcode:"92998-3874"},
        email: "Sincere@april.biz",
        isAdmin: false
      },
      {
        userId:3,
        name:"Ervin Howell",
        username:"Antonette",
        password:"abc123",
        address:{
          streetNumber:1398,
          streetName: "Victor Plains",
          suite:"Suite 879",
          city:"Wisokyburgh",
          state: "",
          country: "Slovenia",
          zipcode:"90566-7771"},
        email: "Shanna@melissa.tv",
        isAdmin: false
      },
      {
        userId:4,
        name:"Clementine Bauch",
        username:"Samantha",
        password:"abc123",
        address:{
          streetNumber:871,
          streetName: "Douglas Extension",
          suite:"Suite 847",
          city:"McKenziehaven",
          state: "",
          country: "England",
          zipcode:"59590-4157"},
        email: "Nathan@yesenia.net",
        isAdmin: false
      },
      {
        userId:5,
        name:"Patricia Lebsack",
        username:"Karianne",
        password:"abc123",
        address:{
          streetNumber:4313,
          streetName: "Koeger Mall",
          suite:"Apt. 692",
          city:"South Elvis",
          state: "",
          country: "England",
          zipcode:"53919-4257"},
        email: "Julianne.OConner@kory.org",
        isAdmin: false
      },
      {
        userId:6,
        name:"Chelsey Dietrich",
        username:"Kamren",
        password:"abc123",
        address:{
          streetNumber:1113,
          streetName: "Skiles Walks",
          suite:"Apt. 351",
          city:"Roscoeview",
          state: "",
          country: "Malta",
          zipcode:"33263"},
        email: "Lucio_Hettinger@annie.ca",
        isAdmin: false
      },
      {
        userId:7,
        name:"Mr. Dennis Schulist",
        username:"Leopoldo_Corkery",
        password:"abc123",
        address:{
          streetNumber:7708,
          streetName: "Norberto Crossing",
          suite:"Apt. 950",
          city:"South Christy",
          state: "",
          country: "Portugal",
          zipcode:"23505-1337"},
        email: "Karley_Dach@jasper.info",
        isAdmin: false
      },
      {
        userId:8,
        name:"Kurtis Weissnat",
        username:"Elwyn.Skiles",
        password:"abc123",
        address:{
          streetNumber:7919,
          streetName: "Rex Trail",
          suite:"Suite 280",
          city:"Howemouth",
          state: "",
          country: "Italy",
          zipcode:"58804-1099"},
        email: "Telly.Hoeger@billy.biz",
        isAdmin: false
      },
      {
        userId:9,
        name:"Nicholas Runolfsdottir V",
        username:"Maxime_Nienow",
        password:"abc123",
        address:{
          streetNumber:9919,
          streetName: "Ellsworth Summit",
          suite:"Suite 729",
          city:"Aliyaview",
          state: "",
          country: "Belarus",
          zipcode:"45169"},
        email: "Sherwood@rosamond.me",
        isAdmin: false
      },
      {
        userId:10,
        name:"Glenna Reichert",
        username:"Delphine",
        password:"abc123",
        address:{
          streetNumber:397,
          streetName: "Dayna Park",
          suite:"Suite 449",
          city:"Bartholomebury",
          state: "",
          country: "England",
          zipcode:"76495-3109"},
        email: "Chaim_McDermott@dana.io",
        isAdmin: false
      },
      {
        userId:11,
        name:"Clementina DuBuque",
        username:"Moriah.Stanton",
        password:"abc123",
        address:{
          streetNumber:7432,
          streetName: "Kattie Turnpike",
          suite:"Suite 198",
          city:"Lebsackbury",
          state: "",
          country: "Croatia",
          zipcode:"31428-2261"},
        email: "Rey.Padberg@karina.biz",
        isAdmin: false
      },
      {
        userId:12,
        name:"Jasen Jester",
        username:"jjester",
        password:"abc123",
        address:{
          streetNumber:123,
          streetName: "Predule Way",
          suite:"",
          city:"Dallas",
          state: "Texas",
          country: "USA",
          zipcode:"31932"},
        email: "jasen@home.com",
        isAdmin: false
      }
    ]
  }

}
