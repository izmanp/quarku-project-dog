import { Component, OnInit } from '@angular/core';
import {FormGroup,FormBuilder,FormControl} from '@angular/forms'
import {Dog} from "../../model/dog";
import {DogService} from "../../service/dog.service";
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  dog !: FormGroup;
  dogObj : Dog = new Dog();
  dogList : Dog[]=[];
  constructor(private formBuilder :FormBuilder, private dogService : DogService) { }

  ngOnInit(): void {
    this.getAllDog();
    this.dog = this.formBuilder.group({
      id: [''],
      name: [''],
      age:[''],
      color: [''],
      raceId:[''],
      race:[''],
      description:['']

    })
  }
addDog(){
    console.log(this.dog);
    this.dogObj.id = this.dog.value.id;
    this.dogObj.name = this.dog.value.name;
    this.dogObj.age = this.dog.value.age;
    this.dogObj.color = this.dog.value.color;
    this.dogObj.raceId = this.dog.value.raceId;
    this.dogObj.race = this.dog.value.race;
    this.dogObj.description = this.dog.value.description;

    this.dogService.addDog(this.dogObj)
      .subscribe(res=>{
        console.log(res);
        this.getAllDog();
      },err=>{
        console.log(err);
  }
      )
}
getAllDog(){
    this.dogService.getAllDog()
      .subscribe(res=>{
        this.dogList=res;

      },err=>{
        console.log("error")
        }
      );
}
editDog(dogs : Dog){
    this.dog.controls['id'].setValue(dogs.id);
    this.dog.controls['name'].setValue(dogs.name);
    this.dog.controls['age'].setValue(dogs.age);
    this.dog.controls['color'].setValue(dogs.color);
    this.dog.controls['raceId'].setValue(dogs.raceId);
    this.dog.controls['race'].setValue(dogs.race);
    this.dog.controls['description'].setValue(dogs.description);
}
updateDog(){
  this.dogObj.id = this.dog.value.id;
  this.dogObj.name = this.dog.value.name;
  this.dogObj.age = this.dog.value.age;
  this.dogObj.color = this.dog.value.color;
  this.dogObj.raceId = this.dog.value.raceId;
  this.dogObj.race = this.dog.value.race;
  this.dogObj.description = this.dog.value.description;

  this.dogService.updateDog(this.dogObj)
    .subscribe(res=>{
        console.log(res);
        this.getAllDog();
      },err=>{
        console.log(err);
      }
    )
}
deleteDog(dogs : Dog){
  if(window.confirm('Are sure you want to delete this dog ?')){
this.dogService.deleteDog(dogs)
  .subscribe(res=>{
    alert('Dog deleted successfully');
      this.getAllDog();
    },err=>{
      console.log(err);
    }
  )
}}
}
