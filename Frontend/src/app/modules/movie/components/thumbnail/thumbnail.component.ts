import { Component, OnInit, Input, Output } from '@angular/core';
import {Movie} from '../../movie';
import {MovieService} from '../../movie.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { EventEmitter } from '@angular/core';
import{MovieDialogComponent} from '../movie-dialog/movie-dialog.component';
import {MatDialogModule} from '@angular/material/dialog';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'movie-thumbnail',
  templateUrl: './thumbnail.component.html',
  styleUrls: ['./thumbnail.component.css']
})
export class ThumbnailComponent implements OnInit {
   @Input()
   movie : Movie;
   @Input()
   useWatchListApi:boolean;

   @Output()
   addMovie = new EventEmitter();

   @Output()
  deleteMovie = new EventEmitter();

    @Output()
    updateMovie = new EventEmitter();

  constructor(private dialog: MatDialog) {
      
     
   }

  ngOnInit() {
  
  }

  addToWatchList(){
     
    this.addMovie.emit(this.movie);


   /* this.movieService.addMovieToWatchList(this.movie)
       .subscribe(movie => {

        this.snackBar.open('Movie Added To WatchList','',{
          duration:1000
        });

       })
    ;*/
  }
  deleteFromWatchList(){
    this.deleteMovie.emit(this.movie);
  }

  updateFromWatchList(actionType)
  {
    let dialogRef = this.dialog.open(MovieDialogComponent, {
      width: '400px', data: {obj: this.movie, actionType: actionType}
    });
    dialogRef.afterClosed().subscribe(result => {
    });
  
  }
  

}
