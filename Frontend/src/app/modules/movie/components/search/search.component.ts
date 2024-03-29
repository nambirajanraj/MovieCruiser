import { Component, OnInit } from '@angular/core';
import {Movie} from '../../movie';
import {MovieService} from '../../movie.service';
@Component({
  selector: 'app-search-movie',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchMovieComponent implements OnInit {

  movies: Array<Movie>;

  constructor(private service: MovieService) { }

  ngOnInit() {
  }

  onEnter(searchKey) {
    console.log(searchKey)
    this.service.searchMovie(searchKey).subscribe(movies => {
      this.movies = movies;
    })
  }


}
