import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.css']
})
export class StudentListComponent implements OnInit {
  students = [
    'Harry Potter',
    'Ron Weasley',
    'Hermione Granger',
    'Draco Malfoy',
    'Cedric Diggory'
  ];

  constructor() {
  }

  ngOnInit(): void {
  }

}
