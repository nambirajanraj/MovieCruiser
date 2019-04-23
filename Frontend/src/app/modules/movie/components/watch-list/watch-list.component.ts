import { Component, OnInit } from '@angular/core';
import {Movie} from '../../movie';
import {MovieService} from '../../movie.service';
@Component({
  selector: 'movie-watch-list',
  templateUrl: './watch-list.component.html',
  styleUrls: ['./watch-list.component.css']
})
export class WatchListComponent implements OnInit {

   movies :Array<Movie>;
   useWatchListApi =true;
  constructor(private movieService: MovieService) { 
    this.movies = []
  }

  ngOnInit() {
    this.movieService.getWatchListMovies().subscribe(movies =>
      {
        this.movies.push(...movies);
      })
  }

}
