import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Dog} from "../model/dog";

@Injectable({
  providedIn: 'root'
})
export class DogService {

  addDogUrl : string;
  getDogUrl : string;
  updateDogUrl  : string;
  deleteDogUrl : string;
  constructor(private http : HttpClient) {

    this.addDogUrl = 'http://localhost:8080/dogs/post';
    this.getDogUrl = 'http://localhost:8080/dogs';
    this.updateDogUrl = 'http://localhost:8080/dogs/update';
    this.deleteDogUrl = 'http://localhost:8080/dogs/delete';
  }
  addDog(dogs : Dog): Observable<Dog>{
    return this.http.post<Dog>(this.addDogUrl,dogs);
  }
  getAllDog(): Observable<Dog[]>{
    return this.http.get<Dog[]>(this.getDogUrl);
  }
  updateDog(dogs : Dog) : Observable<Dog>{
    return this.http.put<Dog>(this.updateDogUrl, dogs);
  }
  deleteDog(dogs: Dog): Observable<Dog>{
    return this.http.delete<Dog>(this.deleteDogUrl+'/'+dogs.id);
  }
}
